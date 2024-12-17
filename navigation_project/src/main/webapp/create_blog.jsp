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

        #blog-content textarea {
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

        /* Responsive Design */
        @media (max-width: 768px) {
            #blog-title input[type="text"] {
                width: 90%;
                height: 50px;
            }

            #blog-content textarea {
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

            #blog-content textarea  {
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
        <form action="Create_blog" method="post">
            <div id="blog-title">
                <input type="text" name="title" id="title" placeholder="Type Title">
            </div>

            <div class="btn-toolbar">
                <button type="button" onclick="formatDoc('bold')" title="Bold"><i class='bx bx-bold'></i></button>
                <button type="button" onclick="formatDoc('italic')" title="Italic"><i class='bx bx-italic'></i></button>
                <button type="button" onclick="formatDoc('underline')" title="Underline"><i class='bx bx-underline'></i></button>
                <button type="button" onclick="formatDoc('strikeThrough')" title="StrikeThrough"><i class='bx bx-strikethrough'></i></button>
                <button type="button" onclick="formatDoc('justifyLeft')" title="JustifyLeft"><i class='bx bx-align-left'></i></button>
                <button type="button" onclick="formatDoc('justifyCenter')" title="JustifyCenter"><i class='bx bx-align-middle'></i></button>
                <button type="button" onclick="formatDoc('justifyRight')" title="JustifyRight"><i class='bx bx-align-right'></i></button>
                <button type="button" onclick="formatDoc('insertOrderedList')" title="Ordered List"><i class='bx bx-list-ol'></i></button>
                <button type="button" onclick="formatDoc('insertUnorderedList')" title="Unordered List"><i class='bx bx-list-ul'></i></button>
                <button type="button" onclick="addLink()" title="Add Link"><i class='bx bx-link'></i></button>
                <button type="button" onclick="formatDoc('unlink')" title="Unlink"><i class='bx bx-unlink'></i></button>
                <button id="show-code" type="button">&lt;/&gt;</button>
            </div>

            <div id="blog-content">
                <!-- <div id="content" contenteditable="true" placeholder="Type your content..."></div> -->
                 <textarea name="content" id="content" placeholder="Type your content..."></textarea>
            </div>

            <div id="blog-submit">
                <input type="submit" value="Send" onclick="submitContent()">
            </div>
        </form>
    </section>

    <script>
        const content = document.getElementById('content');
        const showCode = document.getElementById('show-code');
        let codeViewActive = false;

        function formatDoc(cmd, value = null) {
            document.execCommand(cmd, false, value);
        }

        function addLink() {
            const url = prompt('Insert URL');
            if (url) formatDoc('createLink', url);
        }

        showCode.addEventListener('click', () => {
            codeViewActive = !codeViewActive;
            if (codeViewActive) {
                content.textContent = content.innerHTML;
            } else {
                content.innerHTML = content.textContent;
            }
        });

        function submitContent() {
            const blogContent = content.innerHTML;
            /* alert("Blog content submitted:\n" + blogContent); */
        }
    </script>
</body>

</html>
