#pragma once
#include "Name.h"
#include "Number.h"
class Client
{
private:
	Name e;
	Number p;
	Client* left;
	Client* right;
public:
	Client();
	Client(Name e1, Number p1);
	~Client();
	Client* getLeft() { return this->left; }
	Client* getRight() { return this->right; }
	Name getName() { return e; }
	Number getNumber() { return p; }
	void setLeft(Client*);
	void setRight(Client*);
	void setName(Name e);
	void setNumber(Number p);
	void representation();
};
