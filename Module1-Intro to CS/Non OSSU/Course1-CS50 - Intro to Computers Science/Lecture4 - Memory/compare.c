# include <cs50.h>
# include <stdio.h>

int main(void)
{
    string s = get_string("s: ");
    string t = get_string("t: ");

    if (*s == *t && *(s+1) == *(t+1) && *(s+2) == *(t+2)) //this will compare each of the value for different address
    {
        printf("Same\n");

    }
    else
    {
        printf("Different\n");
    }

    printf("%p\n", s);
    printf("%p\n", t);
}