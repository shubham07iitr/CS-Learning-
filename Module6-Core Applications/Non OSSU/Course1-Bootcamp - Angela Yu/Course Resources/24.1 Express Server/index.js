import express from "express";
const app = express();

//Adding some get method for root
app.get("/", (req,res) => {
  res.send("Hello");
})

  //Adding some get method for /about endpoint
  app.get("/about", (req,res) => {
    res.send("About me: Shubham Gupta");
  })
  //Adding the post method
  app.post("/register", (req,res) => {
    res.sendStatus(201);
  })

  //Adding the put method
  app.put("/user/shubham", (req,res) => {
    res.sendStatus(200);
  })

  //Adding the patch method
  app.patch("/user/shubham", (req,res) => {
    res.sendStatus(200);
  })

  //Adding the delete method
  app.delete("/user/shubham", (req,res) => {
    res.sendStatus(200);
  })


//Creating the server port 
app.listen(3000, () => {
  console.log("Server running on port 3000");
})