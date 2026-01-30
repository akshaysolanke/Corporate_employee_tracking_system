package attendance;

import java.util.Scanner;

public class AttendanceMain {
	
		public void atten()
		{
			AttendanceDAO dao = new AttendanceDAO();
	        Scanner sc = new Scanner(System.in);

	        while (true) {

	            System.out.println("\n---- Attendance Module ----");
	            System.out.println("1. Mark Attendance");
	            System.out.println("2. View One day Attendance");
	            System.out.println("3. View All Attendance");
	            System.out.println("4. Exit");

	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {

	                case 1:	dao.markAttendance();
	                    	break;

	                case 2: dao.viewAttendanceByEmployee();
	                    	break;

	                case 3:dao.viewAllAttendance();
	                    	break;

	                case 4:
	                    System.out.println("Program Ended");
	                    System.exit(0);

	                default:
	                    System.out.println("Invalid Choice");
	            }
	        }
	    }
}
