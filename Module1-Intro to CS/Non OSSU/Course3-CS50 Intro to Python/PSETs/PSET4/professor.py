import random


def main():
    level = get_level()
    generate_integer(level)


def get_level():
    while True:
        try:
            level = int(input("Please enter a level b/w 1,2,3: "))
            if level in [1,2,3]:
                return level 
            else:
                raise ValueError 
        except:
            print("Invalid input, pls inpt only 1,2, or 3: ")

            

def generate_integer(level):
    score = 0
    for i in range(10):
        num1 = ''.join([str(random.randint(0,9)) for _ in range(level)])
        num2 = ''.join([str(random.randint(0,9)) for _ in range(level)])
        counter = 0
        while counter < 3:
            try:
                user_output = int(input(f"{num1} + {num2}: "))
                if user_output == int(num1) + int(num2):
                    score += 1
                    break 
                else:
                    raise ValueError
            except ValueError:
                print("EEE, pls try again")
                counter += 1
    print(f"You got {score} correct out of 10 questions")


if __name__ == "__main__":
    main()