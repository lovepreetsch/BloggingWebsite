package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.ForgetPasswordBeans;
import util.Encryption;

public class ForgetPasswordDao
{
	public static int forgetPassword(ForgetPasswordBeans beans)
	{

		Connection connection = null;
		PreparedStatement stmt = null;

		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("Update ").append("signup ").append("SET ").append("password = ? ").append("where ")
				.append("email = ?");

//		System.out.println("UpdataeQuery" + updateQuery);

		try
		{
			connection = ConnectionManager.getConnection();
			stmt = connection.prepareStatement(updateQuery.toString());

			String pass = beans.getPassword();
			final String secretKey = "secretnote";
			String encryptedPass = Encryption.encrypt(pass, secretKey);

			int index = 1;

			stmt.setString(index++, encryptedPass);
//			stmt.setString(index++, beans.getUserId());
			stmt.setString(index++, beans.getEmail());

			System.out.println("password: " + beans.getPassword());
			System.out.println("email: " + beans.getEmail());

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
