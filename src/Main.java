import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import ubun.annotation.MostUsed;
import ubun.annotation.utility.Utility;

public class Main {
	public static void main(String[] args) throws Exception {
		Class<?> clzz = Class.forName("ubun.annotation.utility.Utility");
		Constructor<?> constructor = clzz.getConstructor();
		Utility utility = (Utility) constructor.newInstance();
		Method[] methods = clzz.getDeclaredMethods();
		for(Method method : methods) {
			if(method.isAnnotationPresent(MostUsed.class)) {
				MostUsed annotation = method.getAnnotation(MostUsed.class);
//				String value = annotation.value();
				method.setAccessible(true);
				method.invoke(utility, annotation.value());
			}
		}
	}
}
