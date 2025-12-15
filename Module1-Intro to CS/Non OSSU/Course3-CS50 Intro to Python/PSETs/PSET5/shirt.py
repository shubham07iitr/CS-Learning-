
from PIL import Image 
from PIL import ImageOps


import sys 

def main():
    ...
    if len(sys.argv) != 3:
        print("You entered too few or too many arguments, pls input only one argument for file name, exiting now")
        sys.exit()
    elif sys.argv[1][-4:] and sys.argv[2][-4:] not in [".png", ".jpg"]:
        print("You have not entered images as arguments")
        sys.exit()
    elif sys.argv[1][-4:] != sys.argv[2][-4:]:
        print("Input and output files format do not match, exiting now")
        sys.exit()
    else:
        try:
            image_convert(sys.argv[1], sys.argv[2])
        except FileNotFoundError:
            print("Image does not exist, exiting now, pls try again")

def image_convert(image1, image2):
    image_main = Image.open(image1)
    image_shirt = Image.open("shirt.png")
    image_main = ImageOps.fit(image = image_main, size = image_shirt.size)
    image_main.paste(image_shirt, mask=image_shirt)
    image_main.show()
    image_main.save(image2)

if __name__ == "__main__":
    main()