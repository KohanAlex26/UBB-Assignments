#include <stdio.h>
#include <stdlib.h>
typedef struct
{
    int day,month,year;
} Date;

int main()
{
    Date date;
    int a,b,c,d,e,n;

    scanf("%d",&date.year);
    a=date.year%19;
    b=date.year%4;
    c=date.year%7;
    d=(19*a+24)%30;
    e=(2*b+4*c+6*d+5)%7;
    n=22+e+d;
    if(n<=31)
    {
        date.day=n;
        date.month=3;
        printf("%d%c%d",date.day,' ',date.month);
    }
    else
    {
        n=n-31;
        date.day=n;
        date.month=4;
        printf("%d%c%d",date.day,' ',date.month);
    }

    return 0;
}
