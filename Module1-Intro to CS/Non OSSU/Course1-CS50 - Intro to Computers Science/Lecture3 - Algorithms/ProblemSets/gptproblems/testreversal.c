# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

int main(void)
{
    string name = get_string("pass the string you want reversed: ");
    int loop_count = strlen(name)/2;
    for (int i = 0; i < loop_count ; i++)
    {
        char temp = name[i];
        name[i] = name[strlen(name) - i-1];
        name[strlen(name)-1-i] = temp;
    }
    printf("The new string is %s\n", name);



}