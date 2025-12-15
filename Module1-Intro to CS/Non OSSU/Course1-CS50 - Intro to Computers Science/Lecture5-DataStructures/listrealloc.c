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
    //lets say we have to increase the memory now of the original pointer
    //we define another temp pointer and make the original list pointer point to the new list location 
    int *temp = realloc(listp, 4*sizeof(int));
    if (listp == NULL)
    {
        free(listp);
        return 1;
    }
    //printf("Checking what happens after realloc to listp memory %i\n", listp[1]);
    listp =  temp;
    listp[3] = 4;
    free(listp); // this is not needed in malloc because it automatically frees up the space from the previous listp while copying the data
    
    for (int i = 0; i < 4; i++)
    {
        printf("%i\n", *(listp+i));
    }
    free(listp); // frees up the new memory of 4 bytes for 1,2,3,4

}