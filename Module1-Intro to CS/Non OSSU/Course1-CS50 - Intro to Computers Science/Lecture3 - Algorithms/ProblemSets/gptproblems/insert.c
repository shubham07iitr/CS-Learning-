# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

char repeat(char *name);
int main(void)
{
    char name[] = "shuuubham";
    char repeat_char = repeat(name);
    if (repeat_char != '\0')
    {
        printf("The repeat character is %c\n", repeat_char);
    }
    else 
    {
        printf("There is no repeat character\n");
    }

}

char repeat(char *name)
{
    char repeat = *name;
    int loop_count = strlen(name);
    for (int i = 1; i < loop_count; i++)
    {
        if (*(name+i) == repeat)
        {
            return repeat;
        }
        else
        {
            repeat = *(name+i);
        }
    }
    return '\0';

}