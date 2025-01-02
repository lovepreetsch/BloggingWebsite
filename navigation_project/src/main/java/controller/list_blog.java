
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

import com.google.gson.Gson;

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
		String searchKeyword = request.getParameter("searchKeyword");
		String action = request.getParameter("action");
		System.out.println("Fetching blogs for user ID: " + userId);

		listBlogDao blogDao = new listBlogDao();
		blogBeans blog = new blogBeans();
		blog.setUserId(userId);
		blog.setSearchKeyword(searchKeyword);

		if("autocomplete".equals(action))
		{
			System.out.println("autocomplete in servlet");
			//			String keyword = request.getParameter("searchKeyword");
			List<String> suggestions = listBlogDao.getBlogSuggestions(blog);
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(suggestions));
			//			try
			//			{
			//				TimeUnit.SECONDS.sleep(2);
			//			}
			//			catch(InterruptedException e1)
			//			{
			//				e1.printStackTrace();
			//			}
			return;
		}

		ResponseWrapper<?> blogstotalCount = blogDao.listBlogCount(blog);
		{
			if(blogstotalCount.getSuccess() > 0)
			{

				int currentPage = 1;
				String pageParam = request.getParameter("page");
				System.out.println("pageParam: " + pageParam);
				if(pageParam != null)
				{
					try
					{
						currentPage = Integer.parseInt(pageParam);
					}
					catch(NumberFormatException e)
					{
						currentPage = 1; // Default to 1 if the page number is not a valid integer
					}
				}

				int totalBlogs = (Integer) blogstotalCount.getData();

				System.out.println("totalBlogs: " + totalBlogs);

				int blogsPerPage = 5; // Blogs per page
				int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage);

				System.out.println("TotalPages: " + totalPages);
				//				request.setAttribute("currentPage", currentPage);
				//				request.setAttribute("totalPages", totalPages);

				blog.setPage(currentPage);
				blog.setCount(blogsPerPage);

				ResponseWrapper<?> blogDetails = blogDao.listBlog(blog);

				//				System.out.println("blogDetails" + blogDetails);

				if(blogDetails.getSuccess() == 1)
				{
					@SuppressWarnings("unchecked")
					List<HashMap<String, Object>> blogList = (List<HashMap<String, Object>>) blogDetails.getData();

					System.out.println("totalBlogs1111111: " + totalBlogs);
					System.out.println("TotalPages1111111: " + totalPages);
					//					System.out.println("BlogList" + blogList);
					System.out.println("Details found in blog list");
					request.setAttribute("totalPages", totalPages);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("blogList", blogList); // Use request scope for forwarding
					request.setAttribute("searchKeyword", searchKeyword);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
					dispatcher.forward(request, response);
					return;
				}
				else if(blogDetails.getSuccess() == 0)
				{
					// Handle failure case
					System.out.println("No record found.");
					session.setAttribute("errorMessage", "No record found");
					request.setAttribute("searchKeyword", searchKeyword);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
					dispatcher.forward(request, response);
					return;
				}
				else
				{
					System.out.println("Internal server error.");
					session.setAttribute("errorMessage", "Internal server error.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			else if(blogstotalCount.getSuccess() == 0)
			{
				// Handle failure case
				System.out.println("No details found.");
				session.setAttribute("errorMessage", "No details found.");
				request.setAttribute("searchKeyword", searchKeyword);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list_blog.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else
			{
				System.out.println("Internal server error.");
				session.setAttribute("errorMessage", "Internal server error.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}
	//	@Override
	//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	//	{
	//		doGet(request, response); // Reuse logic for GET and POST
	//	}
}
