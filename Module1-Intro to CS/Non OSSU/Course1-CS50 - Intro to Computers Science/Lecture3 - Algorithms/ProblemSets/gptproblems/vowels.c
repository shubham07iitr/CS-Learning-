# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>


int vowel(char *name);
int main(void)
{
    char name[] = "shubhamaau";
    int vowel_count = vowel(name);
    printf("The vowel count is %i\n", vowel_count);



}

int vowel(char *name)
{
    int loop_count = strlen(name);
    int count_vowels = 0;
    for (int i = 0; i < loop_count; i++)
    {
        if (strchr("aeiouAEIOU", *(name+i)))
        {
            count_vowels++;
        }
    }
    return count_vowels;
}