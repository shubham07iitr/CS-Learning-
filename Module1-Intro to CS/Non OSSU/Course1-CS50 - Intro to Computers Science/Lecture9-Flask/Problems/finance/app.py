import os

from cs50 import SQL
from flask import Flask, flash, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash
from datetime import datetime
from helpers import apology, login_required, lookup, usd

# Configure application
app = Flask(__name__)

##Generating a random secret_key for flash to work
app.secret_key = "my-secret-key-123"
# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")




@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
@login_required
def index():
    """Show portfolio of stocks"""
    stocks = db.execute("SELECT symbol, quantity FROM transactions where user_id  = ? group by 1", session["user_id"])
    cash_left = round(db.execute("SELECT cash from users where id = ?", session["user_id"])[0]["cash"], 2)
    for i in stocks:
        quote_result = lookup(i["symbol"])
        i["ltp"] = quote_result["price"]
        i["value"] = round(quote_result["price"]*i["quantity"], 2)
    total_value = 0
    for i in stocks:
        total_value += i["value"]
    round(total_value,2)
    return render_template("index.html", stocks = stocks, cash_left = cash_left, total_value = total_value)



@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    """Buy shares of stock"""
    if request.method == "GET":
        return render_template("buy.html")
    elif request.method == "POST":
        try:
            symbol = request.form.get("symbol")
            quantity = int(request.form.get("quantity"))
            if symbol and quantity:
                quote_result = lookup(symbol)
                if quote_result:
                    id = session["user_id"]
                    cash_left = db.execute("SELECT cash from users where id = ?", id)[0]["cash"]
                    if quote_result["price"] * quantity <= cash_left:
                        new_cash = cash_left - quote_result["price"] * quantity
                        db.execute("UPDATE users SET cash = ? where id = ?", new_cash, id)
                        current_date = datetime.now()
                        db.execute("INSERT into transactions (user_id, date, symbol, quantity, price, total_value) VALUES (?, ?, ?, ?, ?, ?)", id, current_date, symbol, quantity , quote_result["price"], quote_result["price"]*quantity) 
                        flash(f"Your purchase was succesful, new cash balance for you is ${new_cash}")
                        return redirect("/")
                    else:
                        return apology("Sorry you don't have enough cash in your account", 403)
                else:
                    return apology("Sorry please enter a valid symbol", 403)
            else:
                return apology("You cannot leave symbol or quantity blank", 403)
        except:
            return apology("Sorry your purchase could not be completed, pls try again", 403)


@app.route("/history")
@login_required
def history():
    """Show history of transactions"""
    stocks = db.execute("SELECT symbol, date, txn_type, quantity, price FROM transactions where user_id  = ? order by  2", session["user_id"])
    cash_left = round(db.execute("SELECT cash from users where id = ?", session["user_id"])[0]["cash"], 2)
    return render_template("history.html", stocks = stocks, cash_left = cash_left)
    


@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id"]

        # Redirect user to home page
        return redirect("/")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/quote", methods=["GET", "POST"])
@login_required
def quote():
    if request.method == "GET":
        return render_template("quote.html")
    elif request.method == "POST":
        symbol = request.form.get("symbol")
        try:
            quote_result = lookup(symbol)
            if quote_result:
                return render_template("quote.html", message_quote = quote_result)
            else:
                return apology("Please enter a valid symbol", 403)
        except:
            return apology("Your request could not be processed, pls try again", 403)



@app.route("/register", methods=["GET", "POST"])
def register():
    if request.method == "GET":
        return render_template("register.html")
    if request.method == "POST":
        name = request.form.get("username")
        password = request.form.get("password")
        confirmp = request.form.get("confirmpassword")
        names = db.execute("SELECT username from users where username = ?", name)
        print(names)
        if name and password and confirmp:
            if password != confirmp:
                return apology("Passwords don't match, pls try again", 403)
            elif len(names) != 0:
                return apology("Username already in DB, pls try again with a different username", 403)
            try:
                pass_hash = generate_password_hash(password)
                db.execute("INSERT INTO users (username, hash) VALUES (?,?)", name, pass_hash)
                flash("You have succesfully registered")
                return render_template("layout.html") ##this is temporary only until we figure out the root page
            except:
                return apology("Sorry we could not register you, please try again", 403)
        else:
            return apology("Sorry please don't leave any field blank" ,403)


@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    """Sell shares of stock"""
    if request.method == "GET":
        stocks = db.execute("SELECT DISTINCT symbol FROM transactions WHERE user_id = ?", session["user_id"])
        return render_template("sell.html", stocks=stocks)
    elif request.method == "POST":
        try:
            symbol = request.form.get("symbol")
            quantity = int(request.form.get("quantity"))
            if symbol and quantity:
                # Fixed: Use SUM(quantity) and alias as 'total'
                qty_bought = db.execute("SELECT SUM(quantity) as total FROM transactions WHERE symbol = ? AND user_id = ? AND txn_type = 'BUY'", symbol, session["user_id"])[0]["total"]
                qty_sold = db.execute("SELECT SUM(quantity) as total FROM transactions WHERE symbol = ? AND user_id = ? AND txn_type = 'SELL'", symbol, session["user_id"])[0]["total"]
                qty_bought = qty_bought or 0
                qty_sold = qty_sold or 0
                qty_owned = qty_bought - qty_sold
                if quantity <= qty_owned:  # Fixed: <= instead of 
                    cash_left = db.execute("SELECT cash FROM users WHERE id = ?", session["user_id"])[0]["cash"]  # Fixed: use session["user_id"]
                    quote_result = lookup(symbol)
                    ltp = quote_result["price"]
                    new_cash = cash_left + ltp * quantity
                    current_date = datetime.now()
                    db.execute("UPDATE users SET cash = ? WHERE id = ?", new_cash, session["user_id"])
                    db.execute("INSERT INTO transactions (user_id, date, txn_type, symbol, quantity, price, total_value) VALUES (?, ?, 'SELL', ?, ?, ?, ?)", session["user_id"], current_date, symbol, quantity, quote_result["price"], quote_result["price"] * quantity) 
                    flash("Your transaction was successful")  # Fixed typo
                    return redirect("/")
                else:
                    return apology("Sorry qty cannot be more than what you own", 403)
            else:
                return apology("Cannot leave any field blank please try again", 403)
        except:
            return apology("Your request was not successful, please try again", 403)  # Fixed typo


##Just a test framework
@app.route("/test-flash")
def test_flash():
    flash("This is a test message!")
    return render_template("layout.html")


if __name__ == '__main__':
    app.run(debug=True)  # ← This enables auto-reload