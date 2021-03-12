#pragma once
#include"BSTNode.h"
#include <string>
using namespace std;
class BinarySearchTree
{
private:
	BSTNode* root;
	string rel;
public:
	BinarySearchTree();
	BinarySearchTree(string r);
	~BinarySearchTree();
	void setRoot(BSTNode*);
	BSTNode* getRoot() { return this->root; }
	void setRelation(string r);
	string getRelation() { return this->rel; }
	BSTNode* search(TPriority p);
	BSTNode* getTop();
	BSTNode* getMin();
	BSTNode* getMax();
	bool isEmpty();
	bool insert(BSTNode* node);
	bool popRoot();
	bool pop(TElem* e,TPriority* p);
};

