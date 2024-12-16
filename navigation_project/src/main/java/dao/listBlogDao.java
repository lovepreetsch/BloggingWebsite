package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import beans.ResponseWrapper;
import beans.blogBeans;

public class listBlogDao {
	public ResponseWrapper<?> listBlog(blogBeans blog) 
	{
		List<HashMap<String,Object>> list = new ArrayList<>();
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		String listQuery = "SELECT * FROM blog WHERE user_id = ?";
		
		
		try 
		{
			stmt = ConnectionManager.getConnection().prepareStatement(listQuery);
			
			stmt.setInt(1, blog.getUserId());
			
			System.out.println("listBlog: "+stmt);
			resultSet = stmt.executeQuery();
			
			if (!resultSet.isBeforeFirst()) 
            {    
                // The ResultSet is empty
                return new ResponseWrapper<String>(0, 404, "No details found");
            } 
            else 
            {
				while (resultSet.next()) {

					HashMap<String,Object> tempObj = new HashMap<>();

					tempObj.put("title", resultSet.getObject("title"));
					tempObj.put("content", resultSet.getObject("content"));
					tempObj.put("userId", resultSet.getObject("user_id"));
//					tempObj.put("roleTitle", resultSet.getObject("role_title"));
//					tempObj.put("deletedStatus", resultSet.getBoolean("deleted_status"));
//					tempObj.put("activeStatus", resultSet.getBoolean("active_status"));

					list.add(tempObj);
				}
				 return new ResponseWrapper<>(1, list, 200, "Role details");
            }
			
		} 
		catch (SQLException ex) 
		{
			ConnectionManager.getConn();
			ex.printStackTrace();
	        return new ResponseWrapper<>(-1, "An error occurred: " + ex.getMessage(), 500, "Internal server error");
		} 
		finally 
		{
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}