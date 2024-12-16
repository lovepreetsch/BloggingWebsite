<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dao.AddressDao" %>
    <%@ page import="beans.LoginBeans" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
    <link rel="stylesheet" href="navigation_project.css">
</head>
<body>

    <section id="header">
        <nav>
        <a class="logo" href="">
            <img src="logo/project.co_4x.jpg" alt="Project">
        </a>
        <ul class="menu">
            <li><a href="" class="active">Home</a></li>
            <li><a href="#about-section">About</a></li>
            <li><a href="#portfolio-section">Portfolio</a></li>
            <li><a href="#contact-section">Contact</a></li>
             <li><a href="/navigation_project/List_blog" target="_self">blog</a></li>
        </ul>
    </nav>
    </section>

    <div  id="home-anime">>
       <span><img src="img/rocket.png" alt=""></span> 
    </div>

    <section id="home-section">
        <div id="header1">
            <h3>Hi! are you looking for web developer?</h3>
        <h2>I'm <span>
                <% 
			        String userName = (String) session.getAttribute("fullname");
			        if (userName != null) { 
			            out.print(userName); 
			        } else { 
			            out.print("Guest");
			        } 
   			 %>       
        		</span></h2>
        <p> I’ve been studying computer science for two years. In order to kick-start my career before I graduate, I am looking for a job of a web developer, since that’s what I’ve been doing already for four years in my free time. Designing websites for friends, developing simple e-shops with the help of some open source content-management systems, and so on. Besides that I am a normal guy who enjoys hiking and spending time with friends, while not working or studying, which takes most of my time of course. If I should pick the best website I’ve designed up to this point, it will be this one: GOOGLE. I am ambitious and hard-working and believe you’ll give me a chance to prove my words.</p>
        <a id="btn" href="ContactUs.html">Contact</a>
    </div>

    </section>

    <section id="about-section">
        <div id="about-img">
            <img src="img/about.png" alt="">
        </div>
        <div id="about-text">
            <h2>Web <span>Development</span> Services</h2>
            <h3>Hire me for your web development needs</h3>

            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Esse harum sed corrupti? Nesciunt natus numquam ratione eveniet quos perspiciatis sapiente veniam libero! Totam error impedit, hic a consectetur officia ipsum quae porro harum nihil, dolorum rerum? Perferendis accusamus ratione cupiditate recusandae dolore corporis quasi delectus dicta atque nulla facilis beatae, nisi quos enim ipsa similique, autem dolor vel illo placeat.
            </p>
            
        </div>

    </section>

    <section id="portfolio-section">
        <div id="portfolio-heading">
            <h1>My <span>Portfolio</span></h1>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquam sunt consequuntur et sequi eaque aliquid provident commodi, alias natus consequatur rerum totam nulla rem quo expedita a molestias corrupti adipisci!</p>
        </div>

        <div id="portfolio-content">
            <div class="portfolio-box">
                <div class="portfolio-img">
                    <img src="img/portfolio.png" alt="">
                </div>
                <div class="portfolio-details">
                    <h4>Web Development</h4>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum voluptatum blanditiis doloremque! Enim odio, corporis vitae tenetur ex libero expedita. Officia corrupti odit exercitationem harum ipsam eligendi doloremque deserunt laudantium modi. Illo quis maiores neque ducimus laboriosam ipsam pariatur quo unde veritatis repudiandae? Dolor totam modi, consequuntur tenetur eligendi temporibus.</p>
                    <a id="btn" href="#contact-section">Contact</a>
                </div>
            </div>
        </div>

        <div id="portfolio-content">
            <div class="portfolio-box">
                <div class="portfolio-img">
                    <img src="img/portfolio1.webp" alt="">
                </div>
                <div class="portfolio-details">
                    <h4>Web Development</h4>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum voluptatum blanditiis doloremque! Enim odio, corporis vitae tenetur ex libero expedita. Officia corrupti odit exercitationem harum ipsam eligendi doloremque deserunt laudantium modi. Illo quis maiores neque ducimus laboriosam ipsam pariatur quo unde veritatis repudiandae? Dolor totam modi, consequuntur tenetur eligendi temporibus.</p>
                    <a id="btn" href="#contact-section">Contact</a>
                </div>
            </div>
        </div>

        <div id="portfolio-content">
            <div class="portfolio-box">
                <div class="portfolio-img">
                    <img src="img/portfolio2.png" alt="">
                </div>
                <div class="portfolio-details">
                    <h4>Web Development</h4>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum voluptatum blanditiis doloremque! Enim odio, corporis vitae tenetur ex libero expedita. Officia corrupti odit exercitationem harum ipsam eligendi doloremque deserunt laudantium modi. Illo quis maiores neque ducimus laboriosam ipsam pariatur quo unde veritatis repudiandae? Dolor totam modi, consequuntur tenetur eligendi temporibus.</p>
                    <a id="btn" href="#contact-section">Contact</a>
                </div>
            </div>
        </div>

        <!-- <div id="portfolio-content">
            <div class="portfolio-box">
                <div class="portfolio-img">
                    <img src="img/portfolio3.jpg" alt="">
                </div>
                <div class="portfolio-details">
                    <h4>Web Development</h4>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum voluptatum blanditiis doloremque! Enim odio, corporis vitae tenetur ex libero expedita. Officia corrupti odit exercitationem harum ipsam eligendi doloremque deserunt laudantium modi. Illo quis maiores neque ducimus laboriosam ipsam pariatur quo unde veritatis repudiandae? Dolor totam modi, consequuntur tenetur eligendi temporibus.</p>
                </div>
            </div>
        </div> -->
    </section>

    <section id="contact-section">
        <form action="navigation-project" method="post">
        <h1>Contact Us</h1>
        <h3>Please enter your Contact details</h3>
        <input type="text" name="name" placeholder="Type your Name" id="name">
        <input type="email" name="email" placeholder="Type your email" id="email">
        <textarea name="message" placeholder="Type your message..." id="message"></textarea>
       <input type="submit" name="action" value="Send">


    <hr>


		<address>
		     <%--  <% 
		      // Retrieve email from session
		        String email = (String) session.getAttribute("email");
		        
		        if (email != null) {
		            LoginBeans login = new LoginBeans();
		            login.setEmail(email);
		       
		            AddressDao dao = new AddressDao();
		            String address = dao.getAddress(login);
		            
		            out.println(address);
		        } else {
		            out.println("No email found in session.");
		        }
		     %> --%>
		     
		      <% 
			        String address = (String) session.getAttribute("address");
			        if (address != null) { 
			            out.print(address); 
			        } else { 
			            out.print("no address found");
			        } 
   			 %>       
		</address>




    <p>Phone no. <b>
                        <% 
			        String mobile = (String) session.getAttribute("mobile");
			        if (mobile != null) { 
			            out.print(mobile); 
			        } else { 
			            out.print("no phone number found");
			        } 
   			 %>     
                  </b> </p>

    <a href="mailto:   <% 
			        String email = (String) session.getAttribute("email");
			        if (email != null) { 
			            out.print(email); 
			        } else { 
			            out.print("no address found");
			        } 
   			 %>     ">
   			 <% 
			        email = (String) session.getAttribute("email");
			        if (email != null) { 
			            out.print(email); 
			        } else { 
			            out.print("no address found");
			        } 
   			 %>  </a> <br>

    <p>copyright &copy; | All rights reserved</p>
</form>

    </section>
    



<!-- <section id="content">
        <img src="img/person.png" alt="">
    </section> -->
    </body>
</html>