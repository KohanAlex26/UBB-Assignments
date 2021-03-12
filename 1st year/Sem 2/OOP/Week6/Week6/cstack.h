#pragma once
#include "Product.h"
#include <iostream>

using namespace std;

typedef enum {
    ADD,
    REMOVE
} OperationType;

class Operation {
private:
    OperationType type;
    Product product;
public:
    Operation();
    void update(OperationType newType,Product p);
    OperationType getType();
    Product  getProduct();
    void setType(OperationType newType);
    void setProduct(Product newProduct);
};


typedef Operation Element; // @todo replace int with Operation : typedef Operation Element;

const int MAX_STACK_SIZE = 100;

class Stack {
private:
    int top;  //index to top of the stack
    Element elements[MAX_STACK_SIZE]; //  MAX_STACK_SIZE - maximum size of stack
public:
    Stack();
    bool push(Element e);
    Element pop();
    bool isEmpty();
    void print();
    void Representation(Element e, char s[]);
    void index(int i, Element e);
    int getTop();
    Element* getElements();
};


