/**
 * 
 */
 //For scrolling to top
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

		//For Image enlarging
		function openModal(imageSrc) {
			const modal = document.getElementById("imageModal");
			const modalImage = document.getElementById("modalImage");

			modalImage.src = imageSrc;

			modal.style.display = "block";
		}

		function closeModal() {
			const modal = document.getElementById("imageModal");

			modal.style.display = "none";
		}

		/*   //For searching
		  document.getElementById("searchButton").addEventListener("click" , function() {
		      const query =   document.getElementById("searchInput").value.toLowerCase();
		      const blogs = document.querySelectorAll(".blog-post");
		

		    blogs.forEach(blog => {

		        const title = blog.querySelector("h2").innerText.toLowerCase();

		        if(title.includes(query)){
		            blog.style.display = "block";
		        }
		        else{
		            blog.style.display = "none";
		        }
		    }); 
		    }); */
		    
// Listen for input in the search bar
document.getElementById("searchInput").addEventListener("input", function () {
    const searchInput = this.value.trim();
    
    console.log("searchInput: " + searchInput);
    console.log("searchInput length: " + searchInput.length);

    if (searchInput.length > 0) {
     console.log("searchInput length inside IF: " + searchInput.length);
        // Make an AJAX request to the server for suggestions
        fetch(`/navigation_project/List_blog?action=autocomplete&searchKeyword=${encodeURIComponent(searchInput)}`)
            .then(response => response.json())
            .then(data => {
                const suggestionBox = document.getElementById("suggestionBox");
                suggestionBox.innerHTML = "";

                if (data.length > 0) {
                    suggestionBox.style.display = "block";
                    suggestionBox.style.borderRadius = "10px";

                    data.forEach(suggestion => {
                        const suggestionItem = document.createElement("div");
                        suggestionItem.textContent = suggestion;
                        suggestionItem.style.padding = "8px";
                        suggestionItem.style.cursor = "pointer";
                        suggestionItem.addEventListener("click", function () {
                            document.getElementById("searchInput").value = suggestion;
                            suggestionBox.style.display = "none";
                        });
                        suggestionBox.appendChild(suggestionItem);
                    });
                } else {
                    suggestionBox.style.display = "none";
                }
            })
            .catch(error => {
                console.error("Error fetching autocomplete suggestions:", error);
            });
    } else {
        document.getElementById("suggestionBox").style.display = "none";
    }
});

//For clicking outsite the suggestion box
document.addEventListener("click", function (event) {
    if (!event.target.closest('#searchInput')) {
        document.getElementById("suggestionBox").style.display = "none";
    }
});

