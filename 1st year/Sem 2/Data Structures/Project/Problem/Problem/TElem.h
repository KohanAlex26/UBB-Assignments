#pragma once
#include <string>
using namespace std;
class TElem
{
private:
	string str;
public:
	TElem();
	TElem(string s);
	~TElem();
	string getStr();
	void setString(string s);
};

