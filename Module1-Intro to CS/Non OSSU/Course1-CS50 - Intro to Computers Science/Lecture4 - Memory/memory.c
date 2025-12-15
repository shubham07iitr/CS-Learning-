# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>
# include <stdlib.h>

int main(void)
{
    // asking for 12 bytes of memory as each int takes up 4 bytes
    int *x = malloc(3 * sizeof(int)) ;
    if (x = NULL)
    {
        return 1;
    }
    x[1] = 73;
    x[2] = 72;
    x[3] = 33; //bug here as I am allocating memory for space which is not there
    free(x)
    return 0;
}