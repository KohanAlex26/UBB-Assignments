#include "BSTNode.h"

#include <iostream>
#include <string>
BSTNode::BSTNode()
{
	left = nullptr;
	right = nullptr;
	TElem e1;
	e1.setString("");
	e = e1;
	TPriority p1;
	p1.setValue(0);
	p = p1;
}

BSTNode::BSTNode(TElem e1,TPriority p1)
{
	left = nullptr;
	right = nullptr;
	e = e1;
	p = p1;
}


BSTNode::~BSTNode()
{
}

void BSTNode::setLeft(BSTNode* node)
{
	this->left = node;
}

void BSTNode::setRight(BSTNode* node)
{
	this->right = node;
}

void BSTNode::setTElem(TElem e1)
{
	e.setString(e1.getStr());
}

void BSTNode::setTPriority(TPriority p1)
{
	p.setValue(p1.getValue());
}

void BSTNode::representation()
{
	std::cout << e.getStr() << " " << p.getValue()<<endl;
}
