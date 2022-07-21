/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbcsqlserver.sampleDB;


import java.sql.*;
public class ConnectToSQLServer {
    public static void main(String[] args) {
      // Create a variable for the connection string.
      String connectionUrl = "jdbc:sqlserver://Localhost:1433;" +
         "databaseName=Test;user=sa;password=sa";
      // Declare the JDBC objects.     
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;// 
      try {
         // Establish the connection.
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// buoc 1
         con = DriverManager.getConnection(connectionUrl); // buoc 2  
         stmt = con.createStatement();
         
         String SQL = "SELECT TOP 10 * FROM Person.Contact";
//         rs = stmt.executeQuery(SQL);//
//
//         // Iterate through the data in the result set and display it.
//         while (rs.next()) {
//            System.out.println(rs.getString(4) + " " + rs.getString(6));
//         }
//         // cho bam phim

        

System.out.println("======================");
         //
 //          createTable(con);
         insertData(con,"BBA",12.1);
         selectAll(con);
         updateTable(con);
         selectAll(con);
         prepareStatement(con);
         selectAll(con);
         prepareStatementSelect(con);
      }

      // Handle any errors that may have occurred.
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}
         if (con != null) try { con.close(); } catch(Exception e) {}
      }


   }
   public static void createTable(Connection conn) {
	String sqlQuery = "CREATE TABLE PRICELIST1( NAME VARCHAR(20), PRICE Float);";
	//String sqlQuery = "create table SerialTest( name image, viewname VARCHAR(30) )";
    if (conn != null){
		try{
			Statement st = conn.createStatement();
			st.execute(sqlQuery);
			st.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
public static void insertData(Connection conn, String name, double val){
	String insertQuery = "INSERT INTO PRICELIST1 VALUES('"
            + name + "'," + val + ");";
	if (conn != null){
		try{
			Statement st = conn.createStatement();
			int d = st.executeUpdate(insertQuery);
			System.out.println("So dong duoc cap nhat: " + d);
			st.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
public static void selectAll(Connection conn){
	String selectQuery = "SELECT * FROM PRICELIST1;";
	if (conn != null){
	try{
		Statement st =
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(selectQuery);
		System.out.println("NAME\t\tPRICE");

		while (rs.next()){
			String name = rs.getString("NAME");
			double val = rs.getDouble("PRICE");
			System.out.println(name + "\t\t" + val);
		}
		st.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
	}
}
public static void updateTable(Connection conn){
	String selectQuery = "SELECT * FROM PRICELIST1;";
	if (conn != null){
		try{
			Statement st =
		conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()){
               if (rs.getString("NAME").equalsIgnoreCase("AAA")){
                    rs.updateDouble("PRICE", 1.5);
                    rs.updateRow();
            }
            }
			st.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

public static void prepareStatement(Connection conn ){
	String insertQuery = "INSERT INTO PRICELIST1 VALUES(?,?);";
	if (conn != null){
	try{
		PreparedStatement prest = conn.prepareStatement(insertQuery);

        prest.setString(1, "Biscuit");
		prest.setDouble(2, 1.2);
		prest.executeUpdate();

		prest.setString(1, "Pen");
		prest.setDouble(2, 0.5);
		prest.executeUpdate();

        prest.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
	}
}

public static void prepareStatementSelect(Connection conn ){
	String selectQuery = "Select * From PRICELIST1 where Price>?";
	if (conn != null){
	try{
		PreparedStatement prest = conn.prepareStatement(selectQuery);


		prest.setDouble(1,3.2);

		ResultSet rs= prest.executeQuery();
        while (rs.next()){
			String name = rs.getString("NAME");
			double val = rs.getDouble("PRICE");
			System.out.println(name + "\t\t" + val);
		}
        prest.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
	}
}
}