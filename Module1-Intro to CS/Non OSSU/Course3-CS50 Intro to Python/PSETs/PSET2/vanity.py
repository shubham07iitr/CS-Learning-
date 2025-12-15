import string
def main():
    plate = input("Plate: ")
    if is_valid(plate):
        print("Valid")
    else:
        print("Invalid")


def is_valid(s):
    letters =  list(string.ascii_uppercase)
    numbers = [str(i) for i in range(10)]
    chars = ['.', ' '] + list(string.punctuation)
    if len(s) not in range(2, 8):
        return False
    if s[0] in letters and s[1] not in letters:
        return False 
    for i in s:
        if i in chars:
            return False 
    for i in range(len(s)):
        if s[i] in numbers and i not in [len(s) -1, len(s) - 2]:
            return False 
    return True

if __name__ == "__main__":
    main()