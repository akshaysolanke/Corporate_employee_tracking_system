package attendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import app.Dbconection;

public class AttendanceMain {

		public void atten() throws ClassNotFoundException, SQLException
		{
			AttendanceDAO dao = new AttendanceDAO();
	        Scanner sc = new Scanner(System.in);
	        int choice ;
	       do{

	            System.out.println("\n---- Attendance Module ----");
	            System.out.println("1. Mark Attendance");
	            System.out.println("2. View One day Attendance");
	            System.out.println("3. View All Attendance");
	            System.out.println("4. Exit");

	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();

	            switch (choice) {

	                case 1:	dao.markAttendance();
	                    	break;

	                case 2: dao.viewAttendanceByEmployee();
	                    	break;

	                case 3:dao.viewAllAttendance();
	                    	break;

	                case 4:
	                    System.out.println("Program Ended");
	                    break;

	                default:
	                    System.out.println("Invalid Choice");
	            }
	        }while(choice!=4);
	    }
}
