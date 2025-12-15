var buttonColours = ["red", "blue", "green", "yellow" ]
var gamePattern = []
var userClickedPattern = [];
var level = 0;
var clickCounter = 0;

function nextSequence() {
    var randomNumber = Math.floor(Math.random()*4);
    var randomChosenColor = buttonColours[randomNumber];
    gamePattern.push(randomChosenColor);
    $("#"+randomChosenColor).fadeOut(100).fadeIn(100);
    playSound(randomChosenColor);
    level++
    $("h1").text("Level " + level);


}   

$(".btn").click(function(event) {
    var userChosenColor = event.target.id ;
    userClickedPattern.push(userChosenColor);
    console.log(userClickedPattern);
    playSound(userChosenColor);
    animatePress(userChosenColor);
    console.log(gamePattern);
    console.log(clickCounter);
    console.log(level);

    for (var i = 0; i < clickCounter+1; i++) {
        if (gamePattern[i] !== userClickedPattern[i]) {
            $("h1").text("Game Over, Press Any Key to Restart");
            $("body").addClass("game-over")
            setTimeout(function() {
                $("body").removeClass("game-over");
                }, 100);
            gamePattern = [];
            userClickedPattern= [];
            level = 0;
            clickCounter = 0;
            break;
        } }
    clickCounter++ 
    if (clickCounter === level) {
        userClickedPattern = [];
        clickCounter = 0;
        nextSequence()
    }
    })

function playSound(name) {
    var source = "./sounds/" +name + ".mp3"
    var audio = new Audio(source)
    audio.play()

}

function animatePress(currentColor) {
    $("."+currentColor).addClass("pressed");
    setTimeout(function() {
        $("."+currentColor).removeClass("pressed");
    }, 100);
}

$(document).keydown(function() {
    if (($("h1").text() === "Press A Key to Start" || $("h1").text() === "Game Over, Press Any Key to Restart")  && level ===0  ) {
        nextSequence();
    }
})

