card_number = input("Please enter the card_no you want to check: ")

def get_sum_others(string):
    list_temp = []
    if len(string)%2 == 0:
        for i in range(1, len(string), 2):
            list_temp.append(int(string[i]))
    else:
        for i in range(0, len(string), 2):
            list_temp.append(int(string[i]))
    print(list_temp)
    return list_temp



def get_sum_two(string):
    list_temp = []
    if len(string)%2 == 0:
        for i in range(0, len(string) -1, 2): ## for case of even no. of elements in the number we would start from 1st element
            list_temp.append(string[i]) ## Creating a list which will later be operated on
    else:
        for i in range(1, len(string) -1, 2):
            list_temp.append(string[i])
    
    ## converting list element into integer and multiplying by 2
    for i in range(0, len(list_temp)):
        list_temp[i] = int(list_temp[i])*2
        
    
    ##checking if a number is > 10 for which we will have a separate mechanism
    counter = 0 ##this is to move the index for listtemp
    for i in list_temp:     
        if i/10 >= 1:
            temp_num = 0
            i = str(i)
            for j in i:
                temp_num += int(j)
            
            list_temp[counter] = temp_num ## Adding sum of digits for those cases where we have 2 digit nos
            
            counter+=1 
        else:
            counter += 1
    print(list_temp)
    return list_temp
            

def card_check(list_two, list_other):
    print(sum(list_two) + sum(list_other))
    if (sum(list_two) + sum(list_other))%10 == 0:
        print("Valid Card")
    else:
        print("INVALID Card No")



list_two = get_sum_two(card_number)
list_other = get_sum_others(card_number)
card_check(list_two = list_two, list_other=list_other)

