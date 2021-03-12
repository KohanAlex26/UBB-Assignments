#include "Name.h"
#include <iostream>
Name::Name()
{

}
Name::Name(string s)
{
	str = s;
}
Name::~Name()
{

}
string Name::getStr()
{
	return str;
}
void Name::setString(string s)
{
	str = s;
}