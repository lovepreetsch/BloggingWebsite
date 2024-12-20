<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Forget Password</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }

        /* Section Styling */
        #forget-section {
            background-color: #e6f1f5;
            border-radius: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            padding: 30px;
            text-align: center;
        }

        /* Header Styling */
        #forget-section h2 {
            font-size: 32px;
            margin-bottom: 10px;
            color: #333;
        }

        #forget-section p {
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }

        /* Input Fields */
        input[type="text"], 
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input[type="text"]:focus, 
        input[type="password"]:focus {
            outline: none;
            border-color: #7a4cf5;
            box-shadow: 0 0 5px rgba(122, 76, 245, 0.5);
        }
        
         .errorMessage{
           color: red;        
        }

        /* Submit Button */
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #7a4cf5;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #6a3ee0;
        }

        /* Responsive Adjustments */
        @media (max-width: 480px) {
            #forget-section {
                width: 90%;
                padding: 20px;
            }

            #forget-section h2 {
                font-size: 28px;
            }
        }
    </style>
</head>
<body>
    <section id="forget-section">
        <form action="ForgetPassword" method="post">
            <!-- Header -->
            <div id="header">
                <h2>Forgot Password?</h2>
                <p>Don't worry, we are here to help.</p>
            </div>

            <!-- Email Field -->
            <div id="content-email">
                <label for="email">Email Address</label>
                <input type="text" name="email" id="email" placeholder="Enter your email" required>
            </div>

            <!-- Password Field -->
            <div id="content-password">
                <label for="password">New Password</label>
                <input type="password" name="password" id="password" placeholder="Enter new password" required>
            </div>
            
            
           <%--   <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if> --%>
        
         <%
            		String errorMessage = (String) session.getAttribute("errorMessage");
           			if(errorMessage != null){
            %> 
            <div class="errorMessage"><%= errorMessage %></div>
            <%
            session.removeAttribute("errorMessage");
           			}
            %>

            <!-- Submit Button -->
            <div id="content-submit">
                <input type="submit" name="action" value="Continue">
            </div> <br>

            Go back to <a href="Login.jsp">Login</a>
        </form>
    </section>
</body>
</html>