def main():
    main_question = input("What is the Answer to the Great Question of Life, the Universe, and Everything?: ")
    deep(main_question)

def deep(text):
    if text == str(42) or text.lower() in ["forty-two" , "forty two"]:
        print("Yes")
    else:
        print("No")


main()