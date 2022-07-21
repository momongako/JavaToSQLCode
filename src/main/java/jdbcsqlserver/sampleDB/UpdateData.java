/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsqlserver.sampleDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hung
 */
public class UpdateData {

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

            String sql = "UPDATE Users SET password=?, fullname=?,"
                    + " email=? WHERE username=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "123456!");
            statement.setString(2, "William Henry");
            statement.setString(3, "Henry@oracle.com");
            statement.setString(4, "bill");//username...

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }

}
