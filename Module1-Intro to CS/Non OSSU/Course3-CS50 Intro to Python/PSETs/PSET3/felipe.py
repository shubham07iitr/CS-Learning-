def main():
    order()

def order():
    menu = {
        "Baja Taco": 4.25,
        "Burrito": 7.50,
        "Bowl": 8.50,
        "Nachos": 11.00,
        "Quesadilla": 8.50,
        "Super Burrito": 8.50,
        "Super Quesadilla": 9.50,
        "Taco": 3.00,
        "Tortilla Salad": 8.00}
    total = 0
    while True:
        try:
            item = input("Input: ")
            if item.lower() in ["exit", "quit"]:
                break
            total += menu[item.title()]
            print("Total is $"+str(total))

        ##EOFError is raised when user hits Ctrl+D
        except EOFError:
            print("You triggered EOF (Ctrl+D). Exiting...")
            break

        except:
            print("Invalid inputs pls try again")

if __name__ == "__main__":
    main()