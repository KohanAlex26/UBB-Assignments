#include "ProductRepo.h"
#include <stdlib.h>
#include <cstring>
#include <ctype.h>
#include <iostream>
using namespace std;

//template <class T>
//ProductRepo<T>::ProductRepo() {
//
//}
//
//template <class T>
//ProductRepo<T>::~ProductRepo() {
//
//}

template <class T>
vector<T> ProductRepo<T>:: getElems()
{
	return elems;
}

template <class T>
int ProductRepo<T>::getLength() {
	return elems.size();
}

template <class T>
int ProductRepo<T>::findPozProduct(T p) {
	for (int i = 0; i < elems.size(); i++) {
		if (strcmp(elems[i].getName(), p.getName()) == 0) {
			return i;
		}
	}
	return -1;
}

template <class T>
bool ProductRepo<T>::add(T p) {
	int poz = findPozProduct(p);
	if (poz == -1) {
		elems.push_back(p);
		return true;
	}
	else {
		elems[poz].setQuantity(elems[poz].getQuantity() + p.getQuantity());
		return true;
	}
}

template <class T>
bool ProductRepo<T>::remove(T p) {
	int poz = findPozProduct(p);
	if (poz != -1) {
		for (int i = poz; i < elems.size() - 1; i++) {
			elems[i] = elems[i + 1];
		}
		elems.pop_back();
		return true;
	}
	return false;
}


template <class T>
bool ProductRepo<T>::update(T p, int index)
{
	if (index<0 || index>elems.size())
		return false;
	elems[index] = p;
	return true;
}

int comparator(const void* a, const void* b) {
	int x = ((Product*)a)->getQuantity();
	int y = ((Product*)b)->getQuantity();
	return (x - y);
}

template <class T>
void ProductRepo<T>::displayByName(char* str)
{
	int i, j;
	char string[1000] = "";
	if (elems.size() == -1)
		printf("Empty repository\n\n");
	else if (strcmp((char*)"", str) == 0)
	{
		qsort(&elems[0], elems.size(), sizeof(Product), comparator);
		for (i = 0;i <= elems.size()-1;i++)
		{
			char aux[100] = "";
			elems[i].toString(aux);
			strcat(string, aux);
		}
		printf("%s", string);
	}
	else
	{
		qsort(&elems[0], elems.size(), sizeof(Product), comparator);
		for (i = 0;i <= elems.size()-1;i++)
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

template <class T>
void ProductRepo<T>::displayByCategory(char* category, int x, Date d)
{
	int i;
	char s[100] = "";
	if (strcmp(s, category) == 0)
	{
		for (i = 0;i <= elems.size()-1;i++)
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
		for (i = 0;i <= elems.size()-1;i++)
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
