# include <stdio.h>
# include <cs50.h>

void cash(int value);

int main(void)
{
    int get_value;
    do 
    {
        get_value = get_int("Change owed: ");
    }
    while (get_value <0) ;
    cash(get_value);
}

void cash(int value)
{
    int denoms[] = {25, 10, 5, 1};
    int change_needed = 0;
    int remaining = value;
    for (int i = 0; i <4; i++)
        if (remaining >= denoms[i])
        {
            change_needed += remaining/denoms[i] ;
            int coins_needed = remaining/denoms[i] ;
            remaining = remaining - coins_needed*denoms[i] ;
            printf("Change till now %i\n", change_needed);
            printf("Remaining till now %i\n", remaining);
        }
        else
        {
            printf("Checking the next denomination\n");
        }

    printf("The total change needed is %i\n", change_needed);
}
