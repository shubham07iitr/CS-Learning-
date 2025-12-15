# include <stdio.h>
# include <cs50.h>
# include <string.h>
#include <ctype.h>

char letters[26] = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
    'U', 'V', 'W', 'X', 'Y', 'Z'
};
int scores[26] = {
    1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 
    5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 
    1, 4, 4, 8, 4, 10
};

int score_letter(char letter);
int score_scrabble(string word);
int main(void)
{
    string word1 = get_string("Please enter Player1 word\n");
    string word2 = get_string("Please enter Player2 word\n");
    int score1 = score_scrabble(word1);
    int score2 = score_scrabble(word2);
    if (score1 > score2)
    {
        printf("Player 1 score %i, player 2 score %i, player 1 wins", score1, score2);
    }
    if (score2 > score1)
    {
        printf("Player 1 score %i, player 2 score %i, player 2 wins", score1, score2);
    }
    else 
    {
        printf("Player 1 score %i, player 2 score %i, TIE", score1, score2);
    }
}

int score_scrabble(string word)
{
    int word_score = 0;
    int loop_count = strlen(word);
    for (int i = 0; i < loop_count; i++)
    {
     word_score += score_letter(word[i]);
     printf("Current letter is ,%c, current score is %i\n", word[i], word_score);
    }
    return word_score;
}

int score_letter(char letter)
{
    int letter_score = 0;
    for (int i = 0; i < 26; i++) 
    {
        if (toupper(letter) == letters[i])
        {
            letter_score =  scores[i];
            
        }

    }
    return letter_score;

}