
import java.sql.*;
import java.util.*;

class connection extends Admin{
   
    //Connection is Build Here
    void con() throws ClassNotFoundException, SQLException
    {
            //class loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("1.class loaded");

            //connection establish
            String url = "jdbc:mysql://localhost:3306/cet_project";
            String username="root";
            String password="";
            cn = DriverManager.getConnection(url,username,password);
            System.out.println("2.Connection Established");

            //Create Statement
            st = cn.createStatement();
            System.out.println("3.Statement created");

    }
}


public class Admin {

    Connection cn;
    Statement st;
    Scanner sc;

    //Admin Access logic
    void access() throws SQLException,ClassNotFoundException
    {
        
        sc = new Scanner(System.in);
        String query = "select username, passwords from admin";
        ResultSet rs = st.executeQuery(query);
 
        System.out.println("Enter username");
        String uname=sc.next();
        System.out.println("Enter password");
        String upass= sc.next();

        while(rs.next())
       {
            String aname= rs.getString("username");
            String apass= rs.getString("passwords");
            if(uname.equals(aname) && upass.equals(apass))
            {
                System.out.println("login successful");
                operations();
            }
            else
            {
            System.out.println("Invalid Credentials");
            }
        }
        
    }

    //All operations logic in switch case
    void operations()throws SQLException
    {   
      int ch;
        do { 
            System.out.println("\n-------Admin Operations-------");
            System.out.println("1.Add employee");
            System.out.println("2.delete employee");
            System.out.println("3.update employee");//dept,proj,atten
            System.out.println("4.Assign project to Employee");
            System.out.println("5.Assign Department to Employee");
            System.out.println("6.View Reports");
            System.out.println("7.Add New Department");
            System.out.println("8.Add New Project");
            System.out.println("9.logout");

            System.out.println("Enter your choice");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1 :addemp();
                        break;
                case 2: delemp();
                        break;
                case 3: updateemp();
                        break;
                case 4: assignproj();
                        break;
                case 5: assigndept();
                        break;
                case 6: viewrepo();
                        break;
                case 7:adddept();
                        break;
                case 8:addproject();
                        break;                      
                case 9:logout();
                        break;
                default:System.out.println("Enter valid choice");                                                   
            }
        } while (ch!=9);
    }

    //Add new Employees Logic
    void addemp() throws SQLException
    {   
        String insert = "insert into employee values(?,?,?,?,?,?)";
        PreparedStatement ps = cn.prepareStatement(insert);
        System.out.println("Enter no of employees to add");
        int n = sc.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter "+(i+1)+" Employee details: ");
            System.out.println("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.println("Enter Employee Name: ");
            String name = sc.next();

            System.out.println("Enter Employee Email: ");
            String email = sc.next();

            System.out.println("Enter Employee Role: ");
            String  role = sc.next();

            System.out.println("Enter Employee Project ID: ");
            int pid = sc.nextInt();

            System.out.println("Enter Employee Department ID: ");
            int did = sc.nextInt();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, role);
            ps.setInt(5, pid);
            ps.setInt(6, did);
            ps.executeUpdate();

        }
        System.out.println("Employee Added..!\n\n");
    }

    //Delete Employee Logic
    void delemp()throws SQLException
    {
        String delete="delete from employee where e_id=?";
        PreparedStatement ps = cn.prepareStatement(delete);
        System.out.println("Enter the Employee Id for delete employee record");
        int id = sc.nextInt();
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Record deleted...!");

    }

    //Update Employee Logic 
    void updateemp()throws SQLException
    {
        int choices;
        do { 
        System.out.println("1.update project");
        System.out.println("2.update department");
        System.out.println("3.Exit from update");
        System.out.println("Enter your choice");
        choices = sc.nextInt();
            switch(choices)
            {
                case 1:assignproj();
                        break;
                case 2:assigndept();
                        break;
                case 3:System.out.println("exited from upadate");        
                default:System.out.println("Enter correct choice");                
            }
        } while (choices!=3);
    }

    //Assign projects to Employee Logic
    void assignproj()throws SQLException
    {
        
        String project = "update employee set p_id = ? where e_id = ?";
        PreparedStatement ps = cn.prepareStatement(project);
        System.out.println("Enter project Id for Assign");
        int pid = sc.nextInt();
        System.out.println("Enter Employee Id for Assign");
        int eid = sc.nextInt();
        ps.setInt(1, pid);
        ps.setInt(2, eid);
        ps.executeUpdate();
        System.out.println("Project Assigned..!");

    }

    //Assign Departments to Employee logic
    void assigndept()throws SQLException
    {
        String project = "update employee set d_id = ? where e_id = ?";
        PreparedStatement ps = cn.prepareStatement(project);
        System.out.println("Enter Department Id for Assign");
        int did = sc.nextInt();
        System.out.println("Enter Employee Id for Assign");
        int eid = sc.nextInt();
        ps.setInt(1, did);
        ps.setInt(2, eid);
        ps.executeUpdate();
        System.out.println("Employee Department Updated..!");

    }

    //View Reports logic
    void viewrepo()throws SQLException
    {
        int ch;
        do { 
            System.out.println("\n-------View Reports-------");
            System.out.println("1.Track Employee Attendance");
            System.out.println("2.Track Project Report");
            System.out.println("3.Exit from View Reports");
            System.out.println("Enter your choice");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1:trackattendance();
                        break;
                case 2:trackproject();
                        break;
                default:System.out.println("Enter Valid choice");                
            }
        } while (ch!=3);
    }

    //Track Employee Attendance
    void trackattendance()throws SQLException
    {
        System.out.println("Enter Employee Id For Track Attendance: ");
        int id = sc.nextInt();
        String sql = "select * from attendence where e_id = ?";
        PreparedStatement ps =cn.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        System.out.println("----Attendance details of Employee Id: "+id+"----");
        System.out.println();
        System.out.println("E_ID : A_DATE : A_STATUS");
        while(rs.next())
        {
            System.out.println(rs.getInt("e_id")+" : "+rs.getDate("a_date")+" : "+rs.getString("a_status"));
        }
        
    }

    //Track Project Details
    void trackproject()throws SQLException
    {
        System.out.println("Enter Project Id For Track Project Status: ");
        int id = sc.nextInt();
        String sql = "select * from project where p_id = ?";
        PreparedStatement ps =cn.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        System.out.println("----Project details of Project Id: "+id+"----");
        System.out.println();
        System.out.println("P_ID :------- P_NAME -------: START_DATE : P_STATUS : END_DATE");
        while(rs.next())
        {
            System.out.println(rs.getInt("p_id")+" : "+rs.getString("pname")+" : "+rs.getDate("startdt")+" : "+rs.getString("p_status")+" : "+rs.getDate("enddt"));
        }
        System.out.println("--------------------------------------------------------------------");
    }

    //Add Department Logic
    void adddept()throws SQLException
    {
        String adddept = "insert into department values(?,?,?)";
        PreparedStatement ps = cn.prepareStatement(adddept);
        System.out.println("Enter Department Id: ");
        int id = sc.nextInt();
        System.out.println("Enter Department Name: ");
        String name = sc.next();
        System.out.println("Enter Department Head Name: ");
        String dname = sc.next();
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, dname);
        ps.executeUpdate();
        System.out.println("department Added..!");

    }

    //Add project logic
    void addproject()throws SQLException
    {
    String sql = "insert into project (p_id, pname, startdt, p_status, enddt) values (?,?,?,?,?)";
    PreparedStatement ps = cn.prepareStatement(sql);

    System.out.print("Enter Project Id: ");
    int id = sc.nextInt();

    System.out.print("Enter Project Name: ");
    String name = sc.next();

    System.out.print("Enter Project Start Date (yyyymmdd): ");
    int sdate = sc.nextInt();

    System.out.print("Enter Project Status: ");
    String status = sc.next();

    System.out.print("Enter Project End Date (yyyymmdd): ");
    int edate = sc.nextInt();

    ps.setInt(1, id);
    ps.setString(2, name);
    ps.setInt(3, sdate);
    ps.setString(4, status);
    ps.setInt(5, edate);
        
        ps.executeUpdate();
        System.out.println("New Project Added..!");
    }

    //logout 
    void logout()throws SQLException
    {   
        System.out.println("logout successful..!");   
    }
    public static void main(String[] args) throws Exception,ClassNotFoundException {
        connection e= new connection();
        e.con();
        e.access();
       
    }
}