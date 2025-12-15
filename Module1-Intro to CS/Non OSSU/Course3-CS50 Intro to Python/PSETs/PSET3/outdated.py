def main():
    dated()

def dated():
    months = {'january': 1,'february': 2, 'march': 3, 'april': 4, 'may': 5, 'june': 6, 'july': 7,  'august': 8, 'september': 9, 'october': 10, 'november': 11,'december': 12}

    while True:
        try:
            user_date = input("Please enter a valid date in month-day-year order format: ")                
            temp_list = user_date.split()

            ##conditions for 1st format mm/dd/yyyy
            if user_date.lower() in ["exit", "quit"]:
                break
            elif int(user_date[-4:]) in range(1, 2026) and user_date[-5] == user_date [-8] == "/" and int(user_date[-7:-5]) in range(1, 32) and int(user_date[-10:-8]) in range(1, 13):
                print("You tried numeric inputs")
                formatted_date = user_date[-4:]+"-"+user_date[-10:-8]+"-"+user_date[-7:-5]
                print(formatted_date)
                break 

            ##conditions for 2nd format Month dd, YYYY    
            elif temp_list[0].lower() in months and int(temp_list[-1]) in range(1, 2025) and int(temp_list[1][:-1]) in range(1,32):
                print("You tried non numeric inputs")
                formatted_date = temp_list[-1]+"-"+str(months[temp_list[0].lower()])+"-"+temp_list[1][:-1]
                print(formatted_date)
                break
            raise ValueError
        except: 
            print("Invalid inputs, pls try again")

if __name__ == "__main__":
    main()