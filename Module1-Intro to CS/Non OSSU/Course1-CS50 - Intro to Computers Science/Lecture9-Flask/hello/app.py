from flask import Flask , render_template, request
app = Flask(__name__) ## for creating a web app and __name__ refers to the current file

@app.route("/", methods = ["GET", "POST"]) ## this basically means the next lock of code to be run whenever someone visits the home pade

def index():
    ##name = request.args.get("name", "world")
    if request.method == "GET":
        return render_template("index.html")
    elif request.method == "POST":
        return render_template("greet.html", name = request.form.get("name", "world"))


if __name__ == "__main__":
    app.run(debug=True)


