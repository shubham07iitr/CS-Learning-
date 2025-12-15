import express, { request } from "express"
import axios from "axios"

const app = express()
const port = 3000;
const API_URL = "https://v2.jokeapi.dev/joke/"
const categories = ["Any", "Programming", "Misc", "Dark", "Pun", "Spooky", "Christmas"]
const languages = ["cs", "de", "en","es", "fr", "pt"]
const jokeType = ["single", "twopart"]

app.use(express.static("public"))
app.use(express.urlencoded({ extended: true })) 

//Defining the home route
app.get("/", (req, res) => {
    res.render("index.ejs", {
        categories: categories,
        languages: languages,
        jokeType: jokeType
    })
})

app.post("/getJoke", async (req, res) => {
    console.log("just checking if things are working");
    try {
        const category = req.body.category;
        const language = req.body.language;
        const contains = req.body.contains
        const config = {params: {
            lang: language,
            contains: contains,
            type: "single"
        }}
        const baseURL = API_URL + "/" + category
        const response = await axios.get(baseURL, config)
        console.log(response)
        const joke = response.data.joke
        console.log(joke)
        res.render("index.ejs", {
            categories: categories,
            languages: languages , 
            jokeType: jokeType,
            joke: joke
        })
    } catch(error) {res.render("index.ejs", {
            categories: categories,
            languages: languages , 
            jokeType: jokeType,
            joke: error.message

    })}
})

//Defining the listening port
app.listen(port, () => {
    console.log(`Server is now running on port ${port}`)
})