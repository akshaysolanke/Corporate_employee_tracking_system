package attendance;

import app.Dbconection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AttendanceDAO {
  
	    Connection cn;
		Scanner sc = new Scanner(System.in);
		AttendanceDAO() throws ClassNotFoundException, SQLException
		{
			this.cn=Dbconection.con();
		}
		
	    // Mark Attendance
	    public void markAttendance() {
	    	
	        try {
 
	            String sql = "INSERT INTO attendence (e_id, a_date, a_status) VALUES(?,?,?)";
	            PreparedStatement ps = cn.prepareStatement(sql);
	            
				System.out.print("Enter Employee ID: ");
	            int empId = sc.nextInt();

	            System.out.print("Enter Date (yyyymmdd): ");
	            String date = sc.next();

	            System.out.print("Enter Status (Present/Absent/Leave): ");
	            String status = sc.next();
	            ps.setInt(1, empId);
	            ps.setString(2, date);
	            ps.setString(3, status);

	            ps.executeUpdate();
	            System.out.println("Attendance Marked");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // View Employee  one day Attendance
	    public void viewAttendanceByEmployee() {

	        try {
	            String sql = "SELECT a_date, a_status FROM attendence WHERE e_id=? and a_date=?";
	            PreparedStatement ps = cn.prepareStatement(sql);
	            
	            System.out.print("Enter eid : ");
	            int eid = sc.nextInt();
	            
				System.out.print("Enter date : ");
	            int dt = sc.nextInt();
	            ps.setInt(1, eid);
	            ps.setInt(2, dt);

	            ResultSet rs = ps.executeQuery();

	            System.out.println("Date\t\tStatus");
	            while (rs.next()) {
	                System.out.println(rs.getDate("a_date") + "\t" + rs.getString("a_status"));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // View All Attendance
	    public void viewAllAttendance() {

	        try {

	            String sql = "SELECT e_id, a_date, a_status FROM attendence where e_id=?";
	            PreparedStatement ps = cn.prepareStatement(sql);

				System.out.print("Enter Employee Id : ");
	            int eid = sc.nextInt();

	            ps.setInt(1, eid);
	            
	            ResultSet rs = ps.executeQuery();

	            System.out.println("EmpID\tDate\t\tStatus");
	            while (rs.next()) {
	                System.out.println(
	                        rs.getInt("e_id") + "\t" +
	                        rs.getDate("a_date") + "\t" +
	                        rs.getString("a_status")
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

