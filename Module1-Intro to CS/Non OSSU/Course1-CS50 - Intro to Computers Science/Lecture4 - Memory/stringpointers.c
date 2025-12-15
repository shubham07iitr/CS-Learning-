# include <stdio.h>

int main(void)
{
    char *s = "HI!";
    printf("%s\n", s); //will print value of string s in this case HI!
    printf("%p\n", s); //will print address of s
    printf("%p\n", &s[0]); //will print address of 1st element of string s
    printf("%p\n", &s[1]); //will print address of 2nd element of string s which will ne consecutive
    printf("%p\n", &s[2]);
    printf("%p\n", &s[3]);


    // pointer arithmetic 
    printf("%c\n", *s);
    printf("%c\n", *(s+1)); // will print the 2nd character which is "I" in this case
    printf("%c\n", *(s+2)); // will print the 3rd character which is "!" in this case
    printf("%c\n", *(s+3)); // will print the 4th character which is Null in this case
    printf("%c\n", *(s+4)); // will print the 2nd character which is overflow


    printf("%s\n", s);
    printf("%s\n", s+1); //this will print I! because string will start from I
    printf("%s\n", s+2);
}