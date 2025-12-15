# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <stdlib.h>

void strtoarray(char* cardno, int* card_array);
int nonmultipliednos( char* cardno, int * card_array);
int sumofdigits(int number);
int multipletwo(char* cardno, int * card_array);


int main(void)
{
    string cardno = "30202531";
    int card_array[strlen(cardno)];
    strtoarray(cardno, card_array);
    int testnonmultiple = nonmultipliednos(cardno, card_array);
    int testmultiple = multipletwo(cardno, card_array);
    int fina
    printf("Sum of non multiple digits %i\n", testnonmultiple);
    printf("Sum of multiple digits %i\n", testmultiple);



}

void strtoarray(char* cardno, int* card_array)
{
    int loop_count = strlen(cardno);
    for (int i = 0; i < loop_count; i++)
    {
        card_array[i] = cardno[i] - '0';
    }
}

int nonmultipliednos( char* cardno, int * card_array)
{
    int loop_count = strlen(cardno);
    int sum_nonmulitple = 0;
    if (loop_count%2 == 0)
    {
        
        for (int i = 1; i < loop_count; i+=2)
        {
            sum_nonmulitple += card_array[i];
        }
    }
    else
    {
        for (int i = 0; i < loop_count; i+=2)
        {
            sum_nonmulitple += card_array[i];
        }
    }
    return sum_nonmulitple;
}

int multipletwo(char* cardno, int * card_array)
{

    int loop_count = strlen(cardno);
    int len_temp_array = loop_count/2;
    int temp_array[len_temp_array] ;
    int final_sum = 0;

    if (loop_count%2 == 0)
    {
        for (int i = 0; i< loop_count -1; i+=2)
        {
            temp_array[i/2] = (cardno[i] - '0')*2;
        }
        
    }
    else
    {
        for (int i = 1; i< loop_count -1; i+=2)
        {
            temp_array[(i-1)/2] = (cardno[i] - '0')*2;

        }
        

    }
    
    for (int j = 0; j < len_temp_array;  j++)
    {
        int temp = sumofdigits(temp_array[j]);
        final_sum += temp;
        printf("current final sum is %i\n", final_sum);

    }
    return final_sum;
    



}


int sumofdigits(int number)
{
    char str[10] = {0};
    sprintf(str, "%d", number);  // Convert int to string
    int return_value = 0;
    for (int i = 0; i< strlen(str); i++)
    {
        return_value += str[i] - '0';

    }
    return return_value;
    
}