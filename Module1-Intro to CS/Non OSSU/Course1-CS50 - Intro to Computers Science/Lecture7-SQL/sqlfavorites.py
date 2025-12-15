from cs50 import SQL ##will help me import a DB file in python

db = SQL("sqlite:///favorites.db") ##creating an object db from favorites database //this is a normal python command

favorite = input("Favorite: ")

rows = db.execute("Select * from favorites where problem = ?", favorite) ##will execute the sql command for us, execute is a cs50 command

print(rows) ## rows is a list of dictionary and 1st element is only a dictioanry 

