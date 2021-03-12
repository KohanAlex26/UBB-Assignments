#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,j,k,l,s,n;
    for(i=9; i>=4; i--)
        for(j=i-1; j>=3; j--)
            for(k=j-1; k>=2; k--)
                for(l=k-1; l>=1; l--)
                {
                    s=i+j+k+l;
                    if(s==24)
                    {
                        n=i*1000+j*100+k*10+l;
                        printf("%d%c",n,'\n');
                    }
                }
    return 0;
}
