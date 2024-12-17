package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.ResponseWrapper;
import beans.blogBeans;
import util.DateUtils;

public class listBlogDao
{
	public ResponseWrapper<?> listBlog(blogBeans blog)
	{
		List<HashMap<String, Object>> list = new ArrayList<>();
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		String listQuery = "SELECT * FROM blog WHERE user_id = ? ORDER BY created_on DESC";

		try
		{
			stmt = ConnectionManager.getConnection().prepareStatement(listQuery);

			stmt.setInt(1, blog.getUserId());

			System.out.println("listBlog: " + stmt);
			resultSet = stmt.executeQuery();

			if (!resultSet.isBeforeFirst())
			{
				// The ResultSet is empty
				return new ResponseWrapper<String>(0, 404, "No details found");
			} else
			{
				while (resultSet.next())
				{

					HashMap<String, Object> tempObj = new HashMap<>();

					tempObj.put("title", resultSet.getObject("title"));
					tempObj.put("content", resultSet.getObject("content"));
					tempObj.put("userId", resultSet.getObject("user_id"));

					String utcTimestamp = resultSet.getString("created_on");
					String userTimeZone = "Asia/Kolkata"; // Replace with dynamic timezone

					System.out.println("utcTimestamp: " + utcTimestamp);

					String formattedDate = DateUtils.convertToUserTimeZone(utcTimestamp, userTimeZone);
					System.out.println("Formatted Date: " + formattedDate);
					tempObj.put("createdOn", formattedDate);

					list.add(tempObj);
				}
				return new ResponseWrapper<>(1, list, 200, "Role details");
			}

		} catch (SQLException ex)
		{
			ConnectionManager.getConn();
			ex.printStackTrace();
			return new ResponseWrapper<>(-1, "An error occurred: " + ex.getMessage(), 500, "Internal server error");
		} finally
		{
			try
			{
				if (resultSet != null)
				{
					resultSet.close();
				}
				if (stmt != null)
				{
					stmt.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}