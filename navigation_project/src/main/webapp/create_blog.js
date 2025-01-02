/**
 * 
 */
 
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
    	
    	
    	// JavaScript to control the loader
    const loader = document.getElementById('loader');
    const sendButton = document.getElementById('submit');
    
      // Add click event listener to the button
    sendButton.addEventListener('click', () => {
      // Show the overlay
      loader.style.display = 'flex';

    });