def main():
    text = input("Please enter some text with smiley face: ")
    print(convert(text))


def convert(text):
    text1 =  text.replace(":)", "🙂")
    text2 = text1.replace(":(" , "🙁")
    return text2 

main()