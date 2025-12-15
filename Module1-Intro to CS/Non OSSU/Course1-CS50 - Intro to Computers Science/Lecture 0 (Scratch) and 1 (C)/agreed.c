#include <cs50.h>
#include <stdio.h>

int main(void)

{
    char c = get_char("DO you agree\n");
    if (c == 'y' || c == 'Y') // when ysing char , we use single quotes and when using strings we use double quotes // also || used for or condition
    {
        printf("Agreed.\n");
    }
    else if (c == 'n' || c == 'N')
    {
        printf("Not agreed.\n");
    }
}