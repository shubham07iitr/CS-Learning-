# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>
# include <stdlib.h>

int main(void)
{
    char *s = get_string("s: ");

    if (s==NULL) //basically means we typed way too much and get_string fucntion returned NULL value signifying overflow
    {
        return 1;
    }

    char *t = malloc(strlen(s) + 1); //this will give me free space in memory equal to length of string + 1 for null
    char *u = malloc(strlen(s) + 1);

    for (int i = 0, n = strlen(s) + 1; i < n; i++)
    {
        t[i] = s[i];
    }

    if (strlen(t) > 0)
    {
        t[0] = toupper(t[0]);
    }
    
    //or we can use the function strcpy
    strcpy(u,s);
    if (strlen(u) > 0)
    {
        u[0] = toupper(u[0]);
    }



    printf("s: %s\n", s);
    printf("t: %s\n", t);
    printf("u: %s\n", u);

    free(t); //this will free up the memory at t
    printf("t: %s\n", t);

}