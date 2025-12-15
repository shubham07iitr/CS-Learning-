import express from "express";
import morgan from "morgan";


const app = express();
const port = 3000;

//Defning the logger
app.use(morgan("combined"));


//Defining the get method
app.get("/", (req, res) => {
  res.send("Hello");
});

app.listen(port, () => {
  console.log(`Listening on port ${port}`);
});
