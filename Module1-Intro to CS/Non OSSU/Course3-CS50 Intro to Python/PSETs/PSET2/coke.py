def main():
    coke()

def coke():
    amount_due = 5025
    while amount_due > 0:
        print(f"Amount due: {amount_due}")
        user_input = int(input("Insert coin"))
        if user_input in [5,25,10]:
            amount_due = amount_due - user_input
    print(f"Change owed:  {-1*amount_due}")

if __name__ == "__main__":
    main()

