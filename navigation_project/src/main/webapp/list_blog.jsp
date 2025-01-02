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
	background-color: #04274d;
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

.errorMessage {
	color: red;
	text-shadow: 2px 4px 6px rgba(0, 0, 0, 0.2);
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 2rem;
	width: 50%;
	margin: auto;
	text-align: center;
	line-height: 20;
}

#search-section {
	margin-left: 8.9%;
}

#searchInput {
	border-radius: 5px;
	font-weight: 400;
	line-height: 1.5;
	padding: .375rem .75rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	width: 15%;
	border: 2px solid #f0f0f0 !important;
}

#searchButton {
	border-radius: 5px;
	font-weight: 400;
	line-height: 1.5;
	padding: .375rem .75rem;
	/* transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out; */
	/* width: 10%; */
	border: 2px solid #f0f0f0 !important;
}

#searchButton:hover {
	background-color: rgba(245, 245, 245, 0.89);
	cursor: pointer;
	box-shadow: 2px 5px 15px rgba(245, 245, 245, 0.5);
}

#suggestionBox div {
    border-bottom: 1px solid #ccc;
    padding: 8px;
    cursor: pointer;
    background-color: #fff;
}

#suggestionBox div:hover {
    background-color: #f0f0f0;
}



.blog-post {
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	width: 80%;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.blog-post:hover {
	transform: scale(1.02);
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

.pagination {
    display: flex;
    justify-content: center;
    padding: 10px;
    list-style: none;
}

.pagination .page-item {
    margin: 0 5px;
}

.pagination .page-link {
    color: #007bff;
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
}

.pagination .active .page-link {
    background-color: #007bff;
    color: white;
    border-color: #007bff;
}


.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Fixed position */
	z-index: 1000; /* On top */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.8);
	/* Black background with transparency */
	overflow: auto; /* Enable scrolling if needed */
	justify-content: center;
	align-items: center;
}

/* Modal content (image) */
.modal-content {
	max-width: 90%;
	max-height: 90%;
	margin: auto;
	margin-top: 50px;
	display: block;
}

/* Close button (X) */
.close {
	color: white;
	font-size: 40px;
	font-weight: bold;
	position: absolute;
	top: 15px;
	right: 35px;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #f44336;
	text-decoration: none;
	cursor: pointer;
}

/* Style for the image thumbnail (small version) */
img {
	cursor: pointer;
	transition: 0.3s ease;
}

@media ( max-width : 800px) {
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
			</a> <a href="/navigation_project/navigation_project.jsp">
				<button type="submit" class="back-button">Home</button>
			</a>

		</nav>
	</section>

	<section id="blog-section">
		<h1>My Blogs</h1>
		<div id="search-section">
			<!-- Search form -->
			<form action="List_blog" method="get">
				<input type="text" id="searchInput" name="searchKeyword" placeholder="Search blogs..." autocomplete="off"/>
				<div id="suggestionBox" style="background-color: white; border: 1px solid #ccc; position: absolute; z-index: 1000; display: none;"></div>
				<button id="searchButton" type="submit">Search</button>
			</form>
		</div>

<!-- 		<div id="autocompleteResults" class="autocomplete-results"></div> -->

		<!-- For errorMessage in center -->
		<%
		String errorMessage = (String) session.getAttribute("errorMessage");
		if (errorMessage != null) {
		%>
		<div class="errorMessage"><%=errorMessage%></div>
		<%
		session.removeAttribute("errorMessage");
		}
		%>
		<c:forEach var="blog" items="${blogList}">
			<div class="blog-post">

				<c:choose>

					<c:when test="${empty blog.title}">
						<p style="color: red; font-weight: bold;">Error: Blog title is
							missing.</p>
					</c:when>

					<c:when test="${empty blog.content}">
						<p style="color: red; font-weight: bold;">Error: Blog content
							is missing.</p>
					</c:when>

					<c:otherwise>

						<div id="time">
							<p>${blog.createdOn }</p>
						</div>
						<h2>${blog.title}</h2>
						<p>${blog.content}</p>
						<span>...</span>
						<button id="readButton">Read more</button>
						<div id="image">
							<%-- <p>http://localhost:8080${pageContext.request.contextPath}/${blog.image }</p> --%>
							<%-- <c:if test="${not empty blog.image}"
                    <img src="http://localhost:8080${pageContext.request.contextPath}/${blog.image}" alt="Blog Image" style="width: 10%; height: auto;">
                </c:if> --%>
							<c:choose>
								<c:when
									test="${not empty blog.image && blog.image.endsWith('.jpg') || blog.image.endsWith('.jpeg') || blog.image.endsWith('.png') }">
									<a href="javascript:void(0);"
										onclick="openModal('http://localhost:8080${pageContext.request.contextPath}/${blog.image}')"><img
										src="http://localhost:8080${pageContext.request.contextPath}/${blog.image}"
										alt="Blog Image" style="width: 10%; height: auto;"></a>
								</c:when>

								<c:when
									test="${not empty blog.image && blog.image.endsWith('.pdf')}">
									<%-- <iframe src="http://localhost:8080${pageContext.request.contextPath}/${blog.image}" alt="Blog Image" style="width: 10%; height: auto;"> --%>
									<a href="${pageContext.request.contextPath}/${blog.image}"
										target="_blank">Click here to view the PDF.</a>
									<!-- </iframe> -->
								</c:when>
								
								<c:when
									test="${not empty blog.image && blog.image.endsWith('.mp4') || blog.image.endsWith('.mkv')}">
									<%-- <iframe src="http://localhost:8080${pageContext.request.contextPath}/${blog.image}" alt="Blog Image" style="width: 10%; height: auto;"> --%>
									<a href="${pageContext.request.contextPath}/${blog.image}"
										target="_blank">Click here to view the video.</a>
									<!-- </iframe> -->
								</c:when>

								<c:otherwise>
									<p style="color: red;">No File uploaded</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
		
		<div id="pagination-section">
    <c:if test="${totalPages > 1}">
        <nav id="page-nav">
            <ul class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li class="page-item ${page == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${page}&searchKeyword=${param.searchKeyword}" aria-label="Go to page ${page}">
                            ${page}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
</div>

		<!-- Modal for Enlarged Image -->
		<div id="imageModal" class="modal">
			<span class="close" onclick="closeModal()">&times;</span> <img
				class="modal-content" id="modalImage">
		</div>


		<button id="upwardButton" onclick="scrollToTop()"
			title="scroll button">
			<i class="fa-solid fa-arrow-up"></i>
		</button>

		<div id="create">
			<a href="create_blog.jsp" title="Create blog"><i
				class="fa-solid fa-circle-plus"></i></a>
		</div>
	</section>

<script src="list_blog.js"></script>

</body>
</html>