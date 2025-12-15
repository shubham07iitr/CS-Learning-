# include <cs50.h>
# include <stdio.h>
# include <ctype.h>
# include <string.h>
# include <stdlib.h>

int main(void)
{
    string s = get_string("s: ");
    string t = s; //this will create a copy of the address so both will be pointing to the first letter of the string 
    t[0] = toupper(t[0]); //Now this will change the first letter of the string , and since both the pointers are towards this both s and t will be changed
    printf("s: %s\n", s);
    printf("t: %s\n", t);
}   