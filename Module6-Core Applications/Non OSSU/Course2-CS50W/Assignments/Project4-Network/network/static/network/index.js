//Defining the logic for edit button


document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll(".editButton").forEach(button => {  // Fixed: added dot and proper forEach
        button.addEventListener('click', function() {  // Use regular function for 'this'
            if (this.textContent === "Edit") {  // Fixed: textContent not value
                const divElement = this.parentElement;
                const postDetails = divElement.querySelector(".actualPostDetails");
                const textElement = document.createElement("textarea");
                textElement.value = postDetails.textContent;
                textElement.className = "editPostTextArea form-control";
                postDetails.replaceWith(textElement);
                this.textContent = "Save Post";  // Fixed: textContent
            } else {
                const textarea = this.parentElement.querySelector("textarea");
                const newPostDetails = textarea.value;
                const postID = parseInt(this.dataset.postid);
                
                // Get CSRF token
                //const csrftoken = document.querySelector('[name=csrfmiddlewaretoken]').value;
                
                fetch('/editPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        //'X-CSRFToken': csrftoken
                    },
                    body: JSON.stringify({
                        newPost: newPostDetails,
                        postID: postID
                    })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        // Replace textarea with h6
                        const newH6 = document.createElement("h6");
                        newH6.className = "actualPostDetails";
                        newH6.textContent = newPostDetails;
                        textarea.replaceWith(newH6);
                        this.textContent = "Edit";
                    }
                });
            }
        });
    });
});

//Defining the logic for Like and Unlike

document.addEventListener('DOMContentLoaded', function() {
    
    
    document.querySelectorAll(".likes").forEach(likeElement => {  // Fixed: querySelectorAll + forEach
        likeElement.addEventListener('click', function() {
            //const csrftoken = document.querySelector('[name=csrfmiddlewaretoken]').value;  // Get CSRF token
            const postID = parseInt(this.dataset.postid);
            const username = this.dataset.user;
            const action = this.dataset.state;  // Fixed: use data-state
            
            fetch('/like', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    //'X-CSRFToken': csrftoken
                },
                body: JSON.stringify({
                    postID: postID,
                    username: username,
                    action: action
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    let currentCount = parseInt(this.dataset.count);  // Fixed: parse to int
                    
                    if (action === "Like") {
                        let newCount = currentCount + 1;
                        this.dataset.state = "Unlike";  // Fixed: use data-state
                        this.dataset.count = newCount;
                        this.innerHTML = `<span>❤️</span> ${newCount}`;  // Fixed: template literal
                    } else {
                        let newCount = currentCount - 1;
                        this.dataset.state = "Like";
                        this.dataset.count = newCount;
                        this.innerHTML = `<span>❤️</span> ${newCount}`;
                    }
                }
            });
        });
    });
});