import re 
import sys 

def main():
    print(count(input("Text: ")))


def count(s):
    count = 0
    not_acceptable_values = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

    if s.lower() == "um":
        return 1
    for index, char in enumerate(s):
        if index == 0 and s[index:index+2].lower() == "um" and s[index+2] not in not_acceptable_values:
            count += 1 
        elif s[index:index+2].lower() == "um" and s[index+2] not in not_acceptable_values and s[index-1] == " ":
            count += 1
    return count 
        

if __name__ == "__main__":
    main()