#include "Product.h"
#include <iostream>
#include <string.h>


Product createProduct(char name[], char category[], int quantity, Date expDate)
{
	Product p;
	strcpy(p.name, name);
	strcpy(p.category, category);
	p.quantity = quantity;
	p.expDate = expDate;
	return p;
}

char* getName(Product* p)
{
	return p->name;
}

char* getCategory(Product* p)
{
	return p->category;
}

int getQuantity(Product* p)
{
	return p->quantity;
}

Date getExpDate(Product* p)
{
	return p->expDate;
}

void toString(Product p, char str[])
{
	sprintf(str, "Product %s of category %s has a quantity of %d and it expires in %d/%d/%d.\n", p.name, p.category, p.quantity, p.expDate.day, p.expDate.month, p.expDate.year);
}
