package util;

public class Constant
{

	//	//Database configuration
	//	public static final String DB_HOSTNAME = "localhost";
	//	public static final String DB_PORT = "3306";
	//	public static final String DB_USERNAME = "root";
	//	public static final String DB_PASSWORD = "root";
	//	public static final String DB_DRIVERS = "com.mysql.cj.jdbc.Driver";
	////	
	//	//Local server Database details
	//	public static final String DB_NAME_LOCAL = "demo";

	// Database configuration
	public static String DB_HOSTNAME = util.Config.getProperty("DB_HOSTNAME");
	public static String DB_PORT = util.Config.getProperty("DB_PORT");
	public static String DB_USERNAME = util.Config.getProperty("DB_USERNAME");
	public static String DB_PASSWORD = util.Config.getProperty("DB_PASSWORD");
	public static String DB_DRIVERS = util.Config.getProperty("DB_DRIVERS");
	//	
	// Local server Database details
	public static final String DB_NAME_LOCAL = Config.getProperty("DB_NAME_LOCAL");

	//	Image folder path
	public static final String PROJECT_PATH = Config.getProperty("PROJECT_PATH");

	public static final String NAVIGATION_PROJECT_FOLDER_PATH = Config.getProperty("NAVIGATION_PROJECT_FOLDER_PATH");

	// ProfilePicture and other Images Thumbnail height and width
	public static final int THUMBNAIL_WIDTH = Integer.valueOf(Config.getProperty("THUMBNAIL_WIDTH"));
	public static final int THUMBNAIL_HEIGHT = Integer.valueOf(Config.getProperty("THUMBNAIL_HEIGHT"));

	public static final String ALLOWED_IMAGE_EXCETIONS = Config.getProperty("ALLOWED_IMAGE_EXCETIONS[]");
	public static final String ALLOWED_DOCUMENT_EXCETIONS = Config.getProperty("ALLOWED_DOCUMENT_EXCETIONS[]");
	public static final String ALLOWED_IMAGE_AND_DOCUMENT_EXCETIONS = Config
			.getProperty("ALLOWED_IMAGE_AND_DOCUMENT_EXCETIONS[]");

	public static final int MAXIMUM_IMAGE_SIZE = Integer.valueOf(Config.getProperty("MAXIMUM_IMAGE_SIZE")); // file allowed up to 5MB
	public static final int MAXIMUM_DOCUMENT_SIZE = Integer.valueOf(Config.getProperty("MAXIMUM_DOCUMENT_SIZE")); // file allowed up to 10MB

}