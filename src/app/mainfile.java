package app;

import admin.Adminfile;
import employee.Emp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Mainfile {
   
    private static Connection cn;
    Scanner sc;
    public static void main(String[] args) throws ClassNotFoundException, SQLException,Exception {
        
        cn = Dbconection.con();
        Adminfile admin = new Adminfile();
        Emp employee = new Emp();

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
                case 1:
                        admin.access();
                        break;

                case 2:
                        employee.userlogin();
                        break; 

                case 3: System.out.println("Exited from project"); 
                        break; 
                
                default:System.out.println("Enter correct choice");
            }
          } while (ch!=3);
        cn.close();
        System.out.println("connection closed");
    }
}
