/**
 * 
 */
 
     // Function to handle login form submission via API (using fetch)
async function handleLogin(event) {
    event.preventDefault();  // Prevent form submission

    // Get email and password values from input fields
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    /* console.log(`Email: ${email}`);
    console.log(`Password: ${password}`);
    console.log(email);
    console.log(password); */

    console.log("========",email,password);
    
    const response = await fetch('/navigation_project/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: email,
            password: password,
        }),
        credentials: 'include',
        credentials: 'same-origin',
    });

   /*  console.log(`Email: ${email}`);
    console.log(`Password: ${password}`); */
    console.log(response)
    if (response.ok) {
        const result = await response.json();
        if (result.status === "success") {
            // Store user details in localStorage or sessionStorage
            localStorage.setItem("user", JSON.stringify(result.user));

            // Redirect to navigation page
            window.location.href = "/navigation_project/navigation_project.jsp";
        } else {
            alert(result.message);
        }
    } else {
        alert("Login failed. Please try again.");
    }


/*            const data = await response.json();

    // Handle response
    if (data.status === 'error') {
        document.getElementById('error-message').innerText = data.message;
    } else if (data.status === 'success') {
        // Redirect to another page after successful login
        window.location.href = "/navigation_project/navigation_project.jsp";
    } */
}