package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.mail.Part;
import javax.servlet.http.Part;

import beans.blogBeans;
import dao.createBlogDao;
import util.UtilityClass;

/**
 * Servlet implementation class create_blog
 */
@WebServlet("/Create_blog")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 100, // 10MB
		maxRequestSize = 1024 * 1024 * 500 // 50MB
)
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

		String title = request.getParameter("title").trim();
		String content = request.getParameter("content").trim();

		String uploadPath = "C:\\Users\\Lovepreet singh\\git\\repository\\navigation_project\\src\\main\\webapp\\img";

		System.out.println("create blog image upload:");

		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists())
		{
			uploadDir.mkdirs();
		}

		String imageUrl = null;

		for(Part part : request.getParts())
		{
			String fileName = part.getSubmittedFileName();

			if(fileName != null && !fileName.isEmpty())
			{
				fileName = Paths.get(fileName).getFileName().toString(); // Get the clean file name

				File fileSaveDir = new File(uploadPath, fileName);

				part.write(fileSaveDir.getAbsolutePath()); // Save the file to the specified path

				imageUrl = "img/" + fileName; // Store the relative path to be saved in DB
			}
		}

		try
		{
			TimeUnit.SECONDS.sleep(2);
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}

		System.out.println("CreateUserId: " + userId);
		System.out.println("Title: " + title);
		System.out.println("Content: " + content);
		System.out.println("Image: " + imageUrl);
		System.out.println("uploadPath: " + uploadPath);

		if(UtilityClass.isNull(title) || title.trim().isEmpty())
		{
			//			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "Title cannot be empty.");
			request.getRequestDispatcher("/create_blog.jsp").forward(request, response);
			return;
		}

		if(UtilityClass.isNull(content) || content.trim().isEmpty())
		{
			//			HttpSession session = request.getSession();
			session.setAttribute("errorMessage", "Content cannot be empty.");
			request.getRequestDispatcher("/create_blog.jsp").forward(request, response);
			return;
		}

		blogBeans blog = new blogBeans();
		blog.setUserId(userId);
		blog.setTitle(title);
		blog.setContent(content);
		blog.setImage(imageUrl);

		createBlogDao create = new createBlogDao();

		try
		{
			int result = create.createBlog(blog);
			if(result > 0)
			{
				//				RequestDispatcher dispatcher = request.getRequestDispatcher("/navigation_project/List_blog");
				response.sendRedirect("List_blog");
				return;
				//				dispatcher.forward(request, response);
			}
			else
			{
				session = request.getSession();
				session.setAttribute("errorMessage", "No data found");
				//				request.setAttribute("errorMessage", "No data found");
				//				RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.html");
				//                dispatcher.forward(request, response);
				response.sendRedirect("/navigation_project/failure.html");
				return;
			}
		}
		catch(Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
