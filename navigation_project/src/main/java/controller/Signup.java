package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SignupBeans;
import dao.SignupDao;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 /**
    * @see HttpServlet#HttpServlet()
    */
   public Signup() 
   {
       super();
       // TODO Auto-generated constructor stub
   }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        // TODO Auto-generated method stub
        System.out.println("inside insertuser");

        // Get parameters from the request
        String fullName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String pinCode = request.getParameter("pinCode");
         

        // Create SignupBeans object and set its properties
        SignupBeans signup = new SignupBeans();
        signup.setFirstName(fullName);
        signup.setLastName(lastName);
        signup.setEmail(email);
        signup.setContact(contact);
        signup.setPassword(password);
        signup.setAddress(address);
        signup.setPinCode(pinCode);

        // Create SignupDao object and call registerPerson method
        SignupDao obj = new SignupDao();
        try {
            int result = obj.registerPerson(signup);
            if (result > 0) {
                // Successful insertion, forward to success page
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/successfull.html");
//                dispatcher.forward(request, response);
            	response.sendRedirect("/navigation_project/successfull.html");
            } else {
                // Failed insertion, forward to failure page (optional)
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.html");
//                dispatcher.forward(request, response);
            	response.sendRedirect("/navigation_project/failure.html");
            }
        } catch (Exception e) {
            // Log and handle any exception that may occur
            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }
    
}
