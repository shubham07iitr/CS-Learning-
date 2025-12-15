import emoji

def main():
    text = input("Input a text you want to be converted: ")
    print(convert_emoji(text))

def convert_emoji(text):
    return emoji.emojize(text, language= "alias")

if __name__ == "__main__":
    main()


