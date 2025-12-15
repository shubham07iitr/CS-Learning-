from sys import argv
import csv 

# with open("./dna/sequences/1.txt") as file:
#     content = file.read()
#     print(content)

# with open("./dna/databases/small.csv") as file:
#     content = csv.reader(file)
#     for row in content:
#         print(row)



##Checking no. of times a particular sequence occurs in a DNA
def longest_match(repeat, file_path):
    with open(file_path) as file:
        content = file.read()
        print(content)
        count = 0
        for i in range(len(content)):
            total_matches = 0
            for j in range(len(repeat)):
                ## checking if each element of repeat is occuring in the same sequence in the bigger string
                if i + j >= len(content):
                    break
                if content[i+j] == repeat[j]:
                    ##this is to ensure we only count when full match is found
                    total_matches += 1
                    pass
                else:
                    break 
                ##this is to ensure we only count when full match is found
                if total_matches == len(repeat):
                    count += 1
                else:
                    pass

        return(count)
    

## Gives back the list of STR sequences hich we hav to check in the sequnce
def list_repeats(file_data):
    with open(file_data) as file:
        table = csv.reader(file)
        list_repeats = []
        for row in table:
            list_repeats = row 
            list_repeats = list_repeats[1:]
            break
        return list_repeats
            


## Gives back the list of counts per STR in the sequence
def list_repeat_count(list_repeats, file_path):
    list_repeat_count = []
    for i in list_repeats:
        list_repeat_count.append(longest_match(repeat = i, file_path = file_path))
    return list_repeat_count



## Checks which name's STR matches with the one we found through list_repeat_count
def final_name_check(list_repeat_count, file_data):
    with open(file_data) as file:
        table = csv.reader(file)
        for row in table:
            new_row = row[1:]
            int_row = []
            for i in new_row:
                try:
                    int_row.append(int(i))
                except ValueError:
                    print("Can't convert to int, moving on")
            print(int_row)
            if int_row == list_repeat_count:
                print(f"Its {row[0]}'s DNA")
                return
        print("No match")


if len(argv) == 3:
    list_repeats= list_repeats(argv[1])
    list_repeat_count = list_repeat_count(list_repeats= list_repeats, file_path=argv[2])
    print(list_repeat_count)
    final_name_check(list_repeat_count= list_repeat_count, file_data= argv[1])
else:
    print("You have shared inadequate CL Arguments")

# a = longest_match(repeat= "AGATC", file_path="./dna/sequences/1.txt")
# print(a)

##database path = "./dna/databases/small.csv"
## sequence file path = "./dna/sequences/1.txt"
