<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Blog</title>
    <style>
        body{
            margin: 0;
            background-color: #04274d;
        }

        #create-blog{
            /* border: 2px solid black; */
            height: 900px;
            display: flex;
            justify-content: center;
        }

        #blog-title {
            margin: 50px 0;
            display: flex;
            justify-content: center;
        }

        #blog-title input[type= "text"]{
            width: 300px;
            height: 40px;
            border-radius: 10px;
        }

        #blog-content textarea{
            width: 900px;
            height: 600px;
            border-radius: 10px;
            padding: 8px;
            box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.5) , -5px -10px 10px rgba(0, 0, 0, 0.5);
        }

        #blog-submit{
            display: flex;
            justify-content: center;
            margin: 70px 0;
        }

        #blog-submit input[type= "submit"]{
            height: 35px;
            width: 75px;
            border-radius: 8px;
            background-color: #e45c94;
            box-shadow: 5px 10px 30px rgba(228,92,148, 0.2) , -5px -10px 30px rgba(228,92,148, 0.2);
        }
</style>
</head>
<body>
    <section id="create-blog">
        <form action="Create_blog" method="post">
        
<%--         
        <%
             int userId = (int) session.getAttribute("userId");
        System.out.println("id: " + userId);
        %> --%>
        
            <div id="blog-title">
                <input type="text" name="title" id="title" placeholder="Enter Title">
            </div>

        
        <div id="blog-content" contenteditable="true">
            <textarea name="content" id="content" placeholder="Type your content..."></textarea>
        </div>

        <div id="blog-submit">
           <!--  <a href="list_blog.jsp"> -->
            <input type="submit" name="action" value="Send">
         <!--    </a> -->
            <!-- <input type="submit" value="Send"> -->
        </div>
    </form>
    </section>
</body>
</html>