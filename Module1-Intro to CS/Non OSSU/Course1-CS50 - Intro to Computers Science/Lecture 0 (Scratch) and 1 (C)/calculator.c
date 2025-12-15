# include <stdio.h>
# include <cs50.h>

int main(void)
{
    long x = get_long("X: \n");
    long y = get_long("Y: \n");
    double z = (double) x/ (double) y; //we have to define float x and float y, or else result will be integer only
    printf("%.20f\n", z); //%li means we are leaving space for long format
}