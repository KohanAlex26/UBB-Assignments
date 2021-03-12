#include "ProductRepo.h"
#include <stdlib.h>
#include <cstring>
#include <ctype.h>
#include <iostream>
using namespace std;

ProductRepo::ProductRepo(int capacityRepo)
{
	elems = (Product*)malloc(sizeof(Product) * capacityRepo);
	capacity = capacityRepo;
	length = -1;
}

bool ProductRepo::add(Product* p)
{
	bool found = false;
	int i = 0;
	while (i <= length && found == false)
	{
		if (strcmp((elems[i]).getName(), (*p).getName()) == 0)
			found = true;
		else
			i++;
	}
	if (found == true)
	{
		elems[i].setQuantity(elems[i].getQuantity() + (*p).getQuantity());
	}
	else
	{
		if (capacity == length)
		{
			elems = (Product*)realloc(elems, 2 * sizeof(Product) * capacity);
			capacity = 2 * capacity;
			if (elems == nullptr)
				return false;
		}
		elems[length + 1] = *p;
		length = length + 1;
	}
	return true;
}

bool ProductRepo::remove(Product* p)
{
	int i = 0, j;
	bool found = false;
	while (i <= length and found == false)
	{
		if (strcmp(elems[i].getName(), (*p).getName()) == 0)
			found = true;
		else
			i++;
	}
	if (found == false)
		return false;
	for (j = i;j <= length;j++)
		elems[j] = elems[j + 1];
	length = length - 1;
	return true;
}

bool ProductRepo::update(Product *p, int index)
{
	if (index<0 || index>length)
		return false;
	elems[index].setName((*p).getName());
	elems[index].setCategory((*p).getCategory());
	elems[index].setQuantity((*p).getQuantity());
	elems[index].setExpDate((*p).getExpDate());
	return true;
}

void ProductRepo::displayByName(char* str)
{
	int i, j;
	char string[1000] = "";
	if (length == -1)
		printf("Empty repository\n\n");
	else if (strcmp((char*)"", str) == 0)
	{
		for (i = 0;i <=length - 1;i++)
			for (j = i + 1;j <= length;j++)
				if (elems[i].getQuantity() > elems[j].getQuantity())
				{
					Product p((char*)"", (char*)"", 0, { 0,0,0 });
					p = elems[i];
					elems[i] = elems[j];
					elems[j] = p;
				}
		for (i = 0;i <= length;i++)
		{
			char aux[100] = "";
			elems[i].toString(aux);
			strcat(string, aux);
		}
		printf("%s", string);
	}
	else
	{
		for (i = 0;i <= length - 1;i++)
			for (j = i + 1;j <= length;j++)
				if (strstr(elems[i].getName(), str) != NULL && strstr(elems[j].getName(), str) != NULL && elems[i].getQuantity() > elems[j].getQuantity())
				{
					Product p((char*)"", (char*)"", 0, { 0,0,0 });
					p = elems[i];
					elems[i] = elems[j];
					elems[j] = p;
				}
		for (i = 0;i <= length;i++)
		{
			if (strstr(elems[i].getName(), str) != NULL)
			{
				char aux[100] = "";
				elems[i].toString(aux);
				strcat(string, aux);
			}
		}
		printf("%s", string);
	}
}

void ProductRepo::displayByCategory(char* category, int x, Date d)
{
	int i;
	char s[100] = "";
	if (strcmp(s, category) == 0)
	{
		for (i = 0;i <= length;i++)
		{
			Date date = elems[i].getExpDate();
			int m = x / 30;
			int rd = x % 30;
			int y = m / 12;
			int rm = m % 12;

			if (date.day + rd > 30)
			{
				date.month++;
				date.day = (date.day + rd) % 30;
			}
			else
				date.day = date.day + rd;

			if (date.month + rm > 12)
			{
				date.year++;
				date.month = (date.month + rm) % 12;
			}
			else
				date.month = date.month + rm;
			date.year = date.year + y;

			if (date.year < d.year)
			{
				char aux[100] = "";
				elems[i].toString(aux);
				printf("%s", aux);
			}
			else if (date.year == d.year && date.month < d.month)
			{
				char aux[100] = "";
				elems[i].toString(aux);
				printf("%s", aux);
			}
			else if (date.year == d.year && date.month == d.month && date.day <= d.day)
			{
				char aux[100] = "";
				elems[i].toString(aux);
				printf("%s", aux);
			}
		}
	}
	else
	{
		for (i = 0;i <= length;i++)
		{
			if (strcmp(category, elems[i].getCategory()) == 0)
			{
				Date date = elems[i].getExpDate();
				int m = x / 30;
				int rd = x % 30;
				int y = m / 12;
				int rm = m % 12;

				if (date.day + rd > 30)
				{
					date.month++;
					date.day = (date.day + rd) % 30;
				}
				else
					date.day = date.day + rd;

				if (date.month + rm > 12)
				{
					date.year++;
					date.month = (date.month + rm) % 12;
				}
				else
					date.month = date.month + rm;
				date.year = date.year + y;

				if (date.year < d.year)
				{
					char aux[100] = "";
					elems[i].toString(aux);
					printf("%s", aux);
				}
				else if (date.year == d.year && date.month < d.month)
				{
					char aux[100] = "";
					elems[i].toString(aux);
					printf("%s", aux);
				}
				else if (date.year == d.year && date.month == d.month && date.day <= d.day)
				{
					char aux[100] = "";
					elems[i].toString(aux);
					printf("%s", aux);
				}
			}
		}
	}

}

int ProductRepo::getLength()
{
	return length;
}
Product* ProductRepo::getElems()
{
	return elems;
}
int ProductRepo::getCapacity()
{
	return capacity;
}