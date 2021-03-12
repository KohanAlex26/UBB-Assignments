#ifndef CSTACK_H
#define CSTACK_H

#include <iostream>
using namespace std;

typedef struct {
    int day;
}Planet;

typedef enum {
    ADD,
    REMOVE
} OperationType;

typedef struct {
    OperationType type;
    Planet* planet;
} Operation;

typedef Operation Element; // @todo replace int with Operation : typedef Operation Element;

const int MAX_STACK_SIZE = 100;

struct Stack {
    int top;  //index to top of the stack
    Element elements[MAX_STACK_SIZE]; //  MAX_STACK_SIZE - maximum size of stack
};

Stack createStack();
bool push(Stack* st, Element e);
Element pop(Stack* st);
bool isEmpty(Stack* st);
void print(Stack* st);
OperationType toString(Element e);



#endif // CSTACK_H
