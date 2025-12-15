from flask import Flask, render_template

app = Flask(__name__)


##Defining route for index
@app.route('/')
def home():
    return render_template('index.html')  # or a child template


##Defining route for education
@app.route("/education")
def education():
    return render_template('education.html')

##Defining route for experience
@app.route("/experience")
def experience():
    return render_template('experience.html')

## Defining route for contact
@app.route("/contact")
def contact():
    return render_template("contact.html")


if __name__ == '__main__':
    app.run(debug=True)  # ← This enables auto-reload