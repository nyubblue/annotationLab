package ubun.annotation.utility;

import ubun.annotation.MostUsed;

public class Utility {

	void doStaff() {
		System.out.println("Doing something .");
	}
	
	@MostUsed("Python")
	void doStaff(String arg) {
		System.out.println("Operating on : "+ arg);
	}

	void doStaff(int i) {
		System.out.println("Operating on : "+ i);
	}
}
