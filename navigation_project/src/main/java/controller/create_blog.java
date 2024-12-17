package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.blogBeans;
import dao.createBlogDao;

/**
 * Servlet implementation class create_blog
 */
@WebServlet("/Create_blog")
public class create_blog extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		System.out.println("CreateUserId: " + userId);
		System.out.println("Title: " + title);
		System.out.println("Content: " + content);

		blogBeans blog = new blogBeans();
		blog.setUserId(userId);
		blog.setTitle(title);
		blog.setContent(content);

		createBlogDao create = new createBlogDao();

		try
		{
			int result = create.createBlog(blog);
			if (result > 0)
			{
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/navigation_project/List_blog");
				response.sendRedirect("List_blog");
//				dispatcher.forward(request, response);
			} else
			{
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.html");
//                dispatcher.forward(request, response);
				response.sendRedirect("/navigation_project/failure.html");
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
