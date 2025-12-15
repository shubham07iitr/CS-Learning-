import express from "express";
import bodyParser from "body-parser";
import pg from "pg"

//Defining the connection with the DB
const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "permalist",
  password: "Apr@2016",
  port: 5432,
})
db.connect();
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

// let items = [
//   { id: 1, title: "Buy milk" },
//   { id: 2, title: "Finish homework" },
// ];



app.get("/", async (req, res) => {
  const result = await db.query("select * from items order by id asc")
  const items = result.rows
  console.log(items)
  res.render("index.ejs", {
    listTitle: "Today",
    listItems: items,
  });
});

app.post("/add", async (req, res) => {
  const item = req.body.newItem;
  try {
    await db.query("insert into items (title) values ($1)", [item])
    res.redirect("/");
  }
  catch(err) {console.log(err); res.send("Sorry data could not be added, pls try agian")}
});

app.post("/edit", async(req, res) => {
  const itemID = req.body.updatedItemId
  const newTitle = req.body.updatedItemTitle
  try {
    await db.query("update items set title = ($1) where id = ($2)", [newTitle, itemID])
    res.redirect("/")
  } catch(err) {console.log(err);  res.send("sorry request could not be completed, pls try again ")}
});

app.post("/delete", async (req, res) => {
  const itemID = req.body.deleteItemId
  try {
    await db.query("delete from items where id = ($1)", [itemID])
    res.redirect("/")
  } catch(err) {console.log(err); res.send("sorry your request could not be completed pls try again")}
});

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
