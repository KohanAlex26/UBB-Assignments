//#include "cstack.h"
//#include <stdio.h>
//#include <string.h>
//#include "Product.h"
//Stack::Stack() {
//    top = -1;
//}
//int Stack::getTop()
//{
//    return top;
//}
//
//Element* Stack::getElements()
//{
//    return elements;
//}
////Operation::Operation()
////{
////    type = ADD;
////    Date d;
////    d.day = 1;
////    d.month = 1;
////    d.year = 2000;
////    Product p((char*)"a", (char*)"dairy", 1, d);
////    product = &p;
////}
////void Operation::setType(OperationType newType)
////{
////    type = newType;
////}
////void Operation::setProduct(Product* newProduct)
////{
////    product = newProduct;
////}
////OperationType Operation::getType()
////{
////    return type;
////}
////Product* Operation::getProduct()
////{
////    return product;
////}
////void Operation::update(OperationType newType, Product* p)
////{
////    type = newType;
////    product = p;
////}
//
//Element Stack::pop() {
//    if (top < 0) {
//        cout << "Stack Underflow" << endl;
//        Element o;
//        OperationType ot = ADD;
//        Product p((char*)"", (char*)"", 0, { 1,1,1 });
//        o.setType(ot);
//        o.setProduct(&p);
//        return o;
//    }
//    else {
//        Element topEl = elements[(top)--];
//        char str[100]="";
//        Representation(topEl, str);
//        printf("%spoped out of the stack \n", str);
//        /*std::cerr << topEl << " popped out from the stack" << endl;*/
//        return topEl;
//    }
//}
//
//bool Stack::push(Element e) {
//    if (top >= (MAX_STACK_SIZE - 1)) {
//        cout << "Stack Overflow";
//        return false;
//    }
//    else {
//        elements[++(top)] = e;
//        char s[100];
//        Representation(e, s);
//        /*printf("%spushed into stack \n", s);*/
//        /*cout << e << " pushed into stack" << endl;*/
//        return true;
//    }
//}
//
//bool Stack::isEmpty()
//{
//    return (top < 0);
//}
//
//void Stack::print()
//{
//    cout << "Stack content: " << endl << "------ ";
//    for (int idx = top; idx >= 0; idx--) {
//        char str[100];
//        Representation(elements[idx], str);
//        printf("%s ", str);
//        /*cout << toString(s->elements[idx]) << " ";*/
//    }
//    cout << endl;
//}
//
//void Stack::Representation(Element e, char s[])
//{
//    char aux[100] = "";
//    Product* p = e.getProduct();
//    p->toString(aux);
//    char typeOfElem[50][30] = {
//        "ADD",
//        "REMOVE"
//    };
//    //cout << typeOfElem[e.getType()];
//    sprintf(s, "Element of type %s, %s", typeOfElem[e.getType()], aux);
//
//}
//void Stack::index(int i,Element e)
//{
//    elements[i] = e;
//    top++;
//}







#include "Cstack.h"
#include <iostream>
using namespace std;

Stack::Stack() {
	top = -1;
}

Stack::~Stack() {

}

int Stack::getTop() {
	return top;
}

Product Stack::getElementsIproduct(int i) {
	return elements[i].p;
}

OperationType Stack::getElementsItype(int i) {
	return elements[i].type;
}

bool Stack::push(Element e) {
	    if (top >= (MAX_STACK_SIZE - 1)) {
	        cout << "Stack Overflow";
	        return false;
	    }
	    else {
	        elements[++(top)] = e;
	        char s[100];
	        Representation(e, s);
	        /*printf("%spushed into stack \n", s);*/
	        /*cout << e << " pushed into stack" << endl;*/
	        return true;
	    }
	}

Element Stack::pop() {
    if (top < 0) {
        cout << "Stack Underflow" << endl;
        Element o;
        o.type= OperationType ::ADD;
        Product p((char*)"", (char*)"", 0, { 1,1,1 });
        o.p = p;
        return o;
    }
    else {
        Element topEl = elements[(top)--];
        char str[100]="";
        Representation(topEl, str);
        printf("%spoped out of the stack \n", str);
        /*std::cerr << topEl << " popped out from the stack" << endl;*/
        return topEl;
    }
}


void Stack::print()
{
    cout << "Stack content: " << endl << "------ ";
    for (int idx = top; idx >= 0; idx--) {
        char str[100];
        Representation(elements[idx], str);
        printf("%s ", str);
        /*cout << toString(s->elements[idx]) << " ";*/
    }
    cout << endl;
}


void Stack::Representation(Element e, char s[])
{
    char aux[100] = "";
    Product* p = &e.p;
    p->toString(aux);
    char typeOfElem[50][30] = {
        "ADD",
        "REMOVE"
    };
    //cout << typeOfElem[e.getType()];
    if (e.type == OperationType::ADD)
    sprintf(s, "Element of type %s, %s", typeOfElem[0], aux);
    if (e.type == OperationType::REMOVE)
    sprintf(s, "Element of type %s, %s", typeOfElem[1], aux);
}