<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <title>Create Blog</title>
    <style>
        body {
            margin: 0;
            background-color: #04274d;
            color: #fff;
            font-family: Arial, sans-serif;
        }

        #create-blog {
            height: auto;
            padding: 20px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }
        
       #error{
            color: red;
            text-shadow: 2px 4px 6px rgba(0, 0, 0, 0.2);
            margin-left: 150px;
        }
        
        .errorMessage{
       	 	color: red;
            text-shadow: 2px 4px 6px rgba(0, 0, 0, 0.2);
            margin-left: 150px;
        }

        #blog-title {
            margin: 20px 0;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        #blog-title input[type="text"] {
            width: 80%;
            max-width: 600px;
            height: 40px;
            border-radius: 10px;
            border: none;
            padding: 0 10px;
        }

        .btn-toolbar {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 10px 0;
        }

        .btn-toolbar button {
            margin: 5px;
            border: none;
            border-radius: 7px;
            width: 40px;
            height: 40px;
            background-color: #e45c94;
            color: #fff;
            font-size: 18px;
            cursor: pointer;
            box-shadow: 2px 4px 6px rgba(0, 0, 0, 0.2);
        }

        .btn-toolbar button:hover {
            background-color: #d14c84;
        }

        #blog-content {
            width: 100%;
            display: flex;
            justify-content: center;
        }

        #content {
            min-width: 900px;
            min-height: 600px;
            width: 90%;
            height: 600px;
            border-radius: 10px;
            padding: 10px;
            border: none;
            background: #fff;
            color: #000;
            overflow-y: auto;
            box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.5);
            outline: none;
        }

        #blog-submit {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }

        #blog-submit input[type="submit"] {
            height: 40px;
            width: 100px;
            border-radius: 8px;
            border: none;
            background-color: #e45c94;
            color: #fff;
            font-size: 16px;
            box-shadow: 5px 10px 30px rgba(228, 92, 148, 0.2), -5px -10px 30px rgba(228, 92, 148, 0.2);
            cursor: pointer;
        }

        #blog-submit input[type="submit"]:hover {
            background-color: #d14c84;
        }

        @media (max-width: 768px) {
            #blog-title input[type="text"] {
                width: 90%;
                height: 50px;
            }

            #content {
                width: 90%;
                height: 400px;
                min-width: unset;
                min-height: unset;
            }

            .btn-toolbar button {
                width: 35px;
                height: 35px;
                font-size: 16px;
            }
        }

        @media (max-width: 480px) {
            #blog-title input[type="text"] {
                width: 90%;
                height: 50px;
            }

            #content  {
                width: 100%;
                height: 300px;
            }

            .btn-toolbar button {
                width: 30px;
                height: 30px;
                font-size: 14px;
            }

            #blog-submit input[type="submit"] {
                width: 80px;
                font-size: 14px;
            }
        }
    </style>
</head>

<body>
    <section id="create-blog">
        <form action="Create_blog" method="post" onsubmit="return validateForm(event);">
        
            <div id="blog-title">
                <input type="text" name="title" id="title" placeholder="Type Title">
            </div>
                    <p id="error"></p>
                    
                    
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
<!--                 <button type="button" onclick="addLink()" title="Add Link"><i class='bx bx-link'></i></button>
                <button type="button" onclick="formatDoc('unlink')" title="Unlink"><i class='bx bx-unlink'></i></button>
                <button id="show-code" type="button">&lt;/&gt;</button> -->
            </div>

            <div id="blog-content">
               <div id="content" contenteditable="true" placeholder="Type your content..."></div>
                 <!-- <textarea name="content" id="content" placeholder="Type your content..."></textarea> -->
                 <textarea name="content" id="hiddenContent" style="display: none;"></textarea>
                 
            </div>

            <div id="blog-submit">
                <input type="submit" value="Send" name="action">
            </div>
        </form>
    </section>

    <script>
      
       function formatDoc(cmd) {
            document.execCommand(cmd);
        }
       
        function validateForm(event) {
/*     	    const title = document.getElementById("title").value.trim(); */
    	    const contentDiv = document.getElementById("content");
    	    const content = contentDiv.innerText.trim();
/*     	    const errorMessage = document.getElementById("error"); */

    	    /* errorMessage.textContent = ""; */ // Clear previous error

/*     	    if (!title) {
    	        errorMessage.textContent = "Title cannot be empty.";
    	        return false;
    	    }

    	    if (!content || content === "<br>") {
    	        errorMessage.textContent = "Content cannot be empty.";
    	        return false;
    	    } */

    	    // Copy contenteditable data to hidden textarea for form submission
    	    document.getElementById("hiddenContent").value = content;

    	    return true; // Allow form submission
    	} 
        
    </script>
</body>

</html>
