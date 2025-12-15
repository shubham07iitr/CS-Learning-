import sys 

def main():
    if len(sys.argv) != 2:
        print("You did not give a filename to read or too many arguments, exiting now")
        sys.exit()
    elif sys.argv[1][-3:] != ".py":
        print("Please give a python file to read")
        sys.exit()
    else:
        try:
            print(f"Count of lines is {count_lines(sys.argv[1])}")
        except FileNotFoundError:
            print("Your file does not exist")
            sys.exit()

def count_lines(filename):
    count = 0
    with open(filename) as file:
        line_list = file.readlines()
        for line in line_list:
            if line == '\n':
                pass
            elif line.strip()[0] == "#" :
                pass 
            else:
                count = count + 1 
            
    return count 

if __name__ == "__main__":
    main()


    
    
