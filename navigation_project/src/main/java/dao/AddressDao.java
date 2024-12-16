package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.LoginBeans;

public class AddressDao {
	public String getAddress(LoginBeans login)  {
		  String address = "";
	        
	        // Initialize connection objects
			Connection connection = null;
		    PreparedStatement ps = null;
		    ResultSet resultSet = null;
	        
	        String sql = "SELECT address FROM signup WHERE email = ?"; // Assuming the address is in row 1

	        try {
	        	
	        	connection = ConnectionManager.getConnection();
	            ps = connection.prepareStatement(sql);
	            
	            ps.setString(1, login.getEmail() );
	        

	            // Execute a query to fetch the address
//	            stmt = conn.createStatement();
	        	resultSet = ps.executeQuery();

	            if (resultSet.next()) {
	                address = resultSet.getString("address");
	                
//	               String emailidFromDB = resultSet.getString("email");
//	                 	
//	                 	if(login.getEmail().equals(emailidFromDB))
//	   	        	 {
//	   	        		 
//	   	        		 return resultSet.getString("address" );
//	   	        	 }
	            }
	            
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally {
		         // Close the result set, statement, and connection
		         try {
		            if (resultSet != null) {
		               resultSet.close();
		            }
		            if (ps != null) {
		            	ps.close();
		            }
		            if (connection != null) {
		               connection.close();
		            }
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
	        }
	        return address;
	    }
}
