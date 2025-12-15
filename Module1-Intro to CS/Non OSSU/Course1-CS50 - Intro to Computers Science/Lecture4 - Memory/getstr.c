# include <stdio.h>

int main(void)

{
    char *s = NULL; //defining a new variable
    printf("s: ");
    scanf("%s", s); //here we dont need to pass &s becasue s in itself is an address
    printf("s: %s\n ",  s);
}