<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Sign up</title>

    <style>
        body {
            background-color: #04274d;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        #signup-section {
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
            box-sizing: border-box;
        }

        #signup-section form {
            background-color: #e45c94;
            border-radius: 20px;
            box-shadow: 5px 10px 30px rgba(228, 92, 148, 0.5), -5px -10px 30px rgba(228, 92, 148, 0.5);
            width: 90%;
            max-width: 500px;
            padding: 20px 30px;
            box-sizing: border-box;
        }

        #signup-section form h2 {
            font-size: 32px;
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
        }

        #signup-section form table {
            width: 100%;
        }

        #signup-section form table tr td {
            font-size: 18px;
            padding: 8px 0;
        }

        #signup-section form table tr td input {
            font-size: 16px;
            border-radius: 7px;
            padding: 8px;
            width: 100%;
            box-sizing: border-box;
            border: none;
        }

        #submit {
            text-align: center;
            margin-top: 20px;
        }

        #submit input[type="submit"] {
            padding: 10px 40px;
            font-size: 18px;
            background-color: #04274d;
            color: #fff;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        #submit input[type="submit"]:hover {
            background-color: #052e58;
        }

        #login {
            text-align: center;
            margin-top: 10px;
        }

        #login a {
            text-decoration: none;
            color: #04274d;
            font-weight: bold;
        }

        #login a:hover {
            text-decoration: underline;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            #signup-section form {
                padding: 20px;
            }

            #signup-section form h2 {
                font-size: 28px;
            }

            #signup-section form table tr td {
                font-size: 16px;
            }

            #submit input[type="submit"] {
                font-size: 16px;
                padding: 8px 20px;
            }
        }

        @media (max-width: 480px) {
            #signup-section form {
                padding: 15px;
            }

            #signup-section form h2 {
                font-size: 24px;
            }

            #signup-section form table tr td {
                font-size: 14px;
            }

            #submit input[type="submit"] {
                font-size: 14px;
                padding: 8px 15px;
            }
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
                    <td><input type="text" name="firstname" id="firstname" placeholder="Enter first name" required="required"></td>
                </tr>
                <tr class="last_name">
                    <td>Last Name:</td>
                    <td><input type="text" name="lastname" id="lastname" placeholder="Enter last name" required="required"></td>
                </tr>
                <tr class="email">
                    <td>Email:</td>
                    <td><input type="email" name="email" id="email" placeholder="Enter email" required="required"></td>
                </tr>
                <tr class="contact">
                    <td>Contact:</td>
                    <td><input type="number" name="mobile" id="mobile" maxlength="10" oninput="this.value = this.value.slice(0, this.maxLength);"  placeholder="Enter number" required="required" ></td>
                </tr>
                <tr class="password">
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password" placeholder="Enter password" required="required"></td>
                </tr>

                <tr class="address">
                    <td>Address:</td>
                    <td><input type="text" name="address" id="address" placeholder="Enter address" required="required"></td>
                </tr>
                <tr class="pin">
                    <td>Pin code:</td>
                    <td><input type="text" name="pinCode" id="pinCode" placeholder="Enter pinCode" required="required"></td>
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