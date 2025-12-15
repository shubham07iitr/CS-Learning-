//To see how the final website should work, run "node solution.js".
//Make sure you have installed all the dependencies with "npm i".
//The password is ILoveProgramming
import express, { urlencoded } from "express"
import { dirname } from "path";
import { fileURLToPath } from "url";
import bodyParser from "body-parser";

//Setting up the Constants
const __dirname = dirname(fileURLToPath(import.meta.url));
const app = express();
const port = 3000;
var password = "";

//Setting up the MW for body parser
app.use(bodyParser.urlencoded({extended: true}));

//Setting up the get route handler
app.get("/", (req, res) => {
    res.sendFile(__dirname + "/public/index.html");
})


//Setting up the POST handler
app.post("/check" , (req, res) => {
    if (req.body["password"] === "ILoveProgramming") {res.sendFile(__dirname + "/public/secret.html")}
    else {res.sendFile(__dirname + "/public/index.html")}
})


//Setting up the server first
app.listen(port, () => {
    console.log("Server has begun at port 3000");
})