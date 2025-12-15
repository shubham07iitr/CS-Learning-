def main():
    input_text = input("Input: ")
    output(input_text)

def output(input_text):
    return_list = []
    for i in input_text:
        if i.lower() not in ["a", "e", "i", "o","u"]:
            return_list.append(i)
    print(''.join(return_list))

if __name__ == "__main__":
    main()