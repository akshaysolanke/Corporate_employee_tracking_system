package attendance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection con;

    Connection dbsetup() throws ClassNotFoundException, SQLException {

        // 1. Load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("1. Driver Loaded");

        // 2. Create the Connection
        String url = "jdbc:mysql://localhost:3306/companydb";
        String username = "root";
        String password = "";

        con = DriverManager.getConnection(url, username, password);
        System.out.println("2. Connection Created");

        return con;
    }
}
