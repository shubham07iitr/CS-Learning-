document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('form').addEventListener('submit', function(event) 
    {
        let name = document.querySelector('#name').value; //we are identiyfing the valye of id name and assigning it into name 
        alert('hello, ' + name);  //this is an inbuilt function in JS 
        event.preventDefault();
    });
});     