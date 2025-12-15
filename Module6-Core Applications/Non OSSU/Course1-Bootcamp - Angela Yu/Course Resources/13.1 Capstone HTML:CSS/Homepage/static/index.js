//Event listener for extra page not available

document.addEventListener('DOMContentLoaded', function() {
    document.querySelector("#extra").addEventListener('click', (e) => {
        e.preventDefault();  // Prevent navigation
        alert("Sorry this page is under construction right now");
    });
});