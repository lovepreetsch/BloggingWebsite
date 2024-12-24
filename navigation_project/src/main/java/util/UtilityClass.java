package util;

import java.security.SecureRandom;
import java.util.List;

public class UtilityClass
{
	public static String generateRandomNumber(int digits)
	{
		SecureRandom random = new SecureRandom();
		//		int digits = 9;
		StringBuilder sb = new StringBuilder(digits);
		for(int i = 0; i < digits; i++)
		{
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	public static boolean isNull(Object value)
	{
		if(value == null)
		{
			//	    	LoggerUtil.log("2 is Null TRUE");
			return true;
		}
		if(value instanceof String || value instanceof Integer)
		{
			//	    	LoggerUtil.log("2 is Null elseif TRUE");
			String strValue = String.valueOf(value);
			return(strValue.equalsIgnoreCase("null") || strValue.trim().isEmpty());
		}
		//	    if (value instanceof JSONArray ) {
		//	        JSONArray strValue = (JSONArray) value;
		//	        return strValue.isEmpty();
		//	    }
		if(value instanceof List)
		{
			List<?> list = (List<?>) value;
			return list.isEmpty();
		}
		if(value.getClass().isArray())
		{
			return java.lang.reflect.Array.getLength(value) == 0;
		}
		// Add more specific checks for other datatypes if needed
		return false; // If the object is of a different datatype, it is not considered null.
	}
}
