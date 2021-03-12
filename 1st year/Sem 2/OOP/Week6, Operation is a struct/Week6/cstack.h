//#pragma once
//#include "Product.h"
//#include <iostream>
//
//using namespace std;
//
//typedef enum {
//    ADD,
//    REMOVE
//} OperationType;
//
////class Operation {
////private:
////    OperationType type;
////    Product* product;
////public:
////    Operation();
////    void update(OperationType newType,Product* p);
////    OperationType getType();
////    Product*  getProduct();
////    void setType(OperationType newType);
////    void setProduct(Product* newProduct);
////};
//
//typedef struct {
//    OperationType type;
//    Product p;
//}Operation;
//
//typedef Operation Element; // @todo replace int with Operation : typedef Operation Element;
//
//const int MAX_STACK_SIZE = 100;
//
//class Stack {
//private:
//    int top;  //index to top of the stack
//    Element elements[MAX_STACK_SIZE]; //  MAX_STACK_SIZE - maximum size of stack
//public:
//    Stack();
//    bool push(Element e);
//    Element pop();
//    bool isEmpty();
//    void print();
//    void Representation(Element e, char s[]);
//    void index(int i, Element e);
//    int getTop();
//    Element* getElements();
//};
//
//
//



#pragma once
#include "Product.h"
enum class OperationType { ADD, REMOVE, NONE };

typedef struct {
	OperationType type;
	Product p;
}Operation;

typedef Operation Element;

const int MAX_STACK_SIZE = 100;

class Stack
{
private:
	Element elements[MAX_STACK_SIZE];
	int top;
public:
	//constructor
	Stack();
	//destructor
	~Stack();
	//getter for top
	int getTop();
	//getter for a product of an element from a position i
	Product getElementsIproduct(int i);
	//getter for a type of an element from a position i
	OperationType getElementsItype(int i);
	//pushing an element into the stack
	bool push(Element e);
	//popping an element from the stack
	Element pop();
	//print the stack
	void printStack();
	void Representation(Element e, char s[]);
	void print();
};

