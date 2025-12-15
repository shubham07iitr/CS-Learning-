import sys 
import csv
from tabulate import tabulate

def main():
    if len(sys.argv) != 2:
        print("You entered too few or too many arguments, pls input only one argument for file name, exiting now")
        sys.exit()
    elif sys.argv[1][-4:] != ".csv":
        print("You did not give a csv file to format, exiting now")
        sys.exit()
    else:
        try:
            convert_csv(sys.argv[1])
        except FileNotFoundError:
            print("File does not exist, exiting now, pls try again")
            sys.exit()

def convert_csv(filename):
    with open(filename) as file:
        ##this reader = csv.reader(file) will give a list of lists, where the first element will be the header, and remaining list will be the rows in csv
        reader = csv.reader(file)
        print(tabulate(reader, headers = "firstrow", tablefmt = "grid"))
        

if __name__ == "__main__":
    main()

