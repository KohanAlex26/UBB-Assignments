#include <assert.h>
#include "BinarySearchTree.h"
#include <string>
#include "Test.h"
#include <iostream>
using namespace std;

void testBinarySearchTree() {

	BinarySearchTree b("<");
	TElem e("a");
	TPriority p(1);
	BSTNode node(e, p);

    TElem e1("b");
    TPriority p1(0);
    BSTNode node1(e1, p1);

    TElem e2("c");
    TPriority p2(-1);
    BSTNode node2(e2, p2);

    TElem e3("d");
    TPriority p3(4);
    BSTNode node3(e3, p3);

	TPriority p4(5);

	assert(b.getRelation() == "<");
	assert(b.insert(&node) == 1);
	assert(b.insert(&node1) == 1);
	assert(b.insert(&node2) == 1);
	assert(b.insert(&node3) == 1);
	assert(b.getRoot()->getTElem().getStr() == e.getStr());


	assert(b.search(p2)->getTElem().getStr()==node2.getTElem().getStr());
	assert(b.search(p4) == nullptr);

	assert(b.getMin()->getTPriority().getValue() == node2.getTPriority().getValue());
	assert(b.getMax()->getTPriority().getValue() == node3.getTPriority().getValue());

	assert(b.isEmpty() == 0);
	TElem e6;
	TPriority p6;

	assert(b.pop(&e6,&p6) == 1);
	assert(b.getRoot()->getTElem().getStr() == e.getStr());
	assert(b.pop(&e6, &p6) == 1);
	assert(b.getRoot()->getTElem().getStr() == e1.getStr());
	assert(b.pop(&e6, &p6) == 1);
	assert(b.pop(&e6, &p6) == 1);
	assert(b.pop(&e6, &p6) == 0);
	assert(b.isEmpty() == 1);

	TElem e5("e");
	TPriority p5(5);
	BSTNode node5(e5, p5);
	b.setRoot(&node5);
	assert(b.getRoot()->getTElem().getStr()== node5.getTElem().getStr());
	assert(b.getRoot()->getTPriority().getValue() == node5.getTPriority().getValue());

	b.setRelation(">");
	assert(b.getRelation() == ">");
}