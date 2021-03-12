// test.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#include "Header.h"
#include <iostream>
#include <vector>
using namespace std;
int main()
{
    std::cout << "Hello World!\n";
    std::vector<string> v = { "Hello","brilliant","students","you are","the best","at","oop" };
    std::vector<MyString> jumboStrings;
    MyString s;
    int count = 0;
    for (int i = 0;i < v.size(); i++)
    {
        count++;
        s = s + v[i].c_str();
        if (count % 5 == 0)
        {
            jumboStrings.push_back(s);
            count = 0;
            s = MyString{};
        }
    }
    if (count != 0)
    {
        jumboStrings.push_back(s);
    }

    {
    MyString m;
    MyString m2("hello");
    MyString m3 = m2;
    MyString m4("OOP");
    MyString m5 = m2 + m4;
    bool less = m4 < m2;
    bool less = m4 < "Jasd";
    cout << less;
    cout << (m4 < m2);
    cout << "m1 is " << m << '\n';
    cout << "m1 is " << m2 << '\n';
    cout << "m1 is " << m3 << '\n';
    cout << "m5 is " << m5 << '\n';
    }
    cout << "living: " << MyString::getLivingCount() << endl;
    cout << "created: " << MyString::getCreatedCount() << endl;
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
