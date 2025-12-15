# include <stdio.h>
# include <stdlib.h>


typedef struct node
{
    int number;
    struct node *next; // here the second property of the node is a pointer which points to another node
}
node;

int main(int argc , char* argv[]) //so tht we can define calues of LL directly through CL A
{
    //Now let's create the LL
    //First we define the head which will point to the first element 
    node *list = NULL; //*list is basically the head
    for (int i= 1; i < argc; i++)
    {
        int number = atoi(argv[i]);
        node *n = malloc(sizeof(node));
        if (n==NULL)
        {
            return 1;
        }
        n->number = number;
        n->next = NULL;
        n->next = list; //pre pending the item to the list
        list = n;
    }
    //printing the values of the LL
    node *ptr = list;
    while(ptr != NULL)
    {
        printf("%i\n", ptr->number);
        ptr = ptr->next; //pointer now points to the pointer of the current node 
    }
    //Freeing up the memory after using the pointer
    while (ptr != NULL)
    {
        node * next = ptr->next; // just an additional temp variable because if we dont use it then we will loist the connectio after using the 2nd line
        free(ptr);
        ptr = next;

    }
}