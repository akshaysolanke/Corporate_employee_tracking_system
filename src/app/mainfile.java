package app;

import admin.adminfile;
import employee.emp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class mainfile {
   
    Connection cn;
    Scanner sc;
    public static void main(String[] args) throws ClassNotFoundException, SQLException,Exception {
        
        adminfile admin = new adminfile();
        dbconection dbcon = new dbconection();
        emp employee = new emp();

        System.out.println("-------CORPORATE EMPLOYEE TRACKING APP------");
        Scanner sc = new Scanner(System.in);
         int ch;
          do { 
            System.out.println("Select User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");

           ch = sc.nextInt();
            switch(ch)
            {
                case 1: dbcon.con();
                        admin.access();
                        break;

                case 2: dbcon.con();
                        employee.employeeMenu();
                        break; 

                case 3: System.out.println("Exited from project"); 
                        break; 
                
                default:System.out.println("Enter correct choice");
            }
          } while (ch!=3);
    }
}
