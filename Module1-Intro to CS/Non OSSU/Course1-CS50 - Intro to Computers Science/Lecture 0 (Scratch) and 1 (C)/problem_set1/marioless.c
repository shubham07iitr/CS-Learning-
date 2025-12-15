# include <stdio.h>
# include <cs50.h>

void pyramid(int height);

int main (void)
{
    int height = get_int("What is the height of pyramid you want: ");
    pyramid(height);
}

void pyramid(int height)
{
    for (int i = 0; i < height; i++) //this loop will just go to next lines
    {
        for (int j = height - i; j > 1; j--) //this loop will dynamically print empty spaces and reduce step by step for each line
        {
            
            printf(" ");
            
        }
        for (int k = 0; k < i+ 1; k++) //this lopp will dynamically print hashes and increase step by step for each line
        {
            printf("#");
        }
        printf("\n");
    }
}