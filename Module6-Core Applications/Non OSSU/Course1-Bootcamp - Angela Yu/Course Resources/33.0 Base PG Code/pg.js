import Client from "pg"
const { Client } = pg; 
const db = new Client({
    user: "username",
    host: "localhost",
    database: "mydatabase",
    port: 5432,
})
db.connect();
db.query("Select * from users", (err, res)=> {
    if (err) {console.error("Error executing query", err.stack)}
    else {console.log("User data:", res.rows)}
    db.end()
})


