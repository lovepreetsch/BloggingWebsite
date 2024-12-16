<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Sign up</title>

    <style>
        body{
            background-color: #04274d;
            margin: 0;
        }

        #signup-section{
            color: white;
            display: flex;
            justify-content: space-around;
            align-items: center;
            height: 900px;
        }
        #signup-section form{
            background-color: #e45c94;
            border-radius: 20px;
            box-shadow: 5px 10px 30px rgb(228,92,148, 0.5), -5px -10px 30px rgba(228,92,148, 0.5);
            width: 500px;
            height: 600px;
            padding: 0px 150px;
        }
        #signup-section form h2{
            font-size: 50px;
            /* padding: 0px 90px; */
            margin: 20px 160px;
            font-weight: bold;
        }
        #table{
            margin: 0 10px;

        }
        #signup-section form table tr td{
            font-size: 23px;
            width: 100%;
            padding: 5px;
        }

        #signup-section form table tr td input{
            font-size: 23px;
            border-radius: 7px;
            padding: 5px
        }
        #submit input[type="submit"] {
            padding: 10px 40px;
            margin: 20px 200px;
            font-size: 20px;
            background-color: #04274d;
            color: #fff;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }
        #submit input[type="submit"]:hover {
                background-color: #052e58;
      }
      #login{
        
        justify-content: center;
        display: flex;
      }
      #login a{
        text-decoration: none;
      }
    </style>

</head>
<body>
    <section id="signup-section">
        <form action="Signup">
            <h2>Sign Up</h2>
        <div id="table">
            <table>
                <tr class="first_name">
                    <td>First Name:</td>
                    <td><input type="text" name="firstname" id="firstname" placeholder="Enter first name"></td>
                </tr>
                <tr class="last_name">
                    <td>Last Name:</td>
                    <td><input type="text" name="lastname" id="lastname" placeholder="Enter last name"></td>
                </tr>
                <tr class="email">
                    <td>Email:</td>
                    <td><input type="email" name="email" id="email" placeholder="Enter email" required></td>
                </tr>
                <tr class="contact">
                    <td>Contact:</td>
                    <td><input type="number" name="mobile" id="mobile" maxlength="10" oninput="this.value = this.value.slice(0, this.maxLength);"  placeholder="Enter number" required></td>
                </tr>
                <tr class="password">
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password" placeholder="Enter password" required></td>
                </tr>

                <tr class="address">
                    <td>Address:</td>
                    <td><input type="text" name="address" id="address" placeholder="Enter address" required></td>
                </tr>
                <tr class="pin">
                    <td>Pin code:</td>
                    <td><input type="text" name="pinCode" id="pinCode" placeholder="Enter pinCode" required></td>
                </tr>
            </table>
        </div>

            <div id="submit">
                <input type="submit" name="action" value="Signup">
            </div>

            <div id="login">
                Already have an account? &nbsp;<a href="/navigation_project/Login.jsp"> Log in</a>
            </div>
        </form>
    </section>
</body>
</html>