#include "TPriority.h"
#include "TElem.h"
#include "BSTNode.h"
#include "BinarySearchTree.h"
#include <string>
#include "Test.h"
#include <iostream>

void printMenu() {
    cout << "1. Add a node" << endl;
    cout << "2. Remove the node with the highest priority" << endl;
    cout << "3. Print root" << endl;
    cout << "0. Exit" << endl;
}

void init(BinarySearchTree t,string r)
{
    t.setRelation(r);
}
void destroy(BinarySearchTree t)
{
    t.setRoot(nullptr);
}
void push(BinarySearchTree t,TElem e,TPriority p)
{
    BSTNode* node1 = new BSTNode(e, p);
    t.insert(node1);
}
void pop(BinarySearchTree t, TElem e, TPriority p)
{
    t.pop(&e, &p);
}
void top(BinarySearchTree t, TElem e, TPriority p)
{
    e=t.getTop()->getTElem();
    p = t.getTop()->getTPriority();
}
bool isEmpty1(BinarySearchTree t)
{
    if (t.isEmpty() == 1)
        return true;
    return false;
}
void application() {
    int option = 1;
    string rel;
    printf("Read the relation of the BinarySearchTree('<' or '>'):");
    std::cin >> rel;
    BinarySearchTree  t(rel);

    TElem e("alin");
    TPriority p(1);
    BSTNode node(e, p);

    TElem e1("bogdan");
    TPriority p1(-1);
    BSTNode node1(e1, p1);

    TElem e2("calin");
    TPriority p2(0);
    BSTNode node2(e2, p2);

    TElem e3("darius");
    TPriority p3(4);
    BSTNode node3(e3, p3);

    TElem e4("emanuel");
    TPriority p4(5);
    BSTNode node4(e4, p4);

    t.insert(&node);
    t.insert(&node1);
    t.insert(&node2);
    t.insert(&node3);
    t.insert(&node4);

    while (option != 0) {
        printMenu();
        cin >> option;
        printf("\n");
        if (option == 1) {
            string s;
            printf("Read the name of the customer:");
            cin >> s;
            TElem e(s);
            int value;
            printf("Read the value(int):");
            cin >> value;
            TPriority p(value);
            BSTNode* node=new BSTNode(e, p);
            t.insert(node);
        }
        else if (option == 2) {
            TElem e;
            TPriority p;
            t.pop(&e,&p);
            cout << "Removed"<<endl;
        }
        else if (option == 3)
        {
            if(t.getRoot()!=nullptr)
            t.getTop()->representation();
        }
        else if (option != 0) {
            cout << "This is not a valid option. Try again!" << endl;
        }
    }
}

int main()
{
    testBinarySearchTree();
    application();

}