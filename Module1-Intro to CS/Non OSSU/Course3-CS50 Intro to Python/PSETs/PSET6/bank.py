def main():
    greeting_var = input("Greeting: ")
    print("$" + str(greeting(greeting_var)))


def greeting(greeting_var):
    if greeting_var.strip().split()[0].lower() == "hello":
        return 0
    elif greeting_var.strip().split()[0][0].lower() == "h":
        return 20
    else:
        return 100

if __name__ == "__main__":
    main()