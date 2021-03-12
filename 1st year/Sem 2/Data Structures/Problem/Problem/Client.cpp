#include "Client.h"

#include <iostream>
#include <string>
Client::Client()
{
	left = nullptr;
	right = nullptr;
	Name e1;
	e1.setString("");
	e = e1;
	Number p1;
	p1.setValue(0);
	p = p1;
}

Client::Client(Name e1,Number p1)
{
	left = nullptr;
	right = nullptr;
	e = e1;
	p = p1;
}


Client::~Client()
{
}

void Client::setLeft(Client* client)
{
	this->left = client;
}

void Client::setRight(Client* client)
{
	this->right = client;
}

void Client::setName(Name e1)
{
	e.setString(e1.getStr());
}

void Client::setNumber(Number p1)
{
	p.setValue(p1.getValue());
}

void Client::representation()
{
	std::cout << e.getStr() << " " << p.getValue()<<endl;
}
