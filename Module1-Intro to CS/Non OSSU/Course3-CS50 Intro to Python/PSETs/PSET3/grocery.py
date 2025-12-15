def main():
    grocery()

def grocery():
    grocery_dict = {}
    while True:
        try:
            item = input("Item name pls: ")
            if item.lower() in ["exit", "quit"]:
                break
            if item.upper() in grocery_dict:
                grocery_dict[item.upper()] += 1
            else:
                grocery_dict[item.upper()] = 1

        except EOFError:
            print("You have decided to exit the grocery item list")
            break
        
    grocery_dict = dict(sorted(grocery_dict.items()))
    for key, value in grocery_dict.items():
        print(f"{value} {key}")

if __name__  == "__main__":
    main()