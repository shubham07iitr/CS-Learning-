# include <stdio.h>

int main(void)

{
    int x; //defining a new variable
    printf("x: ");
    scanf("%i", &x); //here we pass in &x because we want the value to be stored in the address of x 
    printf("x: %i\n ", x);
}