#include "Number.h"



Number::Number()
{
	value = 0;
}

Number::Number(int v)
{
	value = v;
}


Number::~Number()
{
}

int Number::getValue()
{
	return value;
}

void Number::setValue(int v)
{
	value = v;
}

bool Number::relation(Number a,string str)
{
	if (str == "<")
	{
		if (value <= a.value)
			return true;
		return false;
	}
	else if (str == ">")
	{
		if (value >= a.value)
			return true;
		return false;
	}
	return false;
}

bool Number::relationEqual(Number a)
{
	if (value == a.value)
		return true;
	return false;
}
