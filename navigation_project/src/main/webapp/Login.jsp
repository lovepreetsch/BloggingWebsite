<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Login</title>

   <style>
        html{
            /* border: 2px solid black; */
        }
        body{
            /* border: 2px solid black; */
            /* height: 900px; */
            margin: 0;
            background-color: #00658d;
        }
        #login-section{
            /* border: 2px solid black; */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 510px;
            width: 500px;
            margin: 9% 37%;
            background-color: #e6f1f5;
            border-radius: 50px;
            box-shadow: 5px 10px 30px rgb(230,241,245,0.5), -5px -10px 30px rgb(230,241,245,0.5);
        }
        .icon{
            font-size: 50px;
            margin-bottom: 20px;
            /* margin-left: 134px; */
            justify-content: center;
            display: flex;
        }
        #login-section h2{
            font-size: 50px;
            margin-top: 1px;
            margin-left: 140px;
        }
        #email{
            margin: 11px;
            margin-left: 40px;
            font-size: large;
            border-radius: 5px;
        }
        #email input{
            margin-left: 66px;
        }
        #password{
            margin: 11px;
            margin-left: 40px;
            font-size: large;
            border-radius: 5px;
        }

        #forgetPassword{
            float: right;
            margin-right: 20px;
        }

        #forgetPassword a{
            text-decoration: none;
        }

        #login{
            margin-top: 40px;
            border-radius: 10px;
            justify-content: center;
            display: flex;

        }

        #login input[type="submit"]{
            font-size: 20px;
            border-radius: 5px;
            padding: 5px 15px;
            background-color: #0077ff;
        }

        #login input[type="submit"]:hover{
            background-color: #005bb5;
        }

        #signup{
            justify-content: center;
            display: flex;
            margin-top: 30px;
            border-radius: 10px;
            
        }
        #signup a{
            text-decoration: none;
        }
        

    </style>

</head>
<body>
    <section id="login-section">
        <form action="login">
            <div class="icon">👤</div>
            <h2>Login</h2>
            <div id="email">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Enter Email">
            </div>
    
            <div id="password">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Enter Password">
            </div>

            <div id="forgetPassword">
                <a href="#">Forget Password?</a>
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