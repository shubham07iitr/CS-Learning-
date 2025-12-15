# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>

int main(void)
{
    int binary[] = {1,2,4,8,16,32,64,128};
    int temp_array[8] = {0};
    int counter = 0;
    int remaining = 18;
    
    do 
    {
        int i = 0;
        while (remaining >= binary[i])
        {
            i ++;
        }
        printf("value of i is %i\n", i);
        temp_array[8-i] = 1;
        remaining = remaining - binary[i-1];
        printf("remaining value %i\n", remaining);
    }
    while (remaining > 0);

    for (int i = 0; i <8 ; i++)
    {
        printf("%i", temp_array[i]);
    }

}