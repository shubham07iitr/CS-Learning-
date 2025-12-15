def main():
    adieu()
    

def adieu():
    name_list = []
    while True:
        
        try: 
            name = input("Name: ")
            name_list.append(name)
            if name.lower() in ["exit", "quit"]:
                break
        except EOFError:
            return_str = ""
            for i in name_list:
                return_str += "," + " " + i 
            return_list = return_str.split()
            for i in range(len(return_list)):
                if i == 0:
                    return_list[i] = "to "
                elif i ==len(return_list) - 1:
                    return_list[-1] = "and " + return_list[-1]
                else:
                    return_list[i] = return_list[i] + " "
            print(f"Adieu, adieu, " + ''.join(return_list))
            break



if __name__ == "__main__":
    main()