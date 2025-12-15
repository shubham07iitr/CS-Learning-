import express from "express";

const app = express();
const port = 3000;

//Defining the MW function which will log both method and url of the request
const logger = (req, res, next) => {
  console.log("Request method: ", req.method);
  console.log("Request method: ", req.url);
  next(); //This goes to the next item in the codebase in order - either another MW or handler
}
//Using the custom MW function
app.use(logger);

app.get("/", (req, res) => {
  res.send("Hello");
});

app.listen(port, () => {
  console.log(`Listening on port ${port}`);
});
