#include <stdio.h>
#include <stdlib.h>
/** @brief verify if the number "nr" is positive
@param nr number to be verified
@return 1 if the number is positive or 0 if it is negative
*/
int main()
{
    int n,i=0,j;
    char c,s[101],aux[100]="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ",aux2[100]="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    scanf("%d",&n);
    n=n%26;
    scanf("%s",&s);
    while(s[i]!='\0')
    {
        if(s[i]>='A' && s[i]<='Z')
        {
            j=26;
            while(j<=51 && aux[j]!=s[i])
                j++;
            s[i]=aux[j-n];
        }
        if(s[i]>='a' && s[i]<='z')
        {
            j=26;
            while(j<=51 && aux2[j]!=s[i])
                j++;
            s[i]=aux2[j-n];
        }
        i++;
    }
    printf("%s",s);
    return 0;
}
