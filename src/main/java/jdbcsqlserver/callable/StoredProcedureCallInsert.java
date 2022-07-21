/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsqlserver.callable;

import java.sql.*;

/**
 *
 * @author Hung
 */
public class StoredProcedureCallInsert {

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// nap driver
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }
    }

    ;
public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://Localhost:1433;"
                + "databaseName=sampleDB;user=sa;password=sa";
        // Declare the JDBC objects.     
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;// 
        try {

            conn = DriverManager.getConnection(connectionUrl); // buoc 2  
            CallableStatement statement
                    = conn.prepareCall("{call create_author(?, ?)}");
            statement.setString(1, "Bill Gates");
            statement.setString(2, "bill@microsoft.com");
            statement.execute();
            statement.close();
            System.out.println("Stored procedure called successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
