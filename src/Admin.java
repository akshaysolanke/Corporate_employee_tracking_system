
import java.sql.*;
import java.util.Scanner;

class connection extends Admin{
   
    void con() throws ClassNotFoundException, SQLException
    {
            //class loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("1.class loaded");

            //connection establish
            String url = "jdbc:mysql://localhost:3307/cet_project";
            String username="root";
            String password="root";
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
    void operations()throws SQLException
    {   
      int ch;
        do { 
            System.out.println("1.Add employee");
            System.out.println("2.delete employee");
            System.out.println("3.update employee");//dept,proj,atten
            System.out.println("4.Assign project to Employee");
            System.out.println("5.Assign Department to Employee");
            System.out.println("6.View Reports");
            System.out.println("7.logout");

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
                case 7:logout();
                        break;
                default:System.out.println("Enter valid choice");                                                   
            }
        } while (ch!=7);
    }
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
    void delemp()
    {
        System.out.println("delete");
    }
    void updateemp()
    {
        System.out.println("update");
    }
    void assignproj()
    {
        System.out.println("proje");
    }
    void assigndept()
    {
        System.out.println("dept");
    }
    void viewrepo()
    {
        System.out.println("report");
    }
    void logout()
    {
        System.out.println("logout successful..!");
    }
    public static void main(String[] args) throws Exception,ClassNotFoundException {
        connection e= new connection();
        e.con();
        e.access();
       
    }
}