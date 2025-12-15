# include <stdio.h>
# include <cs50.h>
# include <string.h>
#include <ctype.h>

void binary(int decimal);
int main(void)


{
    
    string word = get_string("Enter the string you want converted:");
    int loop_count = strlen(word);
    for (int i = 0; i  < loop_count; i++)
    {
        int temp_decimal = word[i];
        binary(temp_decimal);
    }
}


void binary(int decimal)
{
    int binary[] = {1,2,4,8,16,32,64,128};
    int temp_array[8] = {0};
    int counter = 0;
    int remaining = decimal;
    
    do 
    {
        int i = 0;
        while (remaining >= binary[i])
        {
            i ++;
        }
        //printf("value of i is %i\n", i);
        temp_array[8-i] = 1;
        remaining = remaining - binary[i-1];
        //printf("remaining value %i\n", remaining);
    }
    while (remaining > 0);

    for (int i = 0; i <8 ; i++)
    {
        printf("%i", temp_array[i]);
    }
    printf("\n");

}