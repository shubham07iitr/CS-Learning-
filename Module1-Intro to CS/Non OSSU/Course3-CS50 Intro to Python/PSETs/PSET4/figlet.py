import random
import sys 
from pyfiglet import Figlet

def main():
    figlet = Figlet()
    fonts_list = figlet.getFonts()
    if len(sys.argv) == 1:
        text = input("Input: ")
        figlet_func(figlet, random.choice(fonts_list), text)
    elif len(sys.argv) == 3 and sys.argv[1] in ["-f" , "--font"] and sys.argv[2] in fonts_list:
        text = input("Input: ")
        figlet_func(figlet, sys.argv[2], text)
    else:
        print("Invalid usage")
        sys.exit()




def figlet_func(figlet, font, text):
    figlet.setFont(font=font)
    print("Ouput: " + figlet.renderText(text))

    

if __name__ == "__main__":
    main()