#pragma once
#include"Client.h"
#include <string>
using namespace std;
class PriorityQueue
{
private:
	Client* root;
	string rel;
public:
	PriorityQueue();
	PriorityQueue(string r);
	~PriorityQueue();
	Client* top();
	bool isEmpty();
	bool push(Client* client);
	bool pop(Name* e,Number* p);
};

