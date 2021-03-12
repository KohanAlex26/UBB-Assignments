#pragma once
#include "Product.h"
#include <iostream>

using namespace std;

typedef enum {
    ADD,
    REMOVE
} OperationType;

typedef struct {
    OperationType type;
    Product product;
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
void Representation(Element e, char s[]);


