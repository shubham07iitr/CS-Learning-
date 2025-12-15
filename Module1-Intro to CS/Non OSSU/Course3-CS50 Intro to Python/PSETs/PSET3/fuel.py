def main():
    fuel()

def fuel():
    numbers = [str(i) for i in range(10)]
    while True:
        try:
            fraction = input("Fraction: ")
            if len(fraction) == 3 and fraction[0] in numbers and fraction[2] in numbers and fraction[1] == "/" and fraction[2] != "0" and int(fraction[0]) <= int(fraction[2]):
                break
            if fraction.lower() in ["exit", "quit"]:
                break
        except:
            print("Invalid input, pls try again")
    percent = int((int(fraction[0])/int(fraction[2]))*100)
    if percent >= 99:
        print("F")
    elif percent <= 1:
        print("E")
    else:
        print(str(percent)+"%")

if __name__ == "__main__":
    main()



