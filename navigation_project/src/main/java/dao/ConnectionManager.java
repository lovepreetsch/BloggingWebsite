package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Constant;
//import util.Config;

public class ConnectionManager {
    	static {
		
	}
	
//	public static final String db_drivers = Config.getProperty("DB_DRIVERS"); 
//	public static final String db_port = Config.getProperty("DB_PORT");
//	public static final String hostName = Config.getProperty("DB_HOSTNAME");
//	public static final String db_UserName = Config.getProperty("DB_USERNAME");
//	public static final String db_password = Config.getProperty("DB_PASSWORD");
//	public static final String db_name = Config.getProperty("DB_NAME_LOCAL");
    	
    	public static final String db_drivers = Constant.DB_DRIVERS; 
    	public static final String DB_PORT = Constant.DB_PORT;
    	public static final String hostName = Constant.DB_HOSTNAME;
    	public static final String db_UserName = Constant.DB_USERNAME;
    	public static final String db_password = Constant.DB_PASSWORD;
    	public static final String db_name = Constant.DB_NAME_LOCAL;
    	
    	static {
    	    System.out.println("DB_DRIVERS: " + db_drivers);
    	    System.out.println("DB_PORT: " + DB_PORT);
    	    System.out.println("DB_HOSTNAME: " + hostName);
    	    System.out.println("DB_USERNAME: " + db_UserName);
    	    System.out.println("DB_PASSWORD: " + db_password);
    	    System.out.println("DB_NAME_LOCAL: " + db_name);
    	}


	public static Connection con;
	static String url;

	public static void getConn()
	{
		con = ConnectionManager.getConnection();
	}
         
   public static Connection getConnection()
   {
     
      try
      {
        // String url = "jdbc:odbc:" + "DataSource"; 
         // assuming "DataSource" is your DataSource name

         Class.forName(db_drivers);
         
         try
         {                                      
        	 con = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + DB_PORT + "/"+db_name+"?allowPublicKeyRetrieval=true&useSSL=false", db_UserName, db_password); 
//        	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/winkdoc?allowPublicKeyRetrieval=true&useSSL=false", "root", "Welcome*123"); ; 
        	 System.out.println("\u001B[1m_________________Winkdoc Database Connected__________________\u001B[0m");
         }
         
         catch (SQLException ex)
         {
            System.out.println("\u001B[1m_________________Error In Connecting Winkdoc Database__________________\u001B[0m");
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
    	  System.out.println("\u001B[1m_________________Re-Connecting With Winkdoc Database__________________\u001B[0m");
//    	  ConnectionManager.getConn();
    	  e.printStackTrace();
      }
      catch(Exception e)
      {
    	  System.out.println("\u001B[1m_________________Something Went Wrong Re-Connecting With Winkdoc Database__________________\u001B[0m");
          ConnectionManager.getConn();
          e.printStackTrace();
      }

   return con;
}
}
