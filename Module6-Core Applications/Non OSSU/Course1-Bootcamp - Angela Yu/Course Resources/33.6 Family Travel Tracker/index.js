import express from "express";
import bodyParser from "body-parser";
import pg from "pg";

const app = express();
const port = 3000;

const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "world",
  password: "Apr@2016",
  port: 5432,
});
db.connect();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

let currentUserId = 1;

let users = [
  { id: 1, name: "Angela", color: "teal" },
  { id: 2, name: "Jack", color: "powderblue" },
];

// async function checkVisisted() {
//   const result = await db.query("SELECT country_code FROM visited_countries");
//   const test = await db.query("select * from users");
//   console.log(test)
//   let countries = [];
//   result.rows.forEach((country) => {
//     countries.push(country.country_code);
//   });
//   return countries;
// }

//Defining the home page route handler by just calling the get function for individual user 1
app.get("/", async (req, res) => {
  res.redirect("/1")
});

//Defining the add function for visited countries for that specific individual user
app.post("/add", async (req, res) => {
  const input = req.body["country"];
  const id = req.body.user;
  try {
    const result = await db.query(
      "SELECT country_coude FROM countries WHERE LOWER(country_name) LIKE '%' || $1 || '%';",
      [input.toLowerCase()]
    );

    const data = result.rows[0];
    const countryCode = data.country_coude;
    try {
      await db.query(
        "INSERT INTO visited_countries (country_code, user_id) VALUES ($1, $2)",
        [countryCode, id]
      );
      res.redirect(`/${id}`);
    } catch (err) {
      console.log(err);
    }
  } catch (err) {
    console.log(err);
  }
});

//Defining the user route handler - if we click on add new user, a new page displayed, else, we redirect to /user_id route handler
app.post("/user", async (req, res) => {
  if (req.body.add === "new") {res.render("new.ejs")}
  else {
    const id = req.body.user
    res.redirect(`/${id}`)
  }
});

app.post("/new", async (req, res) => {
  //Hint: The RETURNING keyword can return the data that was inserted.
  //https://www.postgresql.org/docs/current/dml-returning.html
  
  const userName = req.body.name 
  const userColor = req.body.color 
  const result = await db.query("INSERT INTO users (name, color) VALUES ($1, $2) RETURNING id",[userName, userColor]);
  const newUserId = result.rows[0].id;
  console.log("New user ID:", newUserId);
  res.redirect(`/${newUserId}`); //returning to this new user's home page
});

//Defining.a function to get the page for a particular user based on user Id
app.get("/:id", async (req, res) =>{
  //Getting all the users from the DB
  const id = req.params.id
  const users = await db.query("select * from users")
  const result = await db.query("select country_code from visited_countries where user_id = $1", [id])
  const userColor = await db.query("select color from users where id = $1", [id])
  const color = userColor.rows[0].color
  const currentUser = users.rows.find(u => u.id == id);
  let countries = []
  result.rows.forEach((country) => {countries.push(country.country_code);})
  console.log(countries)
  res.render("index.ejs", {
    countries: countries,
    total: countries.length,
    users: users.rows,
    color: color,    
    user: currentUser
  })
})


app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);

});
