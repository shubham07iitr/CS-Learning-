# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <ctype.h>
# include <stdlib.h>


int grade(int count_words, int count_sentences, int count_chars);
int* count(string test);
int main(void)
{
    string test = "Harry Potter was a highly unusual boy in many ways. For one thing, he hated the summer holidays more than any other time of year. For another, he really wanted to do his homework, but was forced to do it in secret, in the dead of the night. And he also happened to be a wizard.";
    int* countarray = count(test);
    printf("Count of words is %i\n", countarray[0]);
    printf("Count of sentences is %i\n", countarray[1]);
    printf("Count of chars is %i\n", countarray[2]);
    int index = grade(countarray[0], countarray[1], countarray[2]);
    printf("Index value is %i\n", index);



}



int* count(string test)
{
    int count_loop = strlen(test);
    int count_blanks = 0;
    int count_stops = 0;
    int* count_array = malloc(3 * sizeof(int));  // ✅ Allocate on heap
    if (count_array == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }

    for (int i = 0; i < strlen(test);  i++)
    {
        if (test[i] == ' ')
        {
            count_blanks += 1;
        }
        if (test[i] == '.')
        {
            count_stops += 1;
        }
    }
    count_array[0] = count_blanks+1;
    count_array[1] = count_stops;
    count_array[2] = strlen(test) - count_blanks - count_stops ;
    return count_array;
}



int grade(int count_words, int count_sentences, int count_chars)
{
    float L = ((float)count_chars / count_words) * 100;
    float S = ((float)count_sentences / count_words) * 100;

    printf("L is %.2f\n", L);
    printf("S is %.2f\n", S);

    float index = 0.0588 * L - 0.296 * S - 15.8;

    return (int) (index + 0.5);  // round to nearest int
}