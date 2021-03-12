#pragma once
#include <string>
using namespace std;
class Name
{
private:
	string str;
public:
	Name();
	Name(string s);
	~Name();
	string getStr();
	void setString(string s);
};

