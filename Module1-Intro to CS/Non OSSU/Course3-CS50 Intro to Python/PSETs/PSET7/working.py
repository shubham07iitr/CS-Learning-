import re 
import sys 

def main():
    print(convert(input("Hours: ")))


def convert(text):
    hours = re.search(
        r"^([0-9]{1,2})(:[0-5][0-9])? (AM|PM) to ([0-9]{1,2})(:[0-5][0-9])? (AM|PM)$", text
    )
    if hours:
        h1, m1, p1 = int(hours.group(1)), hours.group(2) or ":00", hours.group(3)
        h2, m2, p2 = int(hours.group(4)), hours.group(5) or ":00", hours.group(6)

        def convert_hour(h, p):
            if p == "AM":
                return 0 if h == 12 else h
            else:
                return h if h == 12 else h + 12

        t1 = f"{convert_hour(h1, p1):02}{m1}"
        t2 = f"{convert_hour(h2, p2):02}{m2}"
        return f"{t1} to {t2}"
    else:
        raise ValueError
...


if __name__ == "__main__":
    main()