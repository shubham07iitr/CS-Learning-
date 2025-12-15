# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>
# include <stdlib.h>


int main(void)

{
    int *x;
    int *y;
    
    x = malloc(sizeof(int));
    *x = 42;
    *y = 13;
    
    printf("%p\n", x);
    printf("%i\n", *x);
    printf("%p\n", y);
    printf("%i\n", *y);

    char *s = "HI";

    
    printf("%c\n", *s);
    printf("%s\n", s);

}