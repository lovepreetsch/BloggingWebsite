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
import util.UtilityClass;

/**
 * Servlet implementation class Signup
 */
@SuppressWarnings("serial")
@WebServlet("/Login")
//@WebServlet("/api/login")
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

		System.out.println("Server side password: " + password);

		if(UtilityClass.isNull(password))
		{
			System.out.println("inside password null");
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "Password can not be empty!");
			//			response.sendRedirect("/navigation_project/Login.jsp");
			//			response.sendRedirect("/navigation_project/navigation_project.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		LoginBeans login = new LoginBeans();
		login.setEmail(email);
		login.setPassword(password);

		// Get the user's name if login is successful
		HashMap<String, Object> userDetails = LoginDao.login(login);

		String status = (String) userDetails.get("status");

		if("email_not_found".equals(status))
		{
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "Email not found. Please register!");
			//				response.sendRedirect(request.getContextPath() + "/Login.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if("incorrect_password".equals(status))

		{
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "Incorrect Password. Please try again!");
			//				response.sendRedirect(request.getContextPath() + "/Login.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if("success".equals(status))
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
			return;
		}

	}
}

//package controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONObject;
//
//import beans.LoginBeans;
//import dao.LoginDao;
//import util.UtilityClass;
//
///**
// * Servlet implementation class Login
// */
//@WebServlet("/api/login")
//public class Login extends HttpServlet
//{
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//
//		// Read the request body as a String
//		BufferedReader reader = request.getReader();
//		StringBuilder json = new StringBuilder();
//		String line;
//		while((line = reader.readLine()) != null)
//		{
//			json.append(line);
//		}
//
//		// Parse the JSON request body
//		JSONObject jsonRequest = new JSONObject(json.toString());
//
//		// Get email and password from the parsed JSON object
//		String email = jsonRequest.optString("email");
//		String password = jsonRequest.optString("password");
//
//		//		// Capture the email and password from the request body
//		//String email = request.getParameter("email");
//		//String password = request.getParameter("password");
//		System.out.println("Received Password: " + password + " " + email);
//		//		BufferedReader reader = request.getReader();
//		//		StringBuilder json = new StringBuilder();
//		//		String line;
//		//		while((line = reader.readLine()) != null)
//		//		{
//		//			json.append(line);
//		//		}
//		//		JSONObject requestBody = new JSONObject(json.toString());
//		//		String email = requestBody.optString("email");
//		//		String password = requestBody.optString("password");
//
//		// Retrieve user from session
//
//		//HashMap<String, Object> user = (HashMap<String, Object>) request.getSession().getAttribute("user");
//		//		System.out.println("user details retreived :" + user);
//		//		if(user == null)
//		//		{
//		//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		//			out.print(new JSONObject().put("status", "error").put("message", "User not logged in."));
//		//			return;
//		//		}
//
//		//		jsonResponse.put("status", "success");
//		//		jsonResponse.put("user", new JSONObject(user));
//		//		out.print(jsonResponse.toString());
//		//
//
//		JSONObject jsonResponse = new JSONObject();
//		if(UtilityClass.isNull(email))
//		{
//			JSONObject errorResponse = new JSONObject();
//			errorResponse.put("status", "error");
//			errorResponse.put("message", "Email cannot be empty.");
//			out.print(errorResponse.toString());
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
//
//		// Check if the password is null or empty
//		if(UtilityClass.isNull(password))
//		{
//			JSONObject errorResponse = new JSONObject();
//			errorResponse.put("status", "error");
//			errorResponse.put("message", "Password cannot be empty.");
//			out.print(errorResponse.toString());
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
//
//		// Prepare login data
//		LoginBeans login = new LoginBeans();
//		login.setEmail(email);
//		login.setPassword(password);
//
//		// Get the user's login status
//		HashMap<String, Object> userDetails = LoginDao.login(login);
//
//		String status = (String) userDetails.get("status");
//
//		// Handle different statuses
//		jsonResponse = new JSONObject();
//		if("email_not_found".equals(status))
//		{
//			jsonResponse.put("status", "error");
//			jsonResponse.put("message", "Email not found. Please register!");
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//		}
//		else if("incorrect_password".equals(status))
//		{
//			jsonResponse.put("status", "error");
//			jsonResponse.put("message", "Incorrect password. Please try again!");
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		}
//		else if("success".equals(status))
//		{
//			// Store user details in the session
//			request.getSession().setAttribute("user", userDetails);
//			request.getSession().setAttribute(	"fullname",
//												userDetails.get("firstname") + " " + userDetails.get("lastname"));
//			request.getSession().setAttribute("address", userDetails.get("address"));
//			request.getSession().setAttribute("mobile", userDetails.get("mobile"));
//			request.getSession().setAttribute("email", email);
//			request.getSession().setAttribute("id", userDetails.get("id"));
//
//			jsonResponse.put("status", "success");
//			jsonResponse.put("message", "Login successful.");
//
//			// Include user details in the response
//			jsonResponse
//					.put(	"user",
//							new JSONObject().put("id", userDetails.get("id"))
//									.put("fullname", userDetails.get("firstname") + " " + userDetails.get("lastname"))
//									.put("address", userDetails.get("address")).put("mobile", userDetails.get("mobile"))
//									.put("email", email));
//			response.setStatus(HttpServletResponse.SC_OK);
//		}
//
//		// Send the response as JSON
//		out.print(jsonResponse.toString());
//		out.flush();
//	}
//}
