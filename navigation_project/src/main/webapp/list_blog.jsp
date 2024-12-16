<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.listBlogDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Blog</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css" integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
    body {
        display: flex;
        justify-content: center;
        margin: 0;
        background-color: #04274d;
    }
    #blog-section {
        height: auto;
        margin-top: 20px;
    }
    #blog-section h1 {
        text-align: center;
        color: antiquewhite;
    }
    .blog-post {
        margin: 20px auto;
        padding: 20px;
        background-color: white;
        width: 80%;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .blog-post h2 {
        margin: 0;
        font-size: 24px;
        color: #04274d;
    }
    .blog-post p {
        margin-top: 10px;
        font-size: 16px;
        color: #333;
    }
    #create i {
        position: fixed;
        bottom: 20px;
        right: 20px;
        font-size: 80px;
        color: green;
        cursor: pointer;
    }
</style>
</head>
<body>
    <section id="blog-section">
        <h1>My Blogs</h1>
        <c:forEach var="blog" items="${blogList}">
            <div class="blog-post">
                <h2>${blog.title}</h2>
                <p>${blog.content}</p>
            </div>
        </c:forEach>
        <div id="create">
            <a href="create_blog.jsp"><i class="fa-solid fa-circle-plus"></i></a>
        </div>
    </section>
</body>
</html>