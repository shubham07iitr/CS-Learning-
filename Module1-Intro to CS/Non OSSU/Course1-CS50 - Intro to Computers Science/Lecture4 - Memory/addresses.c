# include <stdio.h>

int main (void)

{
    int n = 50;
    int *p = &n;
    printf("%p\n", &n); // this will give me the address of the memory location where my variable is stored at 
    printf("%p\n", p); // this will give me the address of the memory location where my variable is stored at 
    printf("%i\n", *p); // this will give me the value of integer sotred in the memory stored at p
}