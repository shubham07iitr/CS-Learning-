# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>


bool compare(char *name, char *name2);
int main(void)
{
    char name[] = "shubham";
    char name2[] = "shubham";
    if (compare(name, name2))
    {
        printf("Strings are same");
    }
    else
    {
        printf("Strings are different");
    }




}

bool compare(char *name, char *name2)
{
    int loop_count = strlen(name);
    if (strlen(name) == strlen(name2))
    {
        for (int i = 0; i < loop_count; i++)
        {
            if (*(name+i) == *(name2+i))
            {
                
            }
            else 
            {
                return false;
            }
         
        }
        return true;
    }
    else
    {
        return false;
    }

}