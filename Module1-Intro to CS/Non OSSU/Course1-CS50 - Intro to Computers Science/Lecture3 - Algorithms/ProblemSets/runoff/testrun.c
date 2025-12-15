# include <cs50.h>
# include <stdio.h>
# include <string.h>
# include <ctype.h>
#include <stdbool.h>



int preferences[2][3];
typedef struct
{
    string name;
    int votes;
    bool eliminated;
} candidate;

candidate candidates[3];

bool vote(int voter, int rank, string name) ;

int main(void)

{
candidates[0].name = "shubham" ;
candidates[0].votes = 0 ;
candidates[0].eliminated = false;

candidates[1].name = "survi" ;
candidates[1].votes = 0 ;
candidates[1].eliminated = false;

candidates[2].name = "sharvil" ;
candidates[2].votes = 0 ;
candidates[2].eliminated = false;

vote(2, 3, "survi");
printf("%i\n", preferences[1][2]);


}

bool vote(int voter, int rank, string name) 
{
    for (int i = 0; i < 3; i++)
    {
        if (strcasecmp(name, candidates[i].name) == 0)
        {
            preferences[voter][i] = rank ;
            printf("This voter chose %s as his %i preference", candidates[i].name, rank);
            return true;
        }
        
    }
    return false;
}

void tabulate(void)
{
    for (int i= 0; i < voter_count; i++)
    {
        for (int j = 0; j < candidate_count; j++)
        {
            if (preferences[i][j] == 1 & candidate[i].eliminated == false)
            {
                candidates[j].votes++;
            }
        }

    }
    return;
}


bool print_winner(void)
{
    int total_votes = 0;
    for (int i = 0; i < candidate_count; i++)
    {
        total_votes += candidates[i].votes;
    }
    for (int i = 0; i < candidate_count; i++)
    {
        if (candidates[i].votes > total_votes/2)
        {
            return true;
        }
    }
    return false;
}

int find_min(void)
{
    int min_count = candidate_count[0].votes;
    for (int i = 1; i < candidate_count; i++)
    {
        if (candidates[i].votes < min_count)
        {
            min_count = candidates[i].votes;
        }
    }
    return min_count;
}

bool is_tie(int min)
{
    for (int i = 0 ; i < candidate_count; i++)
    {
        if (candidates[i].votes != min)
        {
            return false;
        }
    }
    return true;
}

void eliminate(int min)
{
    for (int i = 0 ; i < candidate_count; i++)
    {
        if (candidates[i].votes == min)
        {
            candidates[i].eliminated == true;
         
            //if a candidate is eliminated we have to ensure that anyone who chose that candidate as 1st preference should have their second and 3rd preference considered
            for (int j = 0; j < voter_count; j++)
            {
                if (preferences[j][i] == 1) // here == 1 refers to the fact that that voter had indicated the candidate as his first preference
                {
                    for (int k =0; k < candidate_count; k++)
                    {
                        preferences[j][k]--;
                    }
                }
            }
        }
    }
    
    return;
    
}
