package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBeans;
//import dao.AuthenticatePerson;
import dao.LoginDao;

/**
 * Servlet implementation class Signup
 */
@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet
{

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		System.out.println("Login user:");
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		LoginBeans login = new LoginBeans();
		login.setEmail(email);
		login.setPassword(password);

		// Get the user's name if login is successful
		HashMap<String, Object> userDetails = LoginDao.login(login);

		if (userDetails == null)
		{
//			response.sendRedirect("/navigation_project/Login.jsp");
//			System.out.println("No data found");
//			return;
			request.setAttribute("errorMessage", "You are not registered");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("/navigation_project/Login.jsp");
			System.out.println("You are not registered");
		} else
		{

			String status = (String) userDetails.get("status");

			if ("email_not_found".equals(status))
			{
				HttpSession session = request.getSession();
				session.setAttribute("errorMessage", "Email not found. Please register!");
				response.sendRedirect(request.getContextPath() + "/Login.jsp");

			} else if ("incorrect_password".equals(status))
			{
				HttpSession session = request.getSession();
				session.setAttribute("errorMessage", "Incorrect Password. Please try again!");
				response.sendRedirect(request.getContextPath() + "/Login.jsp");
			} else if ("success".equals(status))
			{

				String firstname = (String) userDetails.get("firstname");
				String lastname = (String) userDetails.get("lastname");

				String fullname = firstname + " " + lastname;

				int id = (int) userDetails.get("id");

				String address = (String) userDetails.get("address");

				String mobile = (String) userDetails.get("mobile");

				System.out.println("LoginId11111111: " + id);
				System.out.println("LoginId2222222222: " + id);

				HttpSession session = request.getSession();
				session.setAttribute("fullname", fullname);

				session.setAttribute("userId", id);

				session.setAttribute("address", address);

				session.setAttribute("mobile", mobile);

				session.setAttribute("email", email);

//		        session.setAttribute("email", email);

//		RequestDispatcher dispatcher = request.getRequestDispatcher("/navigation_project.jsp");
				response.sendRedirect("/navigation_project/navigation_project.jsp");
			}

		}
	}
}
