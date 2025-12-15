import cs50 

def coins_owed(refund):
    denoms = [25, 10, 5, 1]
    value_left = refund 
    coins_needed = []
    for i in denoms:
        coins_needed.append(int(value_left / i))
        value_left = value_left%i 
    return coins_needed
    
while True:
    try:
        value = float(input("Please share the value you want to exchange: "))
    except ValueError:
        print("Please enter a float only")
    else:
        if value > 0:
            total_coins_needed = sum(coins_owed(int(value*100)))
            print(f"Min count of coins needed: {total_coins_needed}")
            break
        else:
            print("Please enter a positve value only")