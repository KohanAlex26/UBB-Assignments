#ifndef DOMAIN_H
#define DOMAIN_H

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
/** @brief verify if the number "nr" is positive
@param nr - [in] number to be verified
@return 1 if the number is positive or 0 if it is negative
*/
int IsPositive(int nr)
{
    if (nr >= 0)
        return 1;
    return 0;
}
/** @brief verify if the number "nr" is Well Ordered(strictly ascending or strictly descending or random order)
@param nr - [in] number to be verified
@return 1 if the number is strictly ascending, -1 if it strictly descending, 0 if it is random and 2 if it is less than 10
*/
int IsWellOrdered(int nr)
{
    int digit, cresc = 1, descresc = 1, random = 1, aux;
    if (nr <= 9)
        return 2;
    aux = nr % 10;
    nr = nr / 10;
    while (nr > 0)
    {
        digit = nr % 10;
        if (aux == digit)
        {
            cresc = 0;
            descresc = 0;
        }
        if (aux > digit)
            descresc = 0;
        if (aux < digit)
            cresc = 0;
        nr = nr / 10;
        aux = digit;
    }

    if (cresc == 1)
        return 1;
    if (descresc == 1)
        return -1;
    return 0;
}
/** @brief gets the longest subsequence with Well Ordered numbers
@param nr - [in] number of elements in the vector
@param v - [in] the vector
@param start - [out] where the sequence starts from
@param stop - [out] where the sequence stops
@return start and stop, where the sequence starts and where it stops
*/
void longestSubseq(int nr, int v[], int* start, int* stop, int(*IsWellOrdered)(int), int(*IsPositive)(int))
{
    int mx = 0, k = 0, i = 0, aux = 0;
    while (i <= nr - 1)
    {
        if ((IsWellOrdered(v[i]) == 1 || IsWellOrdered(v[i]) == -1) && IsPositive(v[i]) == 1)
            k++;
        else if (k > mx)
        {
            mx = k;
            k = 0;
            *start = aux;
            *stop = i - 1;
            aux = i + 1;
        }
        else
        {
            aux = i + 1;
            k = 0;
        }
        i++;
    }
    if (k > mx)
    {
        *start = aux;
        *stop = i - 1;
        printf("%d\n%d\n\n", *start, *stop);
    }
}
void menu_option(int option)
{
    if (option == 0)
        printf("%s", "Quitting");
    else if (option == 1)
    {
        int nr;
        printf("My number is: ");
        scanf("%d", &nr);
        if (IsPositive(nr) == 1)
            printf("Yes\n\n");
        else
            printf("No\n\n");
    }
    else if (option == 2)
    {
        int nr;
        printf("My number is: ");
        scanf("%d", &nr);
        if (IsWellOrdered(nr) == 1)
            printf("Ascending\n\n");
        else if (IsWellOrdered(nr) == -1)
            printf("Descending\n\n");
        else if (IsWellOrdered(nr) == 0)
            printf("Random\n\n");
        else
            printf("Try bigger than 9\n\n");

    }
    else if (option == 3)
    {
        int nr, v[100], start = 0, stop = -1, i;
        printf("Number of elements: ");
        scanf("%d", &nr);
        for (i = 0; i <= nr - 1; i++)
        {
            printf("Element: ");
            scanf("%d", &v[i]);
        }
        longestSubseq(nr, v, &start, &stop, IsWellOrdered, IsPositive);
        printf("%d\n%d", start, stop);
        if (stop != -1)
        {
            printf("Longest subsequence is:\n");
            for (i = start; i <= stop; i++)
                printf("%d%s", v[i], " ");
            printf("\n\n");
        }
        else
            printf("No well ordered numbers\n\n");
    }
    else
        printf("Invalid option!\n\n");
}
int main()
{
    int option = -1, i;
    char s[100][100] = { "Quit","IsPositive","IsWellOrdered","LongestSubseq" };
    while (option != 0)
    {
        for (i = 0; i <= 3; i++)
            printf("%d%s%s%c", i, ". ", s + i, '\n');
        scanf("%d", &option);
        menu_option(option);
    }
    return 0;
}


#endif // !DOMAIN_H