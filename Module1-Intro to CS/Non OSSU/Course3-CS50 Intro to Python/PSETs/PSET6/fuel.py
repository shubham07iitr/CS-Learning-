def main():
    fraction = input("Fraction: ")
    percent = convert(fraction)
    print(gauge(percent))


def convert(fraction):
    numbers = [str(i) for i in range(10)]    

    if len(fraction) == 3 and fraction[0] in numbers and fraction[2] in numbers and fraction[1] == "/" and fraction[2] != "0" and int(fraction[0]) <= int(fraction[2]):
        return int((int(fraction[0])/int(fraction[2]))*100)
    elif int(fraction[2]) == 0:
        raise ZeroDivisionError
    else:
        raise ValueError
  
    
def gauge(percent):
    if percent >= 99:
        return "F"
    elif percent <= 1:
        return "E"
    else:
        return str(percent)+"%"

if __name__ == "__main__":
    main()



