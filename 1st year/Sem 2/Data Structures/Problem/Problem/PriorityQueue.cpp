#include "PriorityQueue.h"
#include<iostream>


PriorityQueue::PriorityQueue()
{
	root = nullptr;
}

PriorityQueue::PriorityQueue(string r)
{
	this->root = nullptr;
	rel = r;
}

PriorityQueue::~PriorityQueue()
{
	root = nullptr;
}


Client* PriorityQueue::top()
{
	if (root == nullptr)
		return nullptr;
	Client* cn;
	cn = root;
	while (cn->getRight() != nullptr)
	{
		cn = cn->getRight();
	}
	return cn;
}


bool PriorityQueue::push(Client* client)
{
	/*system("@cls||clear");*/
	if (root == nullptr)
	{
		root = new Client;
		root->setName(client->getName());
		root->setNumber(client->getNumber());
		return true;
	}
	else
	{
		int ok = 1;
		Client* cn;
		cn = root;
		while ((cn->getLeft() != nullptr or cn->getRight() != nullptr) and ok == 1)
		{
			if (client->getNumber().relation(cn->getNumber(), rel) == 1 and cn->getLeft() == nullptr)
			{
				cn->setLeft(client);
				ok = 0;
				return true;
			}
			else if (client->getNumber().relation(cn->getNumber(), rel) == 0 and cn->getRight() == nullptr)
			{
				cn->setRight(client);
				ok = 0;
				return true;
			}
			else if (client->getNumber().relation(cn->getNumber(), rel) == 1)
			{
				cn = cn->getLeft();
			}
			else if (client->getNumber().relation(cn->getNumber(), rel) == 0)
				cn = cn->getRight();
		}
		if (ok == 1)
		{
			if (client->getNumber().relation(cn->getNumber(), rel) == 1)
			{
				cn->setLeft(client);
				return true;
			}
			else
			{
				cn->setRight(client);
				return true;
			}
		}
	}
	return false;
}


bool PriorityQueue::isEmpty()
{
	if (root == nullptr)
		return true;
	return false;
}

bool PriorityQueue::pop(Name* e, Number* p)
{
	/*system("@cls||clear");*/
	if (root == nullptr)
	{
		return false;
	}
	else
	{
		Client* cn;
		cn = root;
		if (cn->getRight() == nullptr and cn->getLeft() == nullptr)
		{
			*e = root->getName();
			*p = root->getNumber();
			root = nullptr;
			return true;
		}
		else if (cn->getRight() == nullptr and cn->getLeft()!=nullptr)
		{
			*e = root->getName();
			*p = root->getNumber();
			root=root->getLeft();
			return true;
		}
		else
		{
			while (cn->getRight()->getRight() != nullptr)
			{
				cn = cn->getRight();
			}
			*e = cn->getRight()->getName();
			*p = cn->getRight()->getNumber();
			if (cn->getRight()->getLeft() != nullptr)
			{
				if (cn->getRight()->getLeft()->getNumber().relation(cn->getNumber(), rel) == 1)
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
