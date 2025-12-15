import express from "express";
import { dirname } from "path";
import { fileURLToPath } from "url";
import bodyParser from "body-parser";
const __dirname = dirname(fileURLToPath(import.meta.url));

const app = express();
const port = 3000;

//Defining the body parser
app.use(bodyParser.urlencoded({extended: true}));

//Defining the POST handler
app.post("/submit", (req, res) => {
  console.log(req.body);
})

//Defining the get route handler
app.get("/", (req, res) => {
  res.sendFile(__dirname + "/public/index.html");
});

app.listen(port, () => {
  console.log(`Listening on port ${port}`);
});
