# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

void reverse(char *name);

int main(void)
{
    char name[] = "shubham";
    reverse(name);
}

void reverse(char *name)
{
    int loop_count = strlen(name)/2;
    for (int i = 0; i < loop_count ; i++)
    {
        char temp = *(name+i) ;
        *(name+i) = *(name+ strlen(name) -i - 1);
        *(name+strlen(name)-i - 1) = temp;
    }
    printf("The new string is %s\n", name);

}