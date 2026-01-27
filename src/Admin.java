
import java.sql.*;
import java.util.Scanner;

class connection extends Admin{
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
    void access() throws SQLException
    {
        
        Scanner sc = new Scanner(System.in);
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
            }
            else
            {
            System.out.println("Invalid Credentials");
            }
        }
        
    }
    public static void main(String[] args) throws Exception {
        connection e= new connection();
        e.con();
        
        e.access();
       
    }
}