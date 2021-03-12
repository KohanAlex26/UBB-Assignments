#include "cstack.h"
#include <stdio.h>
Stack createStack() {
    Stack st;
    st.top = -1;
    return st;
}

Element pop(Stack* st) {
    if (st->top < 0) {
        cout << "Stack Underflow" << endl;
        Element o;
        OperationType ot=ADD;
        Product p;
        strcpy(p.name, (char*)"");
        strcpy(p.category, (char*)"");
        p.quantity = 0;
        p.expDate.day = 0;
        p.expDate.month = 0;
        p.expDate.year = 0;
        o.type = ot;
        o.product = p;
        return o;
    }
    else {
        Element topEl = st->elements[(st->top)--];
        char str[100];
        Representation(topEl, str);
        printf("%spoped out of the stack \n", str);
        /*std::cerr << topEl << " popped out from the stack" << endl;*/
        return topEl;
    }
}

bool push(Stack* st, Element e) {
    if (st->top >= (MAX_STACK_SIZE - 1)) {
        cout << "Stack Overflow";
        return false;
    }
    else {
        st->elements[++(st->top)] = e;
        char s[100];
        Representation(e, s);
        printf("%spushed into stack \n", s);
        /*cout << e << " pushed into stack" << endl;*/
        return true;
    }
}

bool isEmpty(Stack* st)
{
    return (st->top < 0);
}

void print(Stack* s)
{
    cout << "Stack content: " << endl << "------ ";
    for (int idx = s->top; idx >= 0; idx--) {
        char str[100];
        Representation(s->elements[idx], str);
        /*printf("%s ", str);*/
        /*cout << toString(s->elements[idx]) << " ";*/
    }
    cout << endl;
}

void Representation(Element e,char s[])
{
    char aux[100] = "";
    Product p=e.product;
    toString(p,aux);
    char typeOfElem[50][30] = {
        "ADD",
        "REMOVE"
    };
    sprintf(s, "Element of type %s, %s",typeOfElem[e.type], aux);
}