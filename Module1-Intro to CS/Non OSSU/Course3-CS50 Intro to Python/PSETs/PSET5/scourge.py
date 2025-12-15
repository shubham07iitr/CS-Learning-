import sys
import csv 

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
    temp_list = []
    with open(filename) as file:
        reader = csv.reader(file)
        for i in reader:
            temp_list.append(i)
                
    ##carrying out cleaning up of data here            
    final_list = []
    for i in temp_list[1:]:
        temp2_list = []
        temp2_list.append(i[0].split(",")[0].strip())
        temp2_list.append(i[0].split(",")[1].strip())
        temp2_list.append(i[1].strip())
        final_list.append(temp2_list)

    ##opening a new file and writing last name, first name and house in that order 
    with open("after.csv", "a") as file:
        writer = csv.writer(file)
        writer.writerow(["Last Name", "First Name", "House"]) 
        for i in final_list:
            writer.writerow(i) 
            
    return 

if __name__ == "__main__":
    main()
