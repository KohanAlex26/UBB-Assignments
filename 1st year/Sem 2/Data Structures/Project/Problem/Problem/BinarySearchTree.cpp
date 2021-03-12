#include "BinarySearchTree.h"
#include<iostream>


BinarySearchTree::BinarySearchTree()
{
	root = nullptr;
}

BinarySearchTree::BinarySearchTree(string r)
{
	this->root = nullptr;
	rel = r;
}

BinarySearchTree::~BinarySearchTree()
{
	root = nullptr;
}

void BinarySearchTree::setRoot(BSTNode* root1)
{
	this->root = root1;
}

void BinarySearchTree::setRelation(string r)
{
	rel = r;
}

BSTNode* BinarySearchTree::search(TPriority p)
{
	if (root == nullptr)
		return nullptr;
	else
	{
		BSTNode* cn;
		cn = root;
		while (cn != nullptr)
		{
			if (p.relationEqual(cn->getTPriority()) == 1)
				return cn;
			if (p.relation(cn->getTPriority(), rel) == 1)
				cn = cn->getLeft();
			else if (p.relation(cn->getTPriority(), rel) == 0)
				cn = cn->getRight();
		}
	}
	return nullptr;
}

BSTNode* BinarySearchTree::getTop()
{
	if (root == nullptr)
		return nullptr;
	BSTNode* cn;
	cn = root;
	while (cn->getRight() != nullptr)
	{
		cn = cn->getRight();
	}
	return cn;
}

BSTNode* BinarySearchTree::getMin()
{
	if(root==nullptr)
		return nullptr;
	BSTNode* currentNode = root;
	while (currentNode->getLeft() != nullptr)
		currentNode = currentNode->getLeft();
	return currentNode;
}

BSTNode* BinarySearchTree::getMax()
{
	if (root == nullptr)
		return nullptr;
	BSTNode* currentNode = root;
	while (currentNode->getRight() != nullptr)
		currentNode = currentNode->getRight();
	return currentNode;
}

bool BinarySearchTree::insert(BSTNode* node)
{
	/*system("@cls||clear");*/
	if (root == nullptr)
	{
		root = new BSTNode;
		root->setTElem(node->getTElem());
		root->setTPriority(node->getTPriority());
		return true;
	}
	else
	{
		int ok = 1;
		BSTNode* cn;
		cn = root;
		while ((cn->getLeft() != nullptr or cn->getRight() != nullptr) and ok == 1)
		{
			if (node->getTPriority().relation(cn->getTPriority(), rel) == 1 and cn->getLeft() == nullptr)
			{
				cn->setLeft(node);
				ok = 0;
				return true;
			}
			else if (node->getTPriority().relation(cn->getTPriority(), rel) == 0 and cn->getRight() == nullptr)
			{
				cn->setRight(node);
				ok = 0;
				return true;
			}
			else if (node->getTPriority().relation(cn->getTPriority(), rel) == 1)
			{
				cn = cn->getLeft();
			}
			else if (node->getTPriority().relation(cn->getTPriority(), rel) == 0)
				cn = cn->getRight();
		}
		if (ok == 1)
		{
			if (node->getTPriority().relation(cn->getTPriority(), rel) == 1)
			{
				cn->setLeft(node);
				return true;
			}
			else
			{
				cn->setRight(node);
				return true;
			}
		}
	}
	return false;
}

bool BinarySearchTree::popRoot()
{
	system("@cls||clear");
	if (root == nullptr)
	{
		return false;
	}
	else
	{
		BSTNode* cn;
		if (root->getRight() == nullptr and root->getLeft() == nullptr)
		{
			root = nullptr;
			return true;
		}
		else if (root->getRight() != nullptr)
		{
			cn = root->getRight();
			if(cn->getLeft()==nullptr)
			{ 
				cn->setLeft(root->getLeft());
				root = cn;
			}
			return true;
		}
		else if (root->getLeft() != nullptr)
		{
			cn = root->getLeft();
			if (cn->getRight() == nullptr)
			{
				cn->setRight(root->getRight());
				root = cn;
			}
			return true;
		}
		else
		if (root->getRight() == nullptr and root->getLeft() != nullptr)
		{
			cn = root->getLeft();
			while (cn->getRight()->getRight() != nullptr)
			{
				cn = cn->getRight();
			}
			cn->getRight()->setLeft(root->getLeft());
			cn->getRight()->setRight(root->getRight());
			root = cn->getRight();
			cn->setRight(nullptr);
			return true;
		}
		else if (root->getLeft() == nullptr and root->getRight() != nullptr)
		{
			cn = root->getRight();
			while (cn->getLeft()->getLeft() != nullptr)
			{
				cn = cn->getLeft();
			}
			cn->getLeft()->setLeft(root->getLeft());
			cn->getLeft()->setRight(root->getRight());
			root = cn->getLeft();
			cn->setLeft(nullptr);
			return true;
		}
	}
	return false;
}

bool BinarySearchTree::isEmpty()
{
	if (root == nullptr)
		return true;
	return false;
}

bool BinarySearchTree::pop(TElem* e, TPriority* p)
{
	/*system("@cls||clear");*/
	if (root == nullptr)
	{
		return false;
	}
	else
	{
		BSTNode* cn;
		cn = root;
		if (cn->getRight() == nullptr and cn->getLeft() == nullptr)
		{
			*e = root->getTElem();
			*p = root->getTPriority();
			root = nullptr;
			return true;
		}
		else if (cn->getRight() == nullptr and cn->getLeft()!=nullptr)
		{
			*e = root->getTElem();
			*p = root->getTPriority();
			this->setRoot(root->getLeft());
			return true;
		}
		else
		{
			while (cn->getRight()->getRight() != nullptr)
			{
				cn = cn->getRight();
			}
			*e = cn->getRight()->getTElem();
			*p = cn->getRight()->getTPriority();
			if (cn->getRight()->getLeft() != nullptr)
			{
				if (cn->getRight()->getLeft()->getTPriority().relation(cn->getTPriority(), rel) == 1)
				{
					cn->setLeft(cn->getRight()->getLeft());
					cn->setRight(nullptr);
				}
				else
					cn->setRight(cn->getRight()->getLeft());
			}
			else
				cn->setRight(nullptr);
			return true;
		}
	}
}
