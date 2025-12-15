#include <stdio.h>

int main(void)

{   const int n =3 ; //const keyword is used to define a constant so that value cant be changed
    for (int i = 0; i < n; i++)
    {
        for (int j = 0 ; j < n ; j++)
        {
            printf("#");
        }
        printf("\n");
    }   
}