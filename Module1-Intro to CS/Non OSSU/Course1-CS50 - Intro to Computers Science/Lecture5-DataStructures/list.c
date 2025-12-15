# include <stdio.h>
# include  <stdlib.h>

int main(void)
{
    
    // Defining memory through array which is less dynamic
    // int list[3];
    // list[0] = 1;
    // list[1] = 2;
    // list[2] = 3;
    // for (int i = 0; i < 3; i++)
    // {
    //     printf("%i\n", list[i]);
    // }

    // Defining memory through pointers which is more dynamic
    int *listp = malloc(3* sizeof(int));
    if (listp == NULL) // always to check in case of memory allocation failure 
    {
        return 1;
    }
    listp[0] = 1;
    listp[1] = 2;
    listp[2] = 3;
    for (int i = 0; i < 3; i++)
    {
        printf("%i\n", listp[i]);
    }
    //lets say we have to increase the memory now of the original pointer
    //we define another temp pointer and make the original list pointer point to the new list location 
    int *temp = malloc(4*sizeof(int));
    if (listp == NULL)
    {
        free(listp);
        return 1;
    }
    for (int i =0; i <3; i ++)
    {
        temp[i] = listp[i];
    }
    temp[3] = 4;
    free(listp); // freeing up original list and then storing the address of temp into the original list 
    listp =  temp;
    for (int i = 0; i < 4; i++)
    {
        printf("%i\n", *(listp+i));
    }
    free(listp); // frees up the new memory of 4 bytes for 1,2,3,4

}