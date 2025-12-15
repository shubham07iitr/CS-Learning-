def main():
    mass = int(input("Please enter the mass you want converted to energy: "))
    print(convert(mass))

def convert(mass):
    light_speed = 300000
    return mass*(light_speed**2)

main()