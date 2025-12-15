height = int(input("Pls specify the height: "))

for i in range(0, height):
    print(" "*(height-i-1) + "#"*(i+1))