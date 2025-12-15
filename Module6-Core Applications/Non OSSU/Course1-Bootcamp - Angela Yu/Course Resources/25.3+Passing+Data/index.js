import express from "express";
import bodyParser from "body-parser";

//Defining the constants
const app = express();
const port = 3000;

//Middleware to parse ddata from form to text
app.use(bodyParser.urlencoded({ extended: true }));

//Ensuring we can use ejs
app.set("view engine", "ejs");

//Defining the get handler
app.get("/", (req, res) => {
  res.render("index"); //for the first get request, we don't pass in any data
});


//Defining the post handler
app.post("/submit", (req, res) => {
  const nameLength = req.body["fName"].length + req.body["lName"].length;
  res.render("index", {length: nameLength});
});


//Activating the server
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
