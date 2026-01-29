package employee;
import attendance.AttendanceMain;
import java.sql.*;
import java.util.Scanner;

public class Emp {

    Scanner sc = new Scanner(System.in);
    AttendanceMain attendence = new AttendanceMain();
    Connection cn;
    Statement st;

    // EMPLOYEE MENU
    public void employeeMenu() throws Exception {

        while (true) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View Personal Details");
            System.out.println("2. View Project");
            System.out.println("3. View Department");
            System.out.println("4. Check Attendance");
            System.out.println("5. give Attendance");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewPersonalDetails();
                case 2 -> viewProject();
                case 3 -> viewDepartment();
                case 4 -> checkAttendance();
                case 5 -> attendence.atten();
                case 6 -> {
                    System.out.println("Thank You");
                    return;
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    // 1️View Personal Details
   public void viewPersonalDetails() throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q = "SELECT ename,email,erole FROM employee WHERE eid=" + eid;
        ResultSet rs = st.executeQuery(q);

        if (rs.next()) {
            System.out.println("Name  : " + rs.getString("ename"));
            System.out.println("Email : " + rs.getString("email"));
            System.out.println("Role  : " + rs.getString("erole"));
        } else {
            System.out.println("Employee not found");
        }
    }

    // 2️ View Project
   public void viewProject() throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q =
            "SELECT p.pname, p.status " +
            "FROM project p, employee_project ep " +
            "WHERE p.pid = ep.pid AND ep.eid=" + eid;

        ResultSet rs = st.executeQuery(q);

        System.out.println("\n--- PROJECT DETAILS ---");
        while (rs.next()) {
            System.out.println("Project Name : " + rs.getString("pname"));
            System.out.println("Status       : " + rs.getString("status"));
        }
    }

    // 3️View Department
   public void viewDepartment() throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q =
            "SELECT d.dname, d.dhead " +
            "FROM department d, employee e " +
            "WHERE d.did = e.did AND e.eid=" + eid;

        ResultSet rs = st.executeQuery(q);

        if (rs.next()) {
            System.out.println("Department Name : " + rs.getString("dname"));
            System.out.println("Department Head : " + rs.getString("dhead"));
        } else {
            System.out.println("Department not found");
        }
    }

    // 4️Check Attendance
   public void checkAttendance() throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q = "SELECT date,status FROM attendance WHERE eid=" + eid;
        ResultSet rs = st.executeQuery(q);

        System.out.println("\n--- ATTENDANCE ---");
        while (rs.next()) {
            System.out.println(rs.getDate("date") + " : " + rs.getString("status"));
        }
    }


}
