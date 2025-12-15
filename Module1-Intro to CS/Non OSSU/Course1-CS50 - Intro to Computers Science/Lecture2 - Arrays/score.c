# include <stdio.h>
# include <cs50.h>

//defining a new funciton which will return a float value and takes an integer array as a parameter
float average(int array[]);



const int N = 3;
int main(void)
{
    int scores[N];
    for (int i = 0; i < N; i++)

    {
        scores[i] = get_int("Score: ");
     
    }
    
    printf("Average: %f\n", (average(scores)));
}

float average(int array[])
{
    int sum = 0;
    for (int  i = 0; i < N; i++)
    {
        sum = sum + array[i];
    }
    return sum/(float) N;

}