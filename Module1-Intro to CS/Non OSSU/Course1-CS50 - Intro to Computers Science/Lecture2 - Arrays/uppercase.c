#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

int main(void)
{
    string s = get_string("Before: ");
    printf("After lower level conversion: ");
    for (int i = 0, n = strlen(s); i < n; i++)
    {
        if (s[i] >= 'a' && s[i] < 'z') // here we are checking if the letter is lowercase
        {
            printf("%c", s[i] - 32); // what we are doing is ASCII for every upper vs lower letter is 32 less , so upper of A is 65 and lower of a is 97
        }
        else
        {
            printf("%c", s[i]);
        }
    }
    printf("\n");
    printf("After abstraction: ");
    for (int i = 0, n = strlen(s); i < n; i++)
    {
        printf("%c", toupper(s[i]));

    }
    printf("\n");

}