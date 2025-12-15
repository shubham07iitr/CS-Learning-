import express from "express"
import pg from "pg"

//Defining the constant objects for app and port

const app = express();
const port = 3000;

//Defining the database
const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "books",
  password: "Apr@2016",
  port: 5432,
})

db.connect();

//Defining the middleware
app.use(express.urlencoded({extended: true}))
app.use(express.static("public"));

//Defining the route for homepage
app.get("/", async (req, res) => {
    const result = await db.query("select * from reviews")
    const books = result.rows
    res.render("index.ejs", {
        books: books
    })
})




//Defining the route for adding a new book

//Defining the route for saving a new book to DB

//Defining the route for sorting the books on homepage based on date
app.get("/sortRead", async (req,res)=> {
    const result = await db.query("select * from reviews order by date_read asc")
    const books = result.rows
    res.render("index.ejs", {
        books: books
    })
})

//Defining the route for sorting the books on homepage based on rating
app.get("/sortRating", async (req,res)=> {
    const result = await db.query("select * from reviews order by rating desc")
    const books = result.rows
    res.render("index.ejs", {
        books: books
    })
})




//Defiing the route to delete an individual book
app.post("/deleteBook", async (req, res)=> {
    const id = req.body.bookid
    await db.query("delete from reviews where id = ($1)" , [id])
    res.redirect("/")
})

//Defining the route for individual books
app.get("/:id" , async (req,res)=> {
    const id = req.params.id 
    const result = await db.query("select * from reviews where id = ($1)", [id])
    const book = result.rows[0]
    res.render("individualBook.ejs", {book:book})
})

//Starting the port to listen to our request
app.listen(port, ()=> {console.log(`Server running on port ${port}`)})