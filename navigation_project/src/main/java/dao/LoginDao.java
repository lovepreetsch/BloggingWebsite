package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import beans.LoginBeans;
import util.Encryption;

public class LoginDao 
{
	public static HashMap<String , Object>  login(LoginBeans login)
	{
    
   	 HashMap<String , Object> obj = new HashMap<String, Object>();
   	 
		 Connection connection = null;
		    PreparedStatement ps = null;
	    ResultSet resultSet = null;
		String selectQuery = "SELECT * FROM signup WHERE email= ? AND password= ?";
        // Declare connection and statement
        try 
//        (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement ps = connection.prepareStatement(selectQuery)) 
        {
        connection = ConnectionManager.getConnection();
        ps = connection.prepareStatement(selectQuery);
        	
	         
	         //encrypting password
      	     String pass = login.getPassword();
      	     final String secretKey = "secretnote";
      	     String encryptedPass = Encryption.encrypt(pass, secretKey);
      	     
      	     ps.setString(1, login.getEmail());
      	     ps.setString(2, encryptedPass);
	         
	         // Execute the query
	         resultSet = ps.executeQuery();

	         if(resultSet.next())
	         {
	        	 
//	        	 HashMap<String , Object> obj = new HashMap<String, Object>();
	        	 
	        	 obj.put("id", resultSet.getObject("id"));
	        	 obj.put("firstname", resultSet.getObject("firstname" ));
	        	 obj.put("lastname" , resultSet.getObject("lastname" ));
	        	 
	        	 obj.put("address", resultSet.getObject("address"));
	        	 obj.put("mobile", resultSet.getObject("contact"));
	        	 
	        	 return obj;
	        	 
	        	 
	        	
//	        	 String emailidFromDB = resultSet.getString("email");
//	        	 String passwordFromDB = resultSet.getString("password");
	        	 
//	        	 if(email.equals(emailidFromDB) && encryptedPass.equals(passwordFromDB))
//	        	 {
	        		 
//	        		 return resultSet.getString("firstname" ) + " " + resultSet.getString("lastname" );
//	        	 }
	         }
	         else {
	        	 return null;
	         }

        } catch (SQLException e) {
            e.printStackTrace(); // For production, use proper logging here
            return null;
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
    }
	}