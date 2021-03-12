#pragma once
#include "TElem.h"
#include "TPriority.h"
class BSTNode
{
private:
	TElem e;
	TPriority p;
	BSTNode* left;
	BSTNode* right;
public:
	BSTNode();
	BSTNode(TElem e1, TPriority p1);
	~BSTNode();
	BSTNode* getLeft() { return this->left; }
	BSTNode* getRight() { return this->right; }
	TElem getTElem() { return e; }
	TPriority getTPriority() { return p; }
	void setLeft(BSTNode*);
	void setRight(BSTNode*);
	void setTElem(TElem e);
	void setTPriority(TPriority p);
	void representation();
};
