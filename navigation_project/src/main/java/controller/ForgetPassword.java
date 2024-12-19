package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

			int isUpdated = ForgetPasswordDao.forgetPassword(beans);

			if (isUpdated != 0)
			{
				response.sendRedirect("/navigation_project/Login.jsp");
			} else
			{

				HttpSession session = request.getSession();
				session.setAttribute("errorMessage", "Your entered email is incorrect.");
				System.out.println("Your entered email is incorrect.");
				response.sendRedirect(request.getContextPath() + "/ForgetPassword.jsp");

			}
		} catch (Exception e)
		{
			e.printStackTrace();
//			request.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
//			System.out.println("An unexpected error occurred. Please try again later.");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/ForgetPassword.jsp");
//			dispatcher.forward(request, response);
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
			System.out.println("An unexpected error occurred. Please try again later.");
			response.sendRedirect(request.getContextPath() + "/ForgetPassword.jsp");
		}
	}

}
