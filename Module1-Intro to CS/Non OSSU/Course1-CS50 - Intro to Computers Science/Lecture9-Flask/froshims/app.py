from flask import Flask , render_template, request

app = Flask(__name__) 

REGISTRANTS = {}
SPORTS = [
    "Basketall",
    "Soccer",
    "Ultimate Frizbee"
]

@app.route("/")
def index():
    return render_template("index.html",  sports = SPORTS)

@app.route("/register", methods = ["POST"])
def register():
    name = request.form.get("name")
    if not name:
        return render_template("failure.html") ## because we dont want user to input a blank name
    sport = request.form.get("sport")
    if sport not in SPORTS:
        return render_template("failure.html") ## because we dont want user to input a sport outside of our list
    REGISTRANTS[name] = sport
    return render_template("success.html")

@app.route("/registrants")
def registrants():
    return render_template("registrants.html", registrants = REGISTRANTS) ## Calling another webpage

# This is the key part for auto-reload:
if __name__ == "__main__":
    app.run(debug=True)

