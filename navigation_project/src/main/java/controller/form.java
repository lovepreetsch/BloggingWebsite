package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.formBeans;
import dao.formDao;
import util.UtilityClass;

@WebServlet("/Form")
public class form extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		if(UtilityClass.isNull(name))
		{
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "name cannot be empty.");
			response.sendRedirect("/navigation_project/navigation_project.jsp#contact-section");
			return;
		}

		if(UtilityClass.isNull(email))
		{
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "email cannot be empty.");
			response.sendRedirect("/navigation_project/navigation_project.jsp#contact-section");
			return;
		}

		if(UtilityClass.isNull(message))
		{
			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "message cannot be empty.");
			response.sendRedirect("/navigation_project/navigation_project.jsp#contact-section");
			return;
		}

		// Perform any necessary actions (e.g., insert data into the database)
		formBeans form = new formBeans();
		form.setName(name);
		form.setEmail(email);
		form.setMessage(message);

		// Insert form data into the database
		formDao obj = new formDao();
		try
		{
			int result = obj.insertForm(form);
			if(result > 0)
			{
				// Redirect or forward to a success page
				response.sendRedirect("/navigation_project/navigation_project.jsp");
			}
			else
			{
				// Redirect to failure page
				response.sendRedirect("/navigation_project/failure.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			// Optionally handle the exception or forward to an error page
			response.sendError(	HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
								"An error occurred while processing the request.");
		}
	}
}
