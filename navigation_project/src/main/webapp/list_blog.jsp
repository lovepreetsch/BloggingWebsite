<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dao.listBlogDao"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Blog</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"
	integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
body {
	margin: 0;
	background-color: #04274d;
}

nav {
	display: flex;
	justify-content: space-around;
	align-items: center;
	box-shadow: 5px 10px 30px rgba(0, 0, 0, 0.5);
	position: sticky;
	width: 100%;
	background-color: #0a3766;
	left: 0px;
	top: 0px;
}

.back-button {
	background-color: #ff0077;
	color: white;
	/* margin-right: 20px; */
	padding: 10px 20px;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	transition: background-color 0.3s;
	cursor: pointer;
}

.back-button:hover {
	background-color: #cc0055;
}

.logo img {
	width: 100px;
	float: left;
	margin-right: 1522px;
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

#time p {
	margin-top: 1px;
	float: right;
	color: #04274d;
	font-weight: bolder;
}

#create i {
	position: fixed;
	bottom: 20px;
	right: 20px;
	font-size: 80px;
	color: green;
	cursor: pointer;
}

#create i {
	position: fixed;
	bottom: 20px;
	right: 20px;
	font-size: 80px;
	color: green;
	cursor: pointer;
}

#upwardButton {
	position: sticky;
	bottom: 20px;
	left: 20px;
	display: none;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 50%;
	padding: 15px;
	font-size: 18px;
	cursor: pointer;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	z-index: 1000;
	transition: opacity 0.3s, transform 0.3s;
}

#upwardButton:hover {
	background-color: #0056b3;
	transform: scale(1.1);
}

  @media (max-width: 800px) {
            nav {
                flex-direction: column;
                align-items: flex-start;
            }

            .logo img {
                margin-bottom: 10px;
            }

            .back-button {
                align-self: flex-end;
            }

            .blog-post {
                width: 95%;
            }

            #blog-section h1 {
                font-size: 1.5rem;
            }

            #create i {
                font-size: 2.5rem;
            }
        }
</style>
</head>
<body>

	<section id="header">
		<nav>
			<a class="logo" href=""> <img src="logo/project.co_4x.jpg"
				alt="Project">
			</a>
			<a href="/navigation_project/navigation_project.jsp">
				<button type="submit" class="back-button">Home</button>
			</a>

		</nav>
	</section>

	<section id="blog-section">
		<h1>My Blogs</h1>
		<c:forEach var="blog" items="${blogList}">
			<div class="blog-post">

				<div id="time">
					<p>${blog.createdOn }</p>
				</div>
				<h2>${blog.title}</h2>
				<p>${blog.content}</p>
			</div>
		</c:forEach>


		<button id="upwardButton" onclick="scrollToTop()"
			title="scroll button">
			<i class="fa-solid fa-arrow-up"></i>
		</button>

		<div id="create">
			<a href="create_blog.jsp" title="Create blog"><i
				class="fa-solid fa-circle-plus"></i></a>
		</div>
	</section>


	<script>
		const scrollButton = document.getElementById("upwardButton");

		onscroll = function() {
			if (document.body.scrollTop > 100
					|| document.documentElement.scrollTop > 100) {

				scrollButton.style.display = "block";

			} else {

				scrollButton.style.display = "none";
			}
		};

		function scrollToTop() {
			scrollTo({

				top : 0,
				behavior : "smooth"

			});

		}
	</script>

</body>
</html>