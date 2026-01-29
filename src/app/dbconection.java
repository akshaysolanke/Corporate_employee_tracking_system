package app;
import java.sql.*;

public class dbconection  {
    Connection cn;
    Statement st;
     public void con() throws ClassNotFoundException, SQLException
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
