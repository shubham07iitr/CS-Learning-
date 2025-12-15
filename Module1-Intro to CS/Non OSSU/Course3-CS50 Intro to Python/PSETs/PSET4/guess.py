import random

def main():
    while True:
        try:
            level = int(input("Please enter a positive integer greater than 0: "))
            if level > 0:
                break
            else:
                raise ValueError 
        except ValueError:
            print("This is an incorrect input, pls enter a positive integer only")
    guess(random.randint(1,level))

def guess(random_number):
    while True:
        try:
            guess_value = int(input("Guess: "))
            if guess_value == random_number:
                print("Just Right")
                break 
            elif guess_value > random_number:
                print("Too Large")
            elif guess_value < random_number and guess_value > 0:
                print("Too Small")
            else:
                raise ValueError
        except ValueError:
            print("Pls try again")


if __name__ == "__main__":
    main()