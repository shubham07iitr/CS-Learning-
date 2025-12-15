#include <stdio.h>
#include <cs50.h>


//we have to name the functions before using it 
int get_size(void);
void print_grid(int size);



int main(void)

{   //get size of grid
    int n = get_size();
    //print grid of bricks
    print_grid(n);
}

//function get_size
//here int means function will return an integer (void means it is taking no param)
int get_size(void)
{
    int n;
    do
    {
        n = get_int("Size: ");
    } while (n < 1);
    return n ;

}

//here void means funciton is not returning anything just printing something , and takes size as param
void print_grid(int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0 ; j < size ; j++)
        {
            printf("#");
        }
        printf("\n");
    }   

}
    
    