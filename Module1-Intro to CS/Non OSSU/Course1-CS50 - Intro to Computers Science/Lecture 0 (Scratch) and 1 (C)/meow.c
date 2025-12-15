#include <stdio.h>

int main(void) 
{

int counter = 3;
while (counter > 0)
{
    printf("meow\n");
    counter = counter - 1;
}

for (int i = 0; i < 3; i++)
{
    printf("meow\n");
}
}