#include <iostream>
using namespace std;

#include "cstack.h"
int main()
{
    Stack s = createStack();
    Element e;
    e.type = ADD;
    Planet p1;
    p1.day = 1;
    push(&s, e);
    print(&s);
    cout << 123;
    //push(&s, 10); // when adding, instead of integers, add Operations
    //push(&s, 20);
    //push(&s, 15);
    //push(&s, 30);
    //print(&s);
    //pop(&s);
    //print(&s);
    //pop(&s);
    //pop(&s);
    //pop(&s);
    //pop(&s);

    return 0;
}
