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
public class InsertData {

    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://Localhost:1433;"
                + "databaseName=sampleDB;user=sa;password=sa";
        // Declare the JDBC objects.     
        Connection conn = null;
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// buoc 1
            conn = DriverManager.getConnection(connectionUrl); // buoc 2  

            String sql = "INSERT INTO Users (username, password, fullname, email)"
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "henry");
            statement.setString(2, "secretpass");
            statement.setString(3, "Henry");
            statement.setString(4, "Henry@microsoft.com");
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

            statement.setString(1, "bill1");
            statement.setString(2, "secretpass 1");
            statement.setString(3, "Bill Gates 1");
            statement.setString(4, "bill.gates@microsoft.com1");
            rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
