#include "TPriority.h"



TPriority::TPriority()
{
	value = 0;
}

TPriority::TPriority(int v)
{
	value = v;
}


TPriority::~TPriority()
{
}

int TPriority::getValue()
{
	return value;
}

void TPriority::setValue(int v)
{
	value = v;
}

bool TPriority::relation(TPriority a,string str)
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

bool TPriority::relationEqual(TPriority a)
{
	if (value == a.value)
		return true;
	return false;
}
