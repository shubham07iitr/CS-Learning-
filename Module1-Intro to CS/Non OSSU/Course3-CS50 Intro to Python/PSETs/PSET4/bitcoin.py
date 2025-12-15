import sys
import requests

API_KEY = "49c54815e34fca04f8f42bcbc86f28e454f7ea05c5f4e2f0bfe06f6015b28c1b"
URL = "https://rest.coincap.io/v3/assets/bitcoin?apiKey="+API_KEY 

def main():
    
    if len(sys.argv) != 2:
        print("Usage: filename Bitcoins")
    else:
        try:
            number = float(sys.argv[1])
            bitcoin_price(number)
        except:
            print("Sorry you need to input a number")
            sys.exit()

def bitcoin_price(number):
    try:
        response = requests.get(URL)
        print(round(float(response.json()["data"]["priceUsd"])*number, 4))
    except requests.RaiseException:
        print("Could not make the API request, pls try again")

if __name__ == "__main__":
    main()

