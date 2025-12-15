# include <cs50.h>
# include <stdio.h>
# include <string.h>
# include <ctype.h>
#include <stdbool.h>



typedef struct
{
    string name;
    int votes;
} candidate;
candidate candidates[3];

bool vote(string name , candidate candidates[], int len_array);
void print_winner(candidate candidates[], int len_array);
int main()
{
candidates[0].name = "shubham" ;
candidates[0].votes = 10 ;

candidates[1].name = "Survi" ;
candidates[1].votes = 20 ;

candidates[2].name = "Sharvil" ;
candidates[2].votes = 20 ;

printf("1st candidate name is %s\n", candidates[1].name);
vote("Shubham", candidates, 3);
printf("Vote count for Shubham is %i\n", candidates[0].votes);
print_winner(candidates, 3);
}


bool vote(string name ,  candidate candidates[], int len_array)
{
    for (int i = 0; i < len_array; i++)
    {
        if (strcasecmp(name, candidates[i].name) == 0)
        {
            candidates[i].votes++ ;
            return true;
        }
        
    }
    return false;
}

void print_winner(candidate candidates[], int len_array)
{
    int temp_highest = 0;
    for (int i = 0; i < len_array; i++)
    {
        if (candidates[i].votes > temp_highest)
        {
            temp_highest = candidates[i].votes;
        }
    }
    string winner[len_array];
    int count_winner = 0;
    for (int i = 0; i < len_array; i++)
    {
        if (candidates[i].votes == temp_highest)
        {
            winner[count_winner] = candidates[i].name;
            count_winner++;
        }
    }

    for (int i = 0; i < count_winner; i++)
    {
        printf("Winner: %s with %i votes\n", winner[i], temp_highest);
    }
}
