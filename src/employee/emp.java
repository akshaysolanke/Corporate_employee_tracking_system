package employee;
import app.Dbconection;
import attendance.AttendanceMain;
import java.sql.*;
import java.util.Scanner;


public class Emp {

    Scanner sc = new Scanner(System.in);
    AttendanceMain attendence = new AttendanceMain();
    Connection cn;
   
  //here i get access of cn using constructor
   public Emp() throws ClassNotFoundException, SQLException
    {
    	this.cn=Dbconection.con();
    	this.sc=sc;
    }
   
    // user login
   	public void userlogin() throws Exception
   	{
   		System.out.println("Are you Registered (yes/no)");
   	    String msg = sc.next();

   	    if (msg.equalsIgnoreCase("yes")) {

   	        System.out.println("Enter your email");
   	        String email = sc.next();

   	        System.out.println("Enter your Password");
   	        String pass = sc.next();

   	        String sql = "select e_email,upassword from emp_registration where e_email=? AND upassword=?";
   	        PreparedStatement ps = cn.prepareStatement(sql);
   	        ps.setString(1, email);
   	        ps.setString(2, pass);

   	        ResultSet rs = ps.executeQuery();

   	        if (rs.next()) {
   	            System.out.println("Login successful");
   	            employeeMenu();
   	        } else {
   	            System.out.println("Invalid Credentials");
   	        }

   	    } else {
   	        System.out.println("Register first");
   	        registartion();
   	    }
   		
   	}
   	
   	//user registration
   	public void registartion() throws Exception
   	{
   		System.out.println("Enter User name");
   		String uname=sc.next();
   		
   		System.out.println("Enter Password");
   		String password=sc.next();
   		
   		System.out.println("Enter contact No");
   		Long mo=sc.nextLong();
   		
   		System.out.println("Enter Email");
   		String email=sc.next();
   		
   		String sql="insert into emp_registration(username,upassword,contact,e_email) values(?,?,?,?)";
   		PreparedStatement ps = cn.prepareStatement(sql);
   		ps.setString(1, uname);
   		ps.setString(2, password);
   		ps.setLong(3, mo);
   		ps.setString(4, email);
   		ps.executeUpdate();
   		
   		System.out.println("Registration completed");
   		userlogin();
   	}
   	
    // EMPLOYEE MENU
    public void employeeMenu() throws Exception {
   
        while (true) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View Personal Details");
            System.out.println("2. View Project");
            System.out.println("3. View Department");
            System.out.println("4. Attendance");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewPersonalDetails();
                case 2 -> viewProject();
                case 3 -> viewDepartment();
                case 4 -> attendence.atten();
                case 5 -> {
                    System.out.println("Thank You");
                    return;
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    // 1️View Personal Details
   public void viewPersonalDetails() throws SQLException {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q = "SELECT ename,email,username, passwords,erole FROM employee WHERE e_id=?";
        PreparedStatement ps = cn.prepareStatement(q);
        ps.setInt(1, eid);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            System.out.println("Name  : " + rs.getString("ename"));
            System.out.println("Email : " + rs.getString("email"));
            System.out.println("Username  : " + rs.getString("username"));
            System.out.println("Password  : " + rs.getString("passwords"));
            System.out.println("Role  : " + rs.getString("erole"));
        } else {
            System.out.println("Employee not found");
        }
    }

    // 2️ View Project
   public void viewProject() throws SQLException {
	   
        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q ="select p.pname, p.p_status from employee e join project p on e.p_id=p.p_id where e.e_id=?";
        PreparedStatement ps = cn.prepareStatement(q);
        ps.setInt(1, eid);
        ResultSet rs = ps.executeQuery();
        
        System.out.println("\n--- PROJECT DETAILS ---");
        while(rs.next())
        {
        		System.out.println("Project Name : " + rs.getString("pname"));
            System.out.println("Status       : " + rs.getString("p_status"));
            System.out.println("---------------------------------------------------------");
        }
        

    }

    // 3️View Department
   public void viewDepartment() throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        String q ="select d.d_name,d.d_head from employee e join department d on e.d_id = d.d_id where e.e_id=?";
        PreparedStatement ps = cn.prepareStatement(q);
        ps.setInt(1, eid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Department Name : " + rs.getString("d_name"));
            System.out.println("Department Head : " + rs.getString("d_head"));
        } else {
            System.out.println("Department not found");
        }
    }



}
