package com;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class funds {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertFunds(String propID, String amount, String description)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into funds(`propID`,`amount`,`description`)"
			 + " values (?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
				
				preparedStmt.setInt(1,Integer.parseInt(propID));
				preparedStmt.setDouble(3, Double.parseDouble(amount));
				preparedStmt.setString(4, description);
				
			// execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 String newFunds = readFunds();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newFunds + "\"}" ; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error While Inserting the Funds.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readFunds()
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for reading.";
		 }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Proposal ID</th><th>Amount</th>" + "<th>Description</th></tr>";
		 
		 String query = "select * from funds";
		
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 	String fundID = Integer.toString(rs.getInt("fundID"));
			 	String propID = Integer.toString(rs.getInt("propID"));
				String amount = Double.toString(rs.getDouble("amount"));
				String description = rs.getString("description");
				
				
		 // Add into the html table;
		 
				output += "<tr><td>" + fundID + "</td>";
				output += "<td>" + propID + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + description + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'data-productid='" + fundID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-productid='" + fundID + "'></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 } 
		
		catch (Exception e)
		 {
		 output = "Error while reading the product.";
		 System.err.println(e.getMessage());
		 }
		return output;
		}

	
	public String updateFunds(String fundID, String propID , String amount, String description)

	
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE funds SET propID=?,amount=?,description=? WHERE fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(propID));  
		 preparedStmt.setDouble(2, Double.parseDouble(amount)); 
		 preparedStmt.setString(3, description);  
		 preparedStmt.setInt(4, Integer.parseInt(fundID)); 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newFunds = readFunds();
		 output =  "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}" ; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error While Updating the Funds.\"}";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 }
	
		public String deleteFunds(String fundID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from funds where fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fundID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newFunds = readFunds();
		 output =  "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}" ; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error While Deleting the Funds.\"}";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 
		 }
		 return output;
		 }

}
