import csv 

with open("favorites.csv", 'r') as file:
    reader = csv.DictReader(file) 
    counts = {}
    ##scratch =  c = python = 0
    for row in reader: ## Dictreader will give a dictionary for each row where key wold be the header
        favorite = row["language"]
        if favorite in counts:
            counts[favorite] += 1
        else:
            counts[favorite] =1 
        
        # if favorite == "Scratch":
        #     scratch += 1
        # elif favorite == "C":
        #     c += 1
        # elif favorite == "Python":
        #     python += 1**/


for favorite in sorted(counts, key = lambda language: counts[language], reverse= True): ## will give results in descending order of values
    print(f"{favorite}: {counts[favorite]}")
##print(f"Scratch: {scratch}, C: {c}, Python, {python}")    

