#pragma once
#include <string>
using namespace std;
class TPriority
{
private:
	int value;
public:
	TPriority();
	~TPriority();
	TPriority(int v);
	int getValue();
	void setValue(int v);
	bool relation(TPriority a,string str);
	bool relationEqual(TPriority a);
};

