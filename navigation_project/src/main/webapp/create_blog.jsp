<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"
        integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Create Blog</title>
    <style>
        body {
            margin: 0;
            background-color: #04274d;
            color: #fff;
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #04274d, #04274d);
        }
        
                #back-button {
            background-color: transparent;
            border: none;
            position: fixed;
            top: 5%;
            left: 2%;
            z-index: auto;
        }

        #back-button button {
            background-color: transparent;
            border: none;
            color: #fff;
            font-size: 1.5rem;
            cursor: pointer;
            transition: transform 0.3s ease, color 0.3s ease;
        }

        #back-button button:hover {
            transform: translateX(-10px);
            color: #e45c94;
        }

        #create-blog {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 900px;
            color: #333;
        }

        h1 {
            text-align: center;
            font-size: 32px;
            color: #333;
            margin-bottom: 30px;
            font-weight: 600;
        }

        #blog-title {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
        }

        #blog-title input[type="text"] {
            width: 80%;
            max-width: 600px;
            height: 40px;
            border-radius: 10px;
            border: 2px solid #ddd;
            padding: 0 15px;
            font-size: 16px;
            outline: none;
            transition: border-color 0.3s ease;
        }

       #blog-title input[type="text"]:focus {
            border-color: #e45c94;
        }

        #error {
            color: #d9534f;
            text-align: center;
            font-size: 14px;
            display: none;
            margin-top: -10px;
        }
        
         .errorMessage{
       	 	color: red;
            text-shadow: 2px 4px 6px rgba(0, 0, 0, 0.2);
            margin-left: 135px;
        }

        .btn-toolbar {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-bottom: 20px;
        }

        .btn-toolbar button {
            border: none;
            border-radius: 10px;
            width: 40px;
            height: 40px;
            background-color: #e45c94;
            color: #fff;
            font-size: 18px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.2s ease, background-color 0.2s ease;
        }

        .btn-toolbar button:hover {
            background-color: #d14c84;
            transform: translateY(-4px);
        }

        #blog-content {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
        }

        #content {
            width: 90%;
            height: 400px;
            min-height: 400px;
            border-radius: 10px;
            padding: 10px;
            border: 2px solid #ddd;
            background: #f9f9f9;
            color: #333;
            font-size: 16px;
            outline: none;
            resize: vertical;
            transition: border-color 0.3s ease;
        }

        #content:focus {
            border-color: #e45c94;
        }

        #blog-submit {
            display: flex;
            justify-content: center;
            gap: 20px;
            align-items: center;
        }

        #blog-submit input[type="file"] {
            border: 2px solid #ddd;
            padding: 10px;
            border-radius: 10px;
            cursor: pointer;
            color: fff;
        }

        #blog-submit input[type="submit"] {
            height: 45px;
            width: 120px;
            border-radius: 10px;
            border: none;
            background-color: #e45c94;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        #blog-submit input[type="submit"]:hover {
            background-color: #d14c84;
            transform: translateY(-4px);
        }
        
	      #loader {
	      position: fixed;
	      top: 0;
	      left: 0;
	      width: 100%;
	      height: 100%;
	      background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black */
	      display: none; /* Hidden by default */
	      justify-content: center;
	      align-items: center;
	      z-index: 1000; /* Ensure it appears above all content */
	    }
	
	    /* Loader styles */
	    .loader {
	      border: 8px solid #f3f3f3;
	      border-top: 8px solid #e45c94;
	      border-radius: 50%;
	      width: 50px;
	      height: 50px;
	      animation: spin 1s linear infinite;
	    }
	
	    @keyframes spin {
	      0% {
	        transform: rotate(0deg);
	      }
	      100% {
	        transform: rotate(360deg);
	      }
	    }

        @media (max-width: 768px) {
            #blog-title input[type="text"] {
                width: 90%;
            }

            #content {
                height: 300px;
            }

            .btn-toolbar button {
                width: 35px;
                height: 35px;
                font-size: 14px;
            }

            #blog-submit input[type="submit"] {
                width: 100px;
                font-size: 14px;
            }
        }

        @media (max-width: 480px) {
            #blog-title input[type="text"] {
                width: 90%;
                height: 45px;
            }

            #content {
                height: 200px;
            }

            .btn-toolbar button {
                width: 30px;
                height: 30px;
                font-size: 12px;
            }

            #blog-submit input[type="submit"] {
                width: 90px;
                font-size: 12px;
            }
        }
    </style>
</head>

<body>
<div id="back-button">
    <button title="Back" onclick="history.back()">
        <i class="fa-solid fa-arrow-left"></i> Back
    </button>
</div>
    <section id="create-blog">
        <h1>Create Your Blog Post</h1>
        <form action="Create_blog" method="post" enctype="multipart/form-data">
            <div id="blog-title">
                <input type="text" name="title" id="title" placeholder="Type Title">
            </div>
                    <!-- <p id="error"></p> -->
                    
                    
            <%
            		String errorMessage = (String) session.getAttribute("errorMessage");
           			if(errorMessage != null){
            %> 
            <div class="errorMessage"><%= errorMessage %></div>
            <%
            session.removeAttribute("errorMessage");
           			}
            %>

            <div class="btn-toolbar">
                <button type="button" onclick="formatDoc('bold')" title="Bold"><i class='bx bx-bold'></i></button>
                <button type="button" onclick="formatDoc('italic')" title="Italic"><i class='bx bx-italic'></i></button>
                <button type="button" onclick="formatDoc('underline')" title="Underline"><i class='bx bx-underline'></i></button>
                <button type="button" onclick="formatDoc('strikeThrough')" title="Strike Through"><i class='bx bx-strikethrough'></i></button>
                <button type="button" onclick="formatDoc('justifyLeft')" title="Justify Left"><i class='bx bx-align-left'></i></button>
                <button type="button" onclick="formatDoc('justifyCenter')" title="Justify Center"><i class='bx bx-align-middle'></i></button>
                <button type="button" onclick="formatDoc('justifyRight')" title="Justify Right"><i class='bx bx-align-right'></i></button>
                <button type="button" onclick="formatDoc('insertOrderedList')" title="Ordered List"><i class='bx bx-list-ol'></i></button>
                <button type="button" onclick="formatDoc('insertUnorderedList')" title="Unordered List"><i class='bx bx-list-ul'></i></button>
            </div>

            <div id="blog-content">
               <div id="content" contenteditable="true" data-placeholder="Type your content..."></div>
                 <textarea name="content" id="hiddenContent" style="display: none;"></textarea>
                 
            </div>

            <div id="blog-submit">
            <input type="file" title="Upload image" name="image" />
                <input id="submit" type="submit" value="Send" name="action" onclick="validateForm(event)">
            </div>
             <div id="loader">
    			<div class="loader"></div>
 			 </div>
        </form>
    </section>

    <script src="create_blog.js">
    </script>
</body>

</html>
 