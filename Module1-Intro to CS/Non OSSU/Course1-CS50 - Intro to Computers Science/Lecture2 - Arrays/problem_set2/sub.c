# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>

char alphabet[26] = {
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
};



void convert(string key, string cipher);
int main(int argc, string argv[])
{
    if (argv[1] == NULL || argv[2] != NULL)
    {
        printf("Usage: ./substittion key \n");
        return 1;
    }
    else if (strlen(argv[1]) != 26)
    {
        printf("Key must contain 26 chars \n");
        return 1;
    }
    else
    {
        printf("All ok, you may enter no\n");
        string cipher = get_string("Plaintext: ");
        convert(argv[1], cipher);
    }
    
}

void convert(string key, string cipher)
{
    
    int loop_count = strlen(cipher);
    for (int i = 0; i < loop_count; i++)
    {
        for (int j = 0; j < 26; j++)
        {
            if (tolower(cipher[i]) ==  alphabet[j])
            {
                printf("Key match is index is %i\n", j);
                printf("Key match is %c\n", key[j]);
                if (islower(cipher[i]))
                {
                    cipher[i] = tolower(key[j]);
                    break;
                }
                else 
                {
                    cipher[i] = toupper(key[j]);
                    break;
                }
            }
        }
    }
    printf("CipherText: %s\n", cipher);
}
