package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.blogBeans;

public class createBlogDao
{
	public int createBlog(blogBeans blog)
	{

		String createQuery = "INSERT INTO blog (user_id, title, content , image) VALUES (?, ? , ?, ?)";
		// Declare connection and statement
		try(Connection connection = ConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(createQuery))
		{

			// Encrypt the password securely

			int index = 1;
			// Set the parameters for the PreparedStatement
			ps.setInt(index++, blog.getUserId());
			ps.setString(index++, blog.getTitle());
			ps.setString(index++, blog.getContent());
			ps.setString(index++, blog.getImage());

			System.out.println("111111111111" + ps);

			// Execute the insert statement
			return ps.executeUpdate(); // Return the number of rows affected

		}
		catch(SQLException e)
		{
			e.printStackTrace(); // For production, use proper logging here
			return 0; // Return 0 in case of failure
		}
	}

}
