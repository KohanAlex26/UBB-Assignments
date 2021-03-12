#include "ProductRepo.h"
#include <stdlib.h>
#include <cstring>
#include <ctype.h>
#include <iostream>
using namespace std;
ProductRepo createProductRepo(int capacity) {
	ProductRepo r;
	r.length = -1;
	r.capacity = capacity;
	r.elems = (Product*)malloc(sizeof(Product) * r.capacity);
	return r;
}

bool add(ProductRepo* repo, Product p)
{
	bool found = false;
	int i = 0;
	while (i <= repo->length && found == false)
	{
		if (strcmp(getName(&repo->elems[i]), getName(&p)) == 0)
			found = true;
		else
			i++;
	}
	if (found == true)
	{
		repo->elems[i].quantity = repo->elems[i].quantity + getQuantity(&p);
	}
	else
	{
		if (repo->capacity == repo->length)
		{
			repo->elems = (Product*)realloc(repo->elems, 2 * sizeof(Product) * repo->capacity);
			repo->capacity = 2 * repo->capacity;
			if (repo->elems == nullptr)
				return false;
		}
		repo->elems[repo->length + 1] = p;
		repo->length = repo->length + 1;
	}
	return true;
}

bool remove(ProductRepo* repo, Product p)
{
	int i = 0, j;
	bool found = false;
	while (i <= repo->length and found == false)
	{
		if (strcmp(getName(&repo->elems[i]), getName(&p)) == 0)
			found = true;
		else
			i++;
	}
	if (found == false)
		return false;
	for (j = i;j <= repo->length;j++)
		repo->elems[j] = repo->elems[j + 1];
	repo->length = repo->length - 1;
	return true;
}

bool update(ProductRepo* repo, Product p,  int index)
{
	if (index<0 || index>repo->length)
		return false;
	strcpy(repo->elems[index].name, p.name);
	strcpy(repo->elems[index].category, p.category);
	repo->elems[index].quantity = p.quantity;
	repo->elems[index].expDate = p.expDate;
	return true;
}

void displayByName(ProductRepo* repo, char* str)
{
	int i, j;
	char string[1000]="";
	if (repo->length == -1)
		printf("Empty repository\n\n");
	else if (strcmp((char*)"", str) == 0)
	{
		for (i = 0;i <= repo->length - 1;i++)
			for (j = i + 1;j <= repo->length;j++)
				if (getQuantity(&repo->elems[i]) > getQuantity(&repo->elems[j]))
				{
					Product p;
					p = repo->elems[i];
					repo->elems[i] = repo->elems[j];
				}
		for (i = 0;i <= repo->length;i++)
		{
			char aux[100] = "";
			toString(repo->elems[i], aux);
			strcat(string, aux);
		}
		printf("%s", string);
	}
	else
	{
		for (i = 0;i <= repo->length - 1;i++)
			for (j = i + 1;j <= repo->length;j++)
				if (strstr(getName(&repo->elems[i]), str) != NULL && strstr(getName(&repo->elems[j]), str) != NULL && getQuantity(&repo->elems[i]) > getQuantity(&repo->elems[j]))
				{
					Product p;
					p = repo->elems[i];
					repo->elems[i] = repo->elems[j];
					repo->elems[j] = p;
				}
		for (i = 0;i <= repo->length;i++)
		{
			if (strstr(getName(&repo->elems[i]), str) != NULL)
			{
				char aux[100] = "";
				toString(repo->elems[i], aux);
				strcat(string, aux);
			}
		}
		printf("%s", string);
	}
}

void displayByCategory(ProductRepo* repo, char* category, int x, Date d)
{
	int i;
	char s[100] = "";
	if (strcmp(s, category) == 0)
	{
		for (i = 0;i <= repo->length;i++)
		{
			Date date = getExpDate(&repo->elems[i]);
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
				toString(repo->elems[i], aux);
				printf("%s", aux);
			}
			else if (date.year == d.year && date.month < d.month)
			{
				char aux[100] = "";
				toString(repo->elems[i], aux);
				printf("%s", aux);
			}
			else if (date.year == d.year && date.month == d.month && date.day <= d.day)
			{
				char aux[100] = "";
				toString(repo->elems[i], aux);
				printf("%s", aux);
			}
		}
	}
	else
	{
		for (i = 0;i <= repo->length;i++)
		{
			if (strcmp(category, getCategory(&repo->elems[i])) == 0)
			{
				Date date = getExpDate(&repo->elems[i]);
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
					toString(repo->elems[i], aux);
					printf("%s", aux);
				}
				else if (date.year == d.year && date.month < d.month)
				{
					char aux[100] = "";
					toString(repo->elems[i], aux);
					printf("%s", aux);
				}
				else if (date.year == d.year && date.month == d.month && date.day <= d.day)
				{
					char aux[100] = "";
					toString(repo->elems[i], aux);
					printf("%s", aux);
				}
			}
		}
	}

}
