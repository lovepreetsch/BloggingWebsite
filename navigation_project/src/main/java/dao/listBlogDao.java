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
import util.UtilityClass;

public class listBlogDao
{
	public ResponseWrapper<?> listBlog(blogBeans blog)
	{
		List<HashMap<String, Object>> list = new ArrayList<>();
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		String listQuery = "SELECT * FROM blog WHERE user_id = " + blog.getUserId();

		if(!UtilityClass.isNull(blog.getSearchKeyword()))
		{
			listQuery += " AND title LIKE '%" + blog.getSearchKeyword() + "%'";
		}

		listQuery += " ORDER BY created_on DESC ";

		if(!UtilityClass.isNull(blog.getPage()) && !UtilityClass.isNull(blog.getCount()))
		{
			int count = blog.getCount();
			int page = (blog.getPage() - 1) * count;

			listQuery += "LIMIT " + page + ", " + count;
		}

		try
		{
			stmt = ConnectionManager.getConnection().prepareStatement(listQuery);

			//			stmt.setInt(1, blog.getUserId());

			System.out.println("listBlog: " + stmt);
			resultSet = stmt.executeQuery();

			if(!resultSet.isBeforeFirst())
			{
				// The ResultSet is empty
				return new ResponseWrapper<String>(0, 404, "No details found");
			}
			else
			{
				while(resultSet.next())
				{

					HashMap<String, Object> tempObj = new HashMap<>();

					tempObj.put("title", resultSet.getObject("title"));
					tempObj.put("content", resultSet.getObject("content"));
					tempObj.put("userId", resultSet.getObject("user_id"));
					tempObj.put("image", resultSet.getObject("image"));

					String utcTimestamp = resultSet.getString("created_on");
					String userTimeZone = "Asia/Kolkata"; // Replace with dynamic timezone

					//					System.out.println("utcTimestamp: " + utcTimestamp);

					String formattedDate = DateUtils.convertToUserTimeZone(utcTimestamp, userTimeZone);
					//					System.out.println("Formatted Date: " + formattedDate);
					tempObj.put("createdOn", formattedDate);

					list.add(tempObj);
				}
				return new ResponseWrapper<>(1, list, 200, "Role details");
			}

		}
		catch(SQLException ex)
		{
			ConnectionManager.getConn();
			ex.printStackTrace();
			return new ResponseWrapper<>(-1, "An error occurred: " + ex.getMessage(), 500, "Internal server error");
		} finally
		{
			try
			{
				if(resultSet != null)
				{
					resultSet.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public ResponseWrapper<?> listBlogCount(blogBeans blog)
	{
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		String listQuery = "SELECT COUNT(*) AS total_count FROM blog WHERE user_id = ? ";

		if(!UtilityClass.isNull(blog.getSearchKeyword()))
		{
			listQuery += " AND title LIKE '%" + blog.getSearchKeyword() + "%'";
		}

		try
		{
			stmt = ConnectionManager.getConnection().prepareStatement(listQuery);

			stmt.setInt(1, blog.getUserId());

			System.out.println("listBlogCount: " + stmt);
			resultSet = stmt.executeQuery();

			if(!resultSet.isBeforeFirst())
			{
				// The ResultSet is empty
				return new ResponseWrapper<String>(0, 404, "No details found");
			}
			else
			{
				resultSet.next();
				int totalCount = resultSet.getInt("total_count");
				return new ResponseWrapper<>(1, totalCount, 200, "Total count");
			}

		}
		catch(SQLException ex)
		{
			ConnectionManager.getConn();
			ex.printStackTrace();
			return new ResponseWrapper<>(-1, "An error occurred: " + ex.getMessage(), 500, "Internal server error");
		} finally
		{
			try
			{
				if(resultSet != null)
				{
					resultSet.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static List<String> getBlogSuggestions(blogBeans blog)
	{
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		List<String> suggestions = new ArrayList<>();
		String sql = "SELECT DISTINCT title, blog.created_on FROM blog WHERE title LIKE ? ORDER BY created_on DESC LIMIT 10 ";

		try
		{
			stmt = ConnectionManager.getConnection().prepareStatement(sql);

			stmt.setString(1, "%" + blog.getSearchKeyword() + "%");

			System.out.println("listBlog: " + stmt);
			resultSet = stmt.executeQuery();

			while(resultSet.next())
			{
				suggestions.add(resultSet.getString("title"));
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if(resultSet != null)
				{
					resultSet.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		return suggestions;
	}

}