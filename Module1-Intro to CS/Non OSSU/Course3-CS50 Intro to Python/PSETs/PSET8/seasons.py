import sys
from datetime import datetime 
from num2words import num2words 



def main():
    try:
        date_of_birth = input("Please enter yoru DoB in YYYY-MM-DD format: ")
        if int(date_of_birth.split("-")[0]) in range(0, 2025) and int(date_of_birth.split("-")[1]) in range(1,13) and int(date_of_birth.split("-")[1]) in range(1,31):
            minutes_passed = minutes_elapsed(date_of_birth=date_of_birth)
            print(convert_date_english(time_in_minutes=minutes_passed))
        else:
            raise ValueError
    except:
        print("You entered incorrect format, pls try again with YYYY-MM_DD format, exiting now")
        sys.exit()


def minutes_elapsed(date_of_birth):
    past = datetime.strptime(date_of_birth, "%Y-%m-%d")
    today_midnight = datetime.today().replace(hour=0, minute=0, second=0, microsecond=0)
    diff = today_midnight - past
    return int(diff.total_seconds() / 60)

def convert_date_english(time_in_minutes):
    return num2words(time_in_minutes) + " " + "minutes"

if __name__ == "__main__":
    main()
