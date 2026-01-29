package attendance;

import java.util.Scanner;

public class AttendanceMain {
	 public static void main(String[] args) {

	        AttendanceDAO dao = new AttendanceDAO();
	        Scanner sc = new Scanner(System.in);

	        while (true) {

	            System.out.println("\n---- Attendance Module ----");
	            System.out.println("1. Mark Attendance");
	            System.out.println("2. View Employee Attendance");
	            System.out.println("3. View All Attendance");
	            System.out.println("4. Exit");

	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {

	                case 1:
	                    System.out.print("Enter Employee ID: ");
	                    int empId = sc.nextInt();

	                    System.out.print("Enter Date (yyyy-mm-dd): ");
	                    String date = sc.next();

	                    System.out.print("Enter Status (Present/Absent/Leave): ");
	                    String status = sc.next();

	                    dao.markAttendance(empId, date, status);
	                    break;

	                case 2:
	                    System.out.print("Enter date : ");
	                    int dt = sc.nextInt();

	                    dao.viewAttendanceByEmployee(dt);
	                    break;

	                case 3:
	                	System.out.print("Enter Employee Id : ");
	                    int eid = sc.nextInt();
	                    dao.viewAllAttendance(eid);
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