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
        <form action="Logout" method="post">
    <button type="submit" class="logout-button">Logout</button>
</form>
        <!-- <a href="/navigation_project/Logout" class="logout-button">Logout</a> -->
    </nav>
    </section>

<!--     <div  id="home-anime">
       <span><img src="img/rocket.png" alt=""></span> 
    </div> -->

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
        <a id="btn" href="#contact-section">Contact</a>
    </div>
    <div id="home-img">
        <img src="img/person1.png" alt="">
    </div>
    </section>

    <section id="about-section">
        <div id="about-img">
            <img src="img/about.png" alt="">
        </div>
        <div id="about-text">
            <h2>Web <span>Development</span> Services</h2>
            <h3>Hire me for your web development needs</h3>

            <p>The customer himself, the customer will be able to pursue the adipiscing of the company. Are these but corrupt? They do not know that the born will never happen with reason. The whole error hinders, here from the very duties that follow these nothing, sorrowful things? We accuse him of suffering, on the grounds of desire to refuse the pain of the body, as if it were said to be chosen, and there are no easy happy ones, except those who themselves and the like, but the pain pleases even that.</p>
            
        </div>

    </section>

    <section id="portfolio-section">
        <div id="portfolio-heading">
            <h1>My <span>Portfolio</span></h1>
            <p>The customer himself, the customer will be able to pursue the adipiscing of the company. With the caresses of pleasure and pain! For by hatred, the life of the body is held fast from freedom. The offices of the corrupt hate the practice of choosing these very things, and they abandon the pains of praising the manners. Who is older than him, and we do not lead the laborious one to bear with him whence the truth is to be repudiated? Pain is the whole way, the result is bound to choose the times.</p>
        </div>

        <div id="portfolio-content">
            <div class="portfolio-box">
                <div class="portfolio-img">
                    <img src="img/portfolio.png" alt="">
                </div>
                <div class="portfolio-details">
                    <h4>Web Development</h4>
                    <p>The customer himself, the customer will be able to pursue the adipiscing of the company. With the caresses of pleasure and pain! For by hatred, the life of the body is held fast from freedom. The offices of the corrupt hate the practice of choosing these very things, and they abandon the pains of praising the manners. Who is older than him, and we do not lead the laborious one to bear with him whence the truth is to be repudiated? Pain is the whole way, the result is bound to choose the times.</p>
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
                    <p>The customer himself, the customer will be able to pursue the adipiscing of the company. With the caresses of pleasure and pain! For by hatred, the life of the body is held fast from freedom. The offices of the corrupt hate the practice of choosing these very things, and they abandon the pains of praising the manners. Who is older than him, and we do not lead the laborious one to bear with him whence the truth is to be repudiated? Pain is the whole way, the result is bound to choose the times.</p>
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
                    <p>The customer himself, the customer will be able to pursue the adipiscing of the company. With the caresses of pleasure and pain! For by hatred, the life of the body is held fast from freedom. The offices of the corrupt hate the practice of choosing these very things, and they abandon the pains of praising the manners. Who is older than him, and we do not lead the laborious one to bear with him whence the truth is to be repudiated? Pain is the whole way, the result is bound to choose the times.</p>
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

</form>

<footer id="footer">

    <p>copyright &copy; | All rights reserved</p>
</footer>

    </section>
    



<!-- <section id="content">
        <img src="img/person.png" alt="">
    </section> -->
    </body>
</html>