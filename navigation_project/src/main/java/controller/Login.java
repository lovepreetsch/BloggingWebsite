package controller;

import java.io.IOException;
import java.util.HashMap;

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
			response.sendRedirect("/navigation_project/Login.jsp");
			System.out.println("No data found");
			return;
		}

		String firstname = (String) userDetails.get("firstname");
		String lastname = (String) userDetails.get("lastname");

		String fullname = firstname + " " + lastname;

		int id = (int) userDetails.get("id");

		String address = (String) userDetails.get("address");

		String mobile = (String) userDetails.get("mobile");

		System.out.println("LoginId11111111: " + id);

		// System.out.println(emailid);
		// System.out.println(password);

//		boolean result = LoginDao.login(email, password);
		if (id != 0)
		{

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
		} else
		{
			request.setAttribute("errorMessage", "Wrong Credentials");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			response.sendRedirect("/navigation_project/Login.jsp");
		}
	}
}
