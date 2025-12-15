from validator_collection import checkers
import sys 

def main():
    print(is_email(input("Email Address: ")))

def is_email(email_address):
    return checkers.is_email(email_address)

if __name__ == "__main__":
    main()
          
        
