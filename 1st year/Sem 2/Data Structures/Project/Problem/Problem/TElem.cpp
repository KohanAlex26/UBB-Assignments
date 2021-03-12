#include "TElem.h"
#include <iostream>
TElem::TElem()
{

}
TElem::TElem(string s)
{
	str = s;
}
TElem::~TElem()
{

}
string TElem::getStr()
{
	return str;
}
void TElem::setString(string s)
{
	str = s;
}