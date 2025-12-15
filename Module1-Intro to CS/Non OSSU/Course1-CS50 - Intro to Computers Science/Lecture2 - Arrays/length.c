# include <cs50.h>
# include <stdio.h>
# include <string.h>

int main(void)

{
    string name = get_string("What's your name?");
    int n = 0;
    while (name[n] != '\0')
    {
        n++;
    }
    printf("Lenght of string through loop %i\n", n);

    int m = strlen(name);
    printf("Lenght of string through function %i\n", m);

}