# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>


char alphabet[26] = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
};


void convert(int key, char letter);
int main(void)
{
    char letter = '?';
    int key = 2;
    convert(key, letter);

}

void convert(int key, char letter)
{
    char return_char = letter;
    for (int j = 0; j < 26; j++)
        {

            if (toupper(letter) == alphabet[j]) 
            {
                if (j+key <= 25)
                {
                    if (islower(letter))
                    {
                        return_char = tolower(alphabet[j+key]);
                    }
                    else
                    {
                        return_char = toupper(alphabet[j+key]);   
                    }
                    
                }
                else 
                {
                    if (islower(letter))
                    {
                        return_char = tolower(alphabet[j+key-26]);
                    }
                    else
                    {
                        return_char = toupper(alphabet[j+key-26]);
                    }

                }
            }
        }
    printf("return char is %c\n", return_char);
}