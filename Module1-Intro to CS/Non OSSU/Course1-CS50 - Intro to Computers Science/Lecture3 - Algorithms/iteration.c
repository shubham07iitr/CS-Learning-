# include <cs50.h>
# include <stdio.h>
# include <string.h>


void drawrecursion(int n);
void drawloop(int n);
int main(void)
{
    int height = get_int("Height: ");
    drawrecursion(height);
    drawloop(height);
}


void drawrecursion(int n)
{   

    //defining the base case
    if (n <=0)
    {
        return;
    }

    //calling the function recursively
    drawrecursion(n-1);
    for (int i = 0; i < n ; i++)
    {
        printf("#");
    }
    printf("\n");

}


void drawloop(int n)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < i+1; j++)
        {
            printf("#");

        }
        printf("\n");
    }
}