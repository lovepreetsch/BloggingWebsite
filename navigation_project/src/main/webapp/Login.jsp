<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <style>
        html {
            /* border: 2px solid black; */
        }

        body {
            margin: 0;
            background-color: #00658d;
        }

        #login-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 540px;
            width: 90%;
            max-width: 500px;
            margin: 10% auto;
            background-color: #e6f1f5;
            border-radius: 50px;
            box-shadow: 5px 10px 30px rgb(230,241,245,0.5), -5px -10px 30px rgb(230,241,245,0.5);
            padding: 20px;
            box-sizing: border-box;
        }

        .icon {
            font-size: 50px;
            margin-bottom: 20px;
            justify-content: center;
            display: flex;
        }

        #login-section h2 {
            font-size: 30px;
            margin-top: 10px;
            text-align: center;
        }
        
        .errorMessage{
           color: red;        
        }

        #email, #password {
            margin: 15px 0;
            font-size: large;
        }

        #email input, #password input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            font-size: large;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        #forgetPassword {
            text-align: right;
            margin-top: 10px;
        }

        #forgetPassword a {
            text-decoration: none;
            color: #0077ff;
        }

        #login {
            margin-top: 20px;
            justify-content: center;
            display: flex;
        }

        #login input[type="submit"] {
            font-size: 18px;
            padding: 10px 20px;
            background-color: #0077ff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #login input[type="submit"]:hover {
            background-color: #005bb5;
        }

        #signup {
            justify-content: center;
            display: flex;
            margin-top: 20px;
            text-align: center;
        }

        #signup a {
            text-decoration: none;
            color: #0077ff;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            #login-section {
                width: 80%;
                padding: 20px;
            }

            #login-section h2 {
                font-size: 26px;
            }

            #email input, #password input {
                padding: 8px;
                font-size: medium;
            }

            #login input[type="submit"] {
                font-size: 16px;
                padding: 8px 15px;
            }
        }

        @media (max-width: 480px) {
            #login-section {
                width: 90%;
                padding: 15px;
            }

            #login-section h2 {
                font-size: 22px;
            }

            #email input, #password input {
                font-size: small;
                padding: 6px;
            }

            #login input[type="submit"] {
                font-size: 14px;
                padding: 8px 12px;
            }
        }
    </style>

</head>
<body>
    <section id="login-section">
        <form action="Login" method="post">
            <div class="icon">👤</div>
            <h2>Login</h2>

            <div id="email">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Enter Email" required="required">
            </div>

            <div id="password">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Enter Password" required="required">
            </div>
            
                        
           <%
            		String errorMessage = (String) session.getAttribute("errorMessage");
           			if(errorMessage != null){
            %> 
            <div class="errorMessage"><%= errorMessage %></div>
            <%
            session.removeAttribute("errorMessage");
           			}
            %>
            

            <div id="forgetPassword">
                <a href="/navigation_project/ForgetPassword.jsp">Forget Password?</a>
            </div>

            <div id="login">
                <input type="submit" name="action" value="Login">
            </div>

            <div id="signup">
                Not a Member?&nbsp;<a href="/navigation_project/Signup.jsp"> Signup</a>
            </div>
        </form>
    </section>
</body>
</html>
