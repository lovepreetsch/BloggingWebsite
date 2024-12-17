package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ForgetPasswordBeans;
import dao.ForgetPasswordDao;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		ForgetPasswordBeans beans = new ForgetPasswordBeans();
		beans.setEmail(email);
		beans.setPassword(password);

		try
		{
			// Check if the email exists and the password is updated
			int isUpdated = ForgetPasswordDao.forgetPassword(beans);

			if (isUpdated != 0)
			{
				// Password updated successfully, redirect to login page
				response.sendRedirect("/navigation-project/Login.jsp");
			} else
			{
				// Email not found, forward back to the forgot password page with an error
				// message
				request.setAttribute("errorMessage", "Your entered email is incorrect.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ForgetPassword.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServletException e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ForgetPassword.jsp");
			dispatcher.forward(request, response);
		}
	}

}

//}
