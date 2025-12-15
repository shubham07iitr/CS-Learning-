import express from "express"
import axios from "axios"

const app = express()
const port = 3000

//Defining the body parser for query params
app.use(express.urlencoded({ extended: true })) 
//Defining the useage of public folders
app.use(express.static("public"))

//Defining the data structure for the blog

var blog = [{
    id: 0,
    title: "The Secret Life of Coffee Beans",
    details: "Have you ever wondered what happens to coffee beans before they reach your morning cup? These little brown gems travel thousands of miles, from volcanic slopes in Ethiopia to misty mountains in Colombia. Farmers handpick each cherry at peak ripeness, then carefully process and dry them under the sun. The beans are sorted, roasted to perfection, and shipped across oceans. Next time you take that first sip, remember you're tasting the dedication of countless people who transformed a simple fruit into liquid gold. Coffee isn't just a beverage—it's a global journey in every cup."},
    {
        id: 1,
        title: "Why I Started Running at 40 (And You Should Too)",
        details: "For years, I thought running was for young, athletic people—not for someone like me who got winded climbing stairs. But on my 40th birthday, something clicked. I laced up a cheap pair of sneakers and jogged around the block, barely making it 200 meters before gasping for air. Six months later, I completed my first 5K. Running didn't just transform my body; it cleared my mind, boosted my confidence, and taught me that age is just a number. If you've been putting off that first run, stop making excuses. Your future self will thank you."
    },
    {
        id: 2,
        title: "The Lost Art of Handwritten Letters",
        details: "In a world of instant messages and emoji reactions, I recently sent my best friend a handwritten letter. No particular reason—just wanted to. Three weeks later, she called me crying (the good kind). She said no one had written her a real letter in over a decade. There's something magical about pen on paper, the effort it takes, the permanence of ink. Your handwriting is uniquely yours, imperfections and all. Digital messages are convenient, but they lack soul. This weekend, grab some paper and write to someone you care about. Trust me, it'll mean more than you think."
    },
    {
        id: 3,
        title: "My Grandmother's Recipe Box Changed My Life",
        details: "After my grandmother passed away, I inherited a small wooden box filled with handwritten recipe cards, some dating back to the 1950s. At first, I tucked it away in a closet, too heartbroken to open it. But one rainy Sunday, I pulled out a card labeled 'Famous Chocolate Cake' and decided to bake it. As I mixed the ingredients, memories flooded back—her laughter, her flour-dusted apron, the way she'd let me lick the spoon. That cake didn't just taste delicious; it connected me to her in a way I didn't expect. Now I cook from that box every week, and each recipe is a conversation with her I thought I'd lost forever."
    },
    {
        id: 4,
        title: "I Deleted Social Media for 30 Days and Here's What Happened",
        details: "Last month, I did something radical—I deleted Instagram, Twitter, and Facebook from my phone for 30 days. The first week was brutal. I reached for my phone every five minutes like a reflex, only to stare at my home screen confused. But by week two, something shifted. I started reading actual books again, having real conversations at dinner, and noticing the world around me. I realized I'd been living life through a screen, curating moments instead of experiencing them. When the 30 days ended, I reinstalled the apps but with new boundaries. Turns out, I don't need to share everything to prove I'm living well."
    }
]




//Defining the home route handler
app.get("/", (req, res) => {
    res.render("index.ejs")
})


//Defining the view page route handler
app.get("/view", (req, res) => {
    res.render("view.ejs", {
        blog: blog
    })
})

//Defining the individidual blog post handler along with delete button
app.get("/view/:id", (req, res) => {
    const id = Number(req.params.id);
    const blogIndividual = blog.find(b => b.id === id);  // ✅ Find by ID
    
    if (blogIndividual) {
        res.render("viewIndividual.ejs", { blogIndividual });
    } else {
        res.render("viewIndividual.ejs", { blogIndividual: null });
    }
});

//Defining hte savePost route handler
app.post("/savePost", (req,res)=> {
    console.log("Checking if things are working")
    const newId =blog.length
    const title = req.body.title
    const details = req.body.details
    const newEntry = {id: newId, title: title, details: details}
    blog.push(newEntry)
    res.render("view.ejs", {blog:blog})
})

//Defining hte deletePost route handler
app.post("/deletePost", (req, res)=> {
    console.log("Checking if things are working")
    const id = Number(req.body.id)
    const index = blog.findIndex(b => b.id === id);
    if (index !== -1) {
        blog.splice(index, 1);
    }
    
    res.render("view.ejs", {blog:blog})
})


//Defining the port to listen
app.listen(port, ()=> {
    console.log(`Server is now running on port ${port}`)
})