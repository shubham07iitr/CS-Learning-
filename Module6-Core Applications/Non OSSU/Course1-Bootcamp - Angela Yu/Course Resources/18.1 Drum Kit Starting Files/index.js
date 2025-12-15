
// function handleClick() {
//     alert("I got clicked")
// }

var buttonArr = [1,2,3,4,5,6,7]

for (var i = 0; i < buttonArr.length; i++) {
    document.querySelectorAll(".drum")[i].addEventListener("click", function() {
        var buttonInnerHTML = this.innerHTML;
        makeSound(buttonInnerHTML);
        buttonAnimation(buttonInnerHTML);
      }

    )}

document.addEventListener("keydown", function (event) {
    makeSound(event.key);
    buttonAnimation(event.key);
})



function makeSound(key) {
        switch (key) {
        case "w":
            var tom1 = new Audio ("sounds/tom-1.mp3");
            tom1.play();
                
            
            break;

        case "a":
            var tom2 = new Audio ("sounds/tom-2.mp3");
            tom2.play();
            break;
      
        default:
            break;}
}


function buttonAnimation(currentKey) {
    var activeButton = document.querySelector("."+currentKey);
    activeButton.classList.add("pressed");
    setTimeout(function() {
        activeButton.classList.remove("pressed");
    }, 100);

}