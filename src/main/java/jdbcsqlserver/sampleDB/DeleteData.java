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
public class DeleteData {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:sqlserver://Localhost:1433;"
                + "databaseName=sampleDB;user=sa;password=sa";
        // Declare the JDBC objects.     
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;// 
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// buoc 1
            con = DriverManager.getConnection(connectionUrl); // buoc 2  
            stmt = con.createStatement();
            String sql = "DELETE FROM Users WHERE username=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "bill");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e) {
            System.err.print("ClassNotFoundException: ");
        }

    }

}
