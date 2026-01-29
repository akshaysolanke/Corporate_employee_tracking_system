package attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceDAO {

	    DBConnection db = new DBConnection();   // 🔗 Connection class
	    Connection con;

	    // Mark Attendance
	    public void markAttendance(int empId, String date, String status) {

	        try {
	            con = db.dbsetup();   //CONNECTION HERE

	            String sql = "INSERT INTO attendance(emp_id, att_date, status) VALUES(?,?,?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1, empId);
	            ps.setString(2, date);
	            ps.setString(3, status);

	            ps.executeUpdate();
	            System.out.println("Attendance Marked");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // View Employee Attendance
	    public void viewAttendanceByEmployee(int date) {

	        try {
	            con = db.dbsetup();   // CONNECTION HERE

	            String sql = "SELECT att_date, status FROM attendance WHERE att_date=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, date);

	            ResultSet rs = ps.executeQuery();

	            System.out.println("Date\t\tStatus");
	            while (rs.next()) {
	                System.out.println(rs.getDate(1) + "\t" + rs.getString(2));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // View All Attendance
	    public void viewAllAttendance(int empid) {

	        try {
	            con = db.dbsetup();   // CONNECTION HERE

	            String sql = "SELECT emp_id, att_date, status FROM attendance where emp_id=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, empid);
	            
	            ResultSet rs = ps.executeQuery();

	            System.out.println("EmpID\tDate\t\tStatus");
	            while (rs.next()) {
	                System.out.println(
	                        rs.getInt(1) + "\t" +
	                        rs.getDate(2) + "\t" +
	                        rs.getString(3)
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

