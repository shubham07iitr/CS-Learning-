# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

char alphabet[26] = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
};


char convert(int key, char letter);
int main(int argc, string argv[])
{
    if (argv[1] == NULL)
    {
        printf("Usage: ./caesar key \n");
    }
    
    else 
    {
        int converted_key = atoi(argv[1]);
        if (converted_key == 0 || argv[2] != NULL)
        {
            printf("Usage: ./caesar key \n");
        }
        else if (converted_key < 0)
        {
            printf("Pls input a positive integer only");
        }
        else
        {
            string to_be_converted = get_string("Plaintext:");
            int loop_count = strlen(to_be_converted);
            string converted_string;
            for (int i = 0; i < loop_count; i++)
            {
                converted_string[i] = convert(converted_key, to_be_converted[i]);
            }
            printf("Final converted string is %s\n", converted_string);
        }

    }
    
}


char convert(int key, char letter)
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
    return return_char;
}