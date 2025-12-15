#include <cs50.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <ctype.h>


string get_guess(int wordsize);
int check_word(string guess, int wordsize, int status[], string choice);
int main (int argc, string argv[])
{
    //todo1 proper usage
    if (argv[1] == NULL || argv[2] != NULL || strlen(argv[1]) > 1)
    {
        printf("Usage: ./wordle wordsize \n");
        return 1;
    }
   //todo2 only accept values b/w 5,6,7,8
    else
    {
        int acceptable_size[4] = {'5','6','7','8'};
        int count_nomatch = 0;
        for (int i =0; i < 4; i++)
        {
            if (*argv[1] != acceptable_size[i])
            {
                count_nomatch++ ;
            }
        }
        if (count_nomatch == 4)
        {
            printf("wordsize must be either 5, 6, 7, or 8\n");
            return 1;
        }
    }
    int wordsize =  *argv[1] -'0';
    printf("Congratulations you have moved forward and wordsize is %i\n", wordsize*2);
    string guess = get_guess(wordsize);
    printf("Guessed word is %s\n", guess);
    string choice = "games";
    int status[5] = {0,0,0,0,0};
    int score = check_word(guess, wordsize, status, choice);
    printf("The total score is %i\n", score);


}

string get_guess(int wordsize)
{
    while (true)
    {
        string guess = get_string("Please enter a %i letter word\n", wordsize);
        if (strlen(guess) == wordsize)
        {
            return guess;
        }

    }
}

int check_word(string guess, int wordsize, int status[], string choice)
{
    int score = 0;
    for (int i = 0; i < wordsize; i++)
    {
        for (int j = 0; j < wordsize; j++)
        {
            if (guess[i] == choice[j] && i == j)
            {
                status[i] = 2;
                break;
            }
            else if (guess[i] == choice[j] && i !=j)
            {
                status[i] = 1;
            }
        }
    }
    for (int i = 0; i < wordsize; i++)
    {
        score += status[i];
    }
    return score;
}