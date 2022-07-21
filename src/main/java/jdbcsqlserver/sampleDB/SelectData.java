/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsqlserver.sampleDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hung
 */
public class SelectData {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:sqlserver://Localhost:1433;"
                + "databaseName=sampleDB;user=sa;password=sa";
        // Declare the JDBC objects.     
        Connection conn = null;
        try {
              // buoc 1 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            System.out.println(" Nap driver thanh cong");

            conn = DriverManager.getConnection(connectionUrl); // buoc 2   
            System.out.println("Kết nối thành công");
            //3. Thao tác với DB
            String sql = "SELECT  user_id, username, password, fullname, email FROM Users";// where username=?"; 
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                String name = result.getString(2);
                String pass = result.getString(3);
                String fullname = result.getString("fullname");
                String email = result.getString("email");

                String output = "User #%d: %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, name, pass, fullname, email));
            }

        } catch (Exception ex) {
            System.out.println("Ko kết nối được");
        }
    }

}
