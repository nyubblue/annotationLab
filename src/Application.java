import ubun.annotation.utility.Hibernate;
import ubun.annotation.utility.TransactionHistory;

public class Application {
	public static void main(String[] args) throws Exception {
		TransactionHistory sane = new TransactionHistory(1233, "Sane", "Credit", 1000);
		TransactionHistory kino = new TransactionHistory(1244, "Kino", "Credit", 322);
		TransactionHistory jei = new TransactionHistory(1255, "Jei", "Credit", 3000);
		TransactionHistory juh = new TransactionHistory(1266, "Juh", "Credit", 2313);

		Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
		
		hibernate.write(sane);
	}
}
