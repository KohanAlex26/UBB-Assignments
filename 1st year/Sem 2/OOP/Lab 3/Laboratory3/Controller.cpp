#include "Controller.h"
#include "cstack.h"
#include "ProductRepo.h"
#include <iostream>
#include <string.h>
#include <assert.h>
#include <stdio.h>


Controller createController(ProductRepo* repo)
{
	Controller ctrl;
	ctrl.repo = repo;
	return ctrl;
}	


bool addProduct(Controller* ctrl, char* planetName, char* category, int quantity, Date date, Stack* undo)
{
		Product p;
		p = createProduct(planetName, category, quantity, date);
		if (add(ctrl->repo, p) == 1)
		{
			Operation o;
			o.type = ADD;
			o.product = p;
			push(undo, o);
			return true;
		}
		return false;
}

void test_addProduct()
{
	ProductRepo* r=&createProductRepo(100);
	Controller* c;
	c = &createController(r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p = createProduct((char*)"a", (char*)"dairy", 1, d);
	assert(add(c->repo, p)==1);

	//add a product with the same name as before so it will add the quantity
	Product p1 = createProduct((char*)"a", (char*)"dairy", 2, d);
	assert(add(c->repo, p1) == 1);

	//verify if the quantity has added
	assert(c->repo->elems[c->repo->length].quantity==3);

	assert(c->repo->elems[c->repo->length].expDate.day==1);
	
	assert(strcmp(c->repo->elems[c->repo->length].category, "dairy") == 0);
}

bool removeProduct(Controller* ctrl, char* planetName, Stack* undo)
{
	char category[100]="dairy";
	int quantity = 0;
	Date date;
	date.day = 0;
	date.month = 0;
	date.year = 0;
	Product p;
	p = createProduct(planetName, category, quantity, date);
	if (remove(ctrl->repo, p) == 1)
	{
		Operation o;
		o.type = REMOVE;
		o.product = p;
		push(undo, o);
		return true;
	}
	return false;
}

void test_removeProduct()
{
	ProductRepo* r = &createProductRepo(100);
	Controller* c;
	c = &createController(r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p = createProduct((char*)"a", (char*)"dairy", 1, d);
	//I try to remove an element which is not in the repo
	assert(remove(c->repo, p) == 0);
	add(c->repo, p);
	//I remove the added element
	assert(remove(c->repo, p) == 1);

	assert(c->repo->length==-1);

	assert(c->repo->capacity == 100);

}
bool updateProduct(Controller* ctrl, char* name, char* category, int quantity, Date expDate, int index)
{
	Product p;
	p = createProduct(name, category, quantity, expDate);
	if (update(ctrl->repo, p, index) == 1)
		return true;
	return false;
}

void test_updateProduct()
{
	ProductRepo* r = &createProductRepo(100);
	Controller* c;
	c = &createController(r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p = createProduct((char*)"a", (char*)"dairy", 1, d);
	add(c->repo, p);
	d.day = 2;
	d.month = 2;
	d.year = 2000;
	Product p1=createProduct((char*)"b", (char*)"meat", 2, d);
	assert(update(c->repo, p1, 0)==1);
	assert(strcmp(c->repo->elems[0].name, "b") == 0);
	assert(strcmp(c->repo->elems[0].name, "a") != 0);
}

void displayByNameProduct(Controller* ctrl, char* str)
{
	displayByName(ctrl->repo, str);
}


void displayByCategoryProduct(Controller* ctrl, char* category, int x, Date d)
{
	displayByCategory(ctrl->repo, category, x, d);
}
