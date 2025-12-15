# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>


int sum(int test[] , int arraysize);
int main(void)
{
    int sumtest[] = {1,2,3,4,5,6};
    int sumarray = sum(sumtest, 6);
    printf("The sum is is %i,\n", sumarray);
}

int sum(int test[] , int arraysize)
{
    int sum =0;
    for (int i = 0; i < arraysize; i++)
    {
        sum += *(test+i);
    }
    return sum;

}