def main():
    case = input("camelCase: ")
    print("snake_case: " + convert(case))

def convert(case):
    return_list = []
    for i in case:
        if i.islower():
            return_list.append(i)
        else:
            return_list.append("_"+i.upper())

    return ''.join(return_list)

if __name__ == "__main__":
    main()
    