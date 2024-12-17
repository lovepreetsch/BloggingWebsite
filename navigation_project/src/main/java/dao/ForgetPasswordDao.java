package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.ForgetPasswordBeans;

public class ForgetPasswordDao
{
	public static int forgetPassword(ForgetPasswordBeans beans)
	{

		Connection connection = null;
		PreparedStatement stmt = null;

		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("Update ").append("signup ").append("SET ").append("password = ? ").append("where ")
				.append("id = ?  ");

		try
		{
			connection = ConnectionManager.getConnection();
			stmt = connection.prepareStatement(updateQuery.toString());

			int index = 1;

			stmt.setString(index++, beans.getPassword());
			stmt.setString(index++, beans.getUserId());

			return stmt.executeUpdate();

		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
			// TODO: handle exception
		} finally
		{
			try
			{
				if (connection != null)
				{
					connection.close();
				}
				if (stmt != null)
				{
					stmt.close();
				}

			} catch (Exception e)
			{
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
