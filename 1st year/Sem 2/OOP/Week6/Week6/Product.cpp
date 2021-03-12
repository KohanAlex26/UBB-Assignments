#include "Product.h"
#include <string.h>
#include <stdio.h>

Product::Product(char* nameProduct, char* categoryProduct, int quantityProduct, Date dProduct)
{
	strcpy(name,nameProduct);
	strcpy(category, categoryProduct);
	quantity = quantityProduct;
	expDate = dProduct;
}
void Product::update(char* nameProduct, char* categoryProduct, int quantityProduct, Date dProduct)
{
	strcpy(name, nameProduct);
	strcpy(category, categoryProduct);
	quantity = quantityProduct;
	expDate.day = dProduct.day;
	expDate.month = dProduct.month;
	expDate.year = dProduct.year;

}

char* Product:: getName()
{
	return name;
}

char* Product:: getCategory()
{
	return category;
} 

int Product:: getQuantity()
{
	return quantity;
}

Date Product:: getExpDate()
{
	return expDate;
}

void Product::setName(char* newName)
{
	strcpy(name, newName);
}
void Product::setCategory(char* newCategory)
{
	strcpy(category, newCategory);
}
void Product::setQuantity(int newQuantity)
{
	quantity = newQuantity;
}
void Product::setExpDate(Date newDate)
{
	expDate = newDate;
}

void Product:: toString(char str[])
{
	sprintf(str, "Product %s of category %s has a quantity of %d and it expires in %d/%d/%d.\n", name, category, quantity, expDate.day, expDate.month, expDate.year);
}
AutomateRefill:: AutomateRefill(char* link,char* name,char* category,int quantity,Date d)
{
	strcpy(orderLink, link);
	setName(name);
	setCategory(category);
	setQuantity(quantity);
	setExpDate(d);
}
ManualRefill::ManualRefill(char* adress, char* name, char* category, int quantity, Date d)
{
	strcpy(shopAddress, adress);
	setName(name);
	setCategory(category);
	setQuantity(quantity);
	setExpDate(d);
}
void AutomateRefill::toStringAutomate()
{
	char aux[100] = "",aux2[100]="";
	toString(aux);
	sprintf(aux2, "and is from link: %s", orderLink);
	strcat(aux, aux2);
	printf("%s", aux);
}
void ManualRefill::toStringManual()
{
	char aux[100] = "", aux2[100] = "";
	toString(aux);
	sprintf(aux2, "and is from adress: %s", shopAddress);
	strcat(aux, aux2);
	printf("%s", aux);
}