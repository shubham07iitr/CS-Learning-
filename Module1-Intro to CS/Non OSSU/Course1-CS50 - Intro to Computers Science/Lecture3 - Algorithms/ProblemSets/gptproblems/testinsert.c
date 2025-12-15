# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

void insert(char *name, int position, char character);
int main()
{
    char name[] = "shubham";
    insert(name, 3, 'x');
    printf("The new value of name after function call is  %s\n", name);
    
}

void insert(char *name, int position, char character)
{
    char *new_name = malloc(strlen(name)+2);
    for (int i = 0; i < position; i++)
    {
        *(new_name+i) = *(name+i);
    }
    *(new_name+position) = character;
    for (int i = position; i < strlen(name)+1; i++)
    {
        *(new_name+i+1) = *(name+i);
    }
    printf("New name is %s\n", new_name);
    name = new_name;
    printf("Old name is %s\n", name);

    
}


int main(void)
{
    int x = 1;
    int y = 2;
 
 
    printf("x is %i, y is %i before swap\n", x, y);
    swap(&x, &y);
    printf("x is %i, y is %i after swap\n", x, y);
}

void swap(int *a, int *b)


{
   int temp = a;
   a = b;
   b = temp;
   print
}
