//Importing relevant modules
import express from "express"
import bodyParser from "body-parser"


const app = express();
const today = new Date(); //creating a date object
const dayNumber = 4;
const port = 3000;

//Importing the EJS
app.set("view engine", "ejs");


//Defining the GET handler
app.get("/", (req, res) => {
    res.render("index", {dayNumber: dayNumber})
})


//Activating the server on 3000 port
app.listen(port, () => {
    console.log("Server started on port 3000");
})