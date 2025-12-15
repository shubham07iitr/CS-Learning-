def main():
    inputs = input("Please enter the expression: ")
    print(interpreter(inputs))

def interpreter(inputs):
    if inputs.split()[1] == "+":
        return float(inputs.split()[0]) + float(inputs.split()[-1])
    elif inputs.split()[1] == "-":
        return float(inputs.split()[0]) - float(inputs.split()[-1])
    elif inputs.split()[1] == "*":
        return float(inputs.split()[0]) * float(inputs.split()[-1])
    elif inputs.split()[1] == "/":
        return float(inputs.split()[0]) / float(inputs.split()[-1])
    else:
        print("Please enter a relevant math operator")

main()