# include <stdio.h>
# include <cs50.h>
# include <string.h>
# include <stdlib.h>

int main(void)

{
    int num = get_int("What is the number: ");
    char str[10] = {0};
    sprintf(str, "%d", num);  // Convert int to string
    int return_value = 0;
    for (int i = 0; i< strlen(str); i++)
    {
        return_value += str[i] - '0';
        printf("current_return_value is %i\n", return_value);

    }
    printf("sum of digits is, %i\n", return_value);
    int a = 5;
    int b = 2;
    int random_no = a/b;
    printf("%i\n", random_no);
}