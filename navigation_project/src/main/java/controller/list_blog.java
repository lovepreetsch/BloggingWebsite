//package controller;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import beans.ResponseWrapper;
//import beans.blogBeans;
//import dao.listBlogDao;
//
///**
// * Servlet implementation class list_blog
// */
//@WebServlet("/List_blog")
//public class list_blog extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("userId");
//		
//		System.out.println("Inside controller servlet: " + userId);
//		
//		listBlogDao obj = new listBlogDao();
//		
//		blogBeans blog = new blogBeans();
//		blog.setUserId(userId);
//		
//		ResponseWrapper<?> blogDetails = obj.listBlog(blog);
//		
//		if(blogDetails.getSuccess() >= 1) {
//			List<HashMap<String,Object>> blogList= (List<HashMap<String, Object>>) blogDetails.getData();
//			HashMap<String, Object> blogObj = (HashMap<String, Object>) blogList.get(0);
//			
//			String title = (String) blogObj.get("title");
//			String content = (String) blogObj.get("content");
//			
//			userId = (int) blogObj.get("userId");
//			
//			System.out.println("userId111111111111: " + userId);
//			
//			if(userId != 0) {
//				
//				System.out.println("userId222222222: " + userId);
//				
//				session = request.getSession();
//				session.setAttribute("userId", userId);
//				
//				session.setAttribute("title", title);
//				session.setAttribute("content", content);
//				session.setAttribute("blogList", blogList);
//				
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
//				try 
//				{ 
//					dispatcher.forward(request, response); 
//				} 
//				catch (ServletException | IOException e) 
//				{ 
//					e.printStackTrace(); 
//				}
//				}
//				else
//				{
//					request.setAttribute("errorMessage", "Wrong Credentials");
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
//					try { dispatcher.forward(request, response); } catch (ServletException | IOException e) { e.printStackTrace(); }
//				}
//		}
//		
//		
//	}
//
//}

package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ResponseWrapper;
import beans.blogBeans;
import dao.listBlogDao;

@WebServlet("/List_blog")
public class list_blog extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false); // false ensures no new session is created
		if(session == null || session.getAttribute("userId") == null)
		{
			// Redirect to login if session or user ID is not available
			response.sendRedirect("Login.jsp");
			return;
		}

		int userId = (int) session.getAttribute("userId");
		System.out.println("Fetching blogs for user ID: " + userId);

		listBlogDao blogDao = new listBlogDao();
		blogBeans blog = new blogBeans();
		blog.setUserId(userId);

		ResponseWrapper<?> blogDetails = blogDao.listBlog(blog);

		System.out.println("blogDetails" + blogDetails);

		if(blogDetails.getSuccess() >= 1)
		{
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> blogList = (List<HashMap<String, Object>>) blogDetails.getData();

			System.out.println("BlogList" + blogList);

			if(blogList != null && !blogList.isEmpty())
			{
				System.out.println("Details found in blog list");
				request.setAttribute("blogList", blogList); // Use request scope for forwarding
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				// No blogs found, set an attribute to notify user
				System.out.println("No blogs found for this user.");
				request.setAttribute("message", "No blogs found for this user.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			// Handle failure case
			System.out.println("Unable to fetch blogs. Please try again later.");
			request.setAttribute("errorMessage", "Unable to fetch blogs. Please try again later.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create_blog.jsp");
			dispatcher.forward(request, response);
		}
	}

	//	@Override
	//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	//	{
	//		doGet(request, response); // Reuse logic for GET and POST
	//	}
}
