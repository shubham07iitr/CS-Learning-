import express from "express";
import bodyParser from "body-parser";

//Defining Constants
const app = express();
const port = 3000;

//Getting the EJS file from views
app.set("view engine", "ejs");


//Middleware to parse ddata from form to text
app.use(bodyParser.urlencoded({ extended: true }));

//Defining the app method for using the static files
app.use(express.static("public"));

/* Write your code here:
Step 1: Render the home page "/" index.ejs - Done
Step 2: Make sure that static files are linked to and the CSS shows up. - Done
Step 3: Add the routes to handle the render of the about and contact pages.
  Hint: Check the nav bar in the header.ejs to see the button hrefs
Step 4: Add the partials to the about and contact pages to show the header and footer on those pages. */

//Defining the GET handler for HOME Page
app.get("/", (req, res) => {
  res.render("index")
})

//Defining the GET handler for ABOUT page
app.get("/about", (req, res) => {
  res.render("about");
})

//Defining the GET handler for CONTACT page
app.get("/contact", (req, res) => {
  res.render("contact");
})



app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
