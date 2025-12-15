#include <cs50.h>
#include <stdio.h>
#include <string.h>

// Max number of candidates
#define MAX 9

// Candidates have name and vote count
typedef struct
{
    string name;
    int votes;
} candidate;

// Array of candidates
candidate candidates[MAX];

// Number of candidates
int candidate_count;

// Function prototypes
bool vote(string name ,  candidate candidates[], int len_array);
void print_winner(candidate candidates[], int len_array);

int main(int argc, string argv[])
{
    // Check for invalid usage
    if (argc < 2)
    {
        printf("Usage: plurality [candidate ...]\n");
        return 1;
    }

    // Populate array of candidates
    candidate_count = argc - 1;
    if (candidate_count > MAX)
    {
        printf("Maximum number of candidates is %i\n", MAX);
        return 2;
    }
    for (int i = 0; i < candidate_count; i++)
    {
        candidates[i].name = argv[i + 1];
        candidates[i].votes = 0;
    }

    int voter_count = get_int("Number of voters: ");

    // Loop over all voters
    for (int i = 0; i < voter_count; i++)
    {
        string name = get_string("Vote: ");

        // Check for invalid vote
        if (!vote(name, candidates, candidate_count))
        {
            printf("Invalid vote.\n");
        }
    }

    // Display winner of election
    print_winner(candidates, candidate_count);
}

// Update vote totals given a new vote
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


// Print the winner (or winners) of the election
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
