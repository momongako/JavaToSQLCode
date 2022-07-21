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
public class StoredProcedureCallSelect {

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
            CallableStatement statement = conn.prepareCall("{call get_books(?)}");
    
            statement.setInt(1, 0); 
            boolean hadResults = statement.execute(); 
            // print headings
            System.out.println("| Title | Description | Rating |");
            System.out.println("================================"); 
            while (hadResults) {
                ResultSet resultSet = statement.getResultSet(); 
                // process result set
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    int rating = resultSet.getInt("rating");
 
                    System.out.println(
                            "| " + title + " | " + description + " | " + rating + " |");
                } 
                hadResults = statement.getMoreResults();
            } 
            statement.close(); 
        } catch (SQLException ex) {  ex.printStackTrace();  }
    }
}
