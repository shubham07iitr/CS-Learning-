# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>
# include <stdlib.h>


int main(void)
{
    int scores[1024]; // defining an array of 1024 size 
    for (int i = 0; i < 10; i++) // here we have not defined the values of the array but we will get back garbage values
    {
        printf("%i\n", scores[i]);
    }
}