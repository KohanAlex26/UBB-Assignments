// GrilaOOP.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
using namespace std;
int main()
{
    /*int a[5] = { 1, 2, 3, 4, 5 };
    for (int i = 0; i < 5; i++)
    {
        printf("%d\n", a[i]);
        printf("%d\n", *(a + i));
        printf("%d\n", a + i);
        printf("%d\n", *a);
    }*/

        int x = -1;
        // Some code 
        cout << "Before try \n";
        try {
            cout << "Inside try \n";
            if (x < 0)
            {
                throw x;
                cout << "After throw (Never executed) \n";
            }
        }
        cout << 2;
        catch (int x) {
        }

        cout << "After catch (Will be executed) \n";
        return 0;

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
