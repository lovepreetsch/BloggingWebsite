package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import beans.LoginBeans;
import util.Encryption;

public class LoginDao
{
	public static HashMap<String, Object> login(LoginBeans login)
	{

		HashMap<String, Object> obj = new HashMap<String, Object>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		String emailCheckQuery = "SELECT password FROM signup where email = ?";
		String selectQuery = "SELECT * FROM signup WHERE email= ? AND password= ?";

		try

		{

			connection = ConnectionManager.getConnection();

			// For query 1st
			ps = connection.prepareStatement(emailCheckQuery);

			ps.setString(1, login.getEmail());

			resultSet = ps.executeQuery();

			if (!resultSet.next())
			{
				obj.put("status", "email_not_found");
				return obj;
			}

			// Close resources
			ps.close();
			resultSet.close();

			// For query 2nd
			ps = connection.prepareStatement(selectQuery);

			String pass = login.getPassword();
			final String secretKey = "secretnote";
			String encryptedPass = Encryption.encrypt(pass, secretKey);

			ps.setString(1, login.getEmail());
			ps.setString(2, encryptedPass);

			System.out.println("stmt: " + ps);

			resultSet = ps.executeQuery();

			if (resultSet.next())
			{

				obj.put("id", resultSet.getObject("id"));
				obj.put("firstname", resultSet.getObject("firstname"));
				obj.put("lastname", resultSet.getObject("lastname"));

				obj.put("address", resultSet.getObject("address"));
				obj.put("mobile", resultSet.getObject("contact"));
				obj.put("status", "success");

				return obj;

			} else
			{
				obj.put("status", "incorrect_password");
				return obj;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{

			try
			{
				if (resultSet != null)
				{
					resultSet.close();
				}
				if (ps != null)
				{
					ps.close();
				}
				if (connection != null)
				{
					connection.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}