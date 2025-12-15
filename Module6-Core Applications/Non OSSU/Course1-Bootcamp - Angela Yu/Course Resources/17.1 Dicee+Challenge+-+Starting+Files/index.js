var randNo1 = Math.floor(Math.random()*6) + 1;
var newImg1 = "./images/dice" + randNo1 + ".png";
var randNo2 = Math.floor(Math.random()*6) + 1;
var newImg2 = "./images/dice" + randNo2 + ".png"
document.querySelector(".img1").setAttribute("src", newImg1);    
document.querySelector(".img2").setAttribute("src", newImg2);    
if (randNo1 > randNo2) {document.querySelector("h1").textContent = "Player 1 wins"}
else if (randNo2 === randNo1) {document.querySelector("h1").textContent = "Tie! Try Again"}
else  {document.querySelector("h1").textContent = "Player 2 wins"}