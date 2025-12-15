# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>

string to_upper_string(string name);

int main()
{
    string name = "shubham";
    string upper_name = to_upper_string(name);
    printf("Upper case string is %s\n", upper_name);
}

string to_upper_string(string name)
{
    
    int loop_count = strlen(name);
    for (int i=0; i <= loop_count; i++)
    {
        name[i] = toupper(name[i]);
        
    }
    return name;
}