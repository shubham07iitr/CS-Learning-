def main():
    greeting_var = input("Greeting: ")
    greeting(greeting_var)  


def greeting(greeting_var):
    if greeting_var.strip().split()[0].lower() == "hello":
        print("$0")
    elif greeting_var.strip().split()[0][0].lower() == "h":
        print("$20")
    else:
        print("$100")

main()