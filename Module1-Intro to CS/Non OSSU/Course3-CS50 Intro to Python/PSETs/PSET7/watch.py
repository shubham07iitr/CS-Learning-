import re 
import sys 

def main():
    print(parse(input("HTML: ")))

def parse(URL):
    matches = re.search('"https?://(www\.)?youtube\.com/embed/(\w+)".*', URL )
    if matches:
        return ("https://youtu.be/"+matches.group(2))
    else:
        return None

if __name__ == "__main__":
    main()

    