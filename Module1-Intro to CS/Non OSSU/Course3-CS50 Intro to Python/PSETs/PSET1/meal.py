def main():
    time = input("What time is it: ")
    convert(time)

def convert(time):
    ...
    if int(time.split(":")[0] + time.split(":")[1]) in range(700, 760) or int(time.split(":")[0] + time.split(":")[1]) == 800:
        print("Breakfast time")
    elif int(time.split(":")[0] + time.split(":")[1]) in range(1200, 1260) or int(time.split(":")[0] + time.split(":")[1]) == 1300:
        print("Lunch time")
    elif int(time.split(":")[0] + time.split(":")[1]) in range(1800, 1860) or int(time.split(":")[0] + time.split(":")[1]) == 1900:
        print("Dinner time")


if __name__ == "__main__":
    main()