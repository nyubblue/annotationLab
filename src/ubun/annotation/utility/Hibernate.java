package ubun.annotation.utility;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ubun.annotation.Column;
import ubun.annotation.PrimaryKey;

public class Hibernate<T> {

	private Connection con;
	private AtomicLong id = new AtomicLong();

	public static <T> Hibernate<T> getConnection() throws SQLException, ClassNotFoundException {
		return new Hibernate<T>();
	}

	private Hibernate() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ubn_annation?useSSL=false", "root",
				"abc123");
	}

	public void write(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Class<? extends Object> clzz = t.getClass();
		Field[] fields = clzz.getDeclaredFields();
		Field prkey = null;
		StringJoiner joiner = new StringJoiner(",");
		List<Field> columns = new ArrayList<>();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.isAnnotationPresent(PrimaryKey.class)) {
				prkey = f;
				System.out.println(
						"The primary key is : " + f.getName() + "value : " + f.get(t) + " and the columns are : ");
			} else if (f.isAnnotationPresent(Column.class)) {
				joiner.add(f.getName());
				columns.add(f);
				System.out.println(f.getName() + " value : " + f.get(t));
			}
		}
		int number = columns.size() + 1;
		String qMark = IntStream.range(0, number).mapToObj(e -> "?").collect(Collectors.joining(","));
		String sql = "INSERT INTO " + clzz.getSimpleName() + "( " + prkey.getName() + "," +joiner.toString() + ") "
				+ "VALUES (" + qMark + ")";
		System.out.println("Success\n");
		PreparedStatement stmt = con.prepareStatement(sql);
		if (prkey.getType() == long.class) {
			stmt.setLong(1, id.incrementAndGet());
		}
		int index = 2;
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getType() == int.class) {
				stmt.setInt(index++, (int) field.get(t));
			} else if (field.getType() == String.class) {
				stmt.setString(index++, (String) field.get(t));
			}
		}
		System.out.println("\n" + sql + "\n-----END-----");

	}
}
