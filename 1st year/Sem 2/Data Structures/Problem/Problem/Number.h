#pragma once
#include <string>
using namespace std;
class Number
{
private:
	int value;
public:
	Number();
	~Number();
	Number(int v);
	int getValue();
	void setValue(int v);
	bool relation(Number a,string str);
	bool relationEqual(Number a);
};

