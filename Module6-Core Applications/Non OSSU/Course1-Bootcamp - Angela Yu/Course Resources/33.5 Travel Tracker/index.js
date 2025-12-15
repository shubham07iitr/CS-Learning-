import express from "express";
import bodyParser from "body-parser";
import pg from "pg"

//Defining the objects for our project
const {Client} =pg;
const app = express();
const port = 3000;

//Defining the visited countries variable
var visited = [];

//Opening and closing the db and updating our visited countries
const db = new Client({
    user: "postgres",
    host: "localhost",
    database: "world",
    password: "Apr@2016",
    port: 5432,
})
db.connect();

//Defining the middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));


//Defining the home page route handler
app.get("/", async (req, res) => {
  const result = await db.query("Select country_code from visited_countries");
  let countries = []
  result.rows.forEach((country) => {countries.push(country.country_code);})
  console.log(result.rows)
  res.render("index.ejs", {total: countries.length ,countries: countries})
});

//Defining the add route handler to add visited countries directly through web
app.post("/add", async(req, res) => {
  const country_name = req.body.country.trim().toLowerCase()
  //lets run a check on the DB whether we have a country or not
  const result = await db.query( "SELECT country_coude, country_name FROM countries WHERE LOWER(country_name) = $1",[country_name])
  if (result.rows.length === 0) {res.send("Sorry, pls enter a correct country name")}
  else{
    await db.query("Insert into visited_countries (country_code) values ($1)", [result.rows[0]["country_coude"]])
    res.redirect("/")
  }
})

app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
