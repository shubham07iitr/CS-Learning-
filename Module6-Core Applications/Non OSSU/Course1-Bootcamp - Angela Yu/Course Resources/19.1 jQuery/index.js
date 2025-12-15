$("h1").css("color", "red")

$("h1").click(function() {$("h1").css("color" ,"black")})

//W/o jQuery
for (var i = 0; i < 5; i++) {
    document.querySelectorAll("button")[i].addEventListener("click", function() {
        document.querySelector("h1").style.color = "purple";
    })}

//W/ JQuery    
$("button").click(function() {$("h1").css("color", "purple")});

//Keypress

$(document).keydown(function(event) {
  $("h1").text(event.key);
});

//On keyword
$("h1").on("click", function () {$("h1").css("color", "red")})


//Animations using jQuery
$("button").click(function() {
    $("h1").slideDown().slideUp().animate({
        opacity: 0.3});
})