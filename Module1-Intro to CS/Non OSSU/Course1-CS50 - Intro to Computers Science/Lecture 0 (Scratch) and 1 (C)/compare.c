#include <cs50.h> //this adds a new library called cs50
#include <stdio.h> // this calls library stdio

int main(void)
{
    int x = get_int("whats x?");
    int y = get_int("whats y?");

    if (x<y)
    {
        printf("X is less than Y\n");
    }
    else if (x>y)
    {
        printf("X is not less than Y\n");
    }
    else 
    {
        printf("X is equal to Y\n");
    }
}