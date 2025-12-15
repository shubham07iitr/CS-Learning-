#include <cs50.h>
#include <stdio.h>

int main() {
    string first = get_string("What is your first name "); // get_string is being used from cs50 library
    string second = get_string("What is your last name "); // get_string is being used from cs50 library
    printf("Hello %%, %s %s\n!", first , second);
    return 0;   
}
