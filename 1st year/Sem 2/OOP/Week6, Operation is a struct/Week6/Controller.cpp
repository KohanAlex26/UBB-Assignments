#include "Controller.h"
#include "cstack.h"
#include "ProductRepo.h"
#include <iostream>
#include <string.h>
#include <assert.h>
#include <stdio.h>



Controller::Controller(ProductRepo* newRepo)
{
	repo = newRepo;
}

void Controller::update(ProductRepo* newRepo)
{
	repo = newRepo;
}

bool Controller::addProduct(char* planetName, char* category, int quantity, Date date)
{
	Product p(planetName, category, quantity, date);
	if (repo->add(&p) == 1)
	{
		Element o;
		o.type = OperationType::ADD;
		o.p = p;
		undo.push(o);
		/*cout << undo->getElements()[0].getProduct()->getQuantity()<<endl;*/
		return true;
	}
	return false;
}

void  test_addProduct()
{
	ProductRepo r(100);
	Controller c(&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"a", (char*)"dairy", 1, d);
	assert(c.repo->add(&p) == 1);

	//add a product with the same name as before so it will add the quantity
	Product p1 ((char*)"a", (char*)"dairy", 2, d);
	assert(c.repo->add(&p1) == 1);

	//verify if the quantity has added
	assert(c.repo->getElems()[c.repo->getLength()].getQuantity() == 3);

	assert(c.repo->getElems()[c.repo->getLength()].getExpDate().day == 1);

	assert(strcmp(c.repo->getElems()[c.repo->getLength()].getCategory(), "dairy") == 0);
}

bool Controller::removeProduct(char* planetName)
{
	char category[100] = "dairy";
	int quantity = 0;
	Date date;
	date.day = 0;
	date.month = 0;
	date.year = 0;
	Product p(planetName, (char*)"", 0, { 0,0,0 });
	if (repo->remove(&p) == 1)
	{
		Operation o;
		o.type= OperationType::REMOVE;
		o.p = p;
		undo.push(o);
		return true;
	}
	return false;
}

void test_removeProduct()
{
	ProductRepo r(100);
	Controller c=(&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"", (char*)"", 0, { 0,0,0 });
	//I try to remove an element which is not in the repo
	//cout << c.repo->remove(p);
	assert(c.repo->remove(&p) == 0);
	c.repo->add(&p);
	//I remove the added element
	assert(c.repo->remove(&p) == 1);

	assert(c.repo->getLength() == -1);

	assert(c.repo->getCapacity() == 100);

}
void Controller::loadData() {
	Product p1((char*)"Banana", (char*)"fruit", 4, {1,20,2020});
	repo->add(&p1);
	Product p2((char*)"Cake", (char*)"sweets", 2, {30,2,2019});
	repo->add(&p2);
	Product p3((char*)"Pork", (char*)"meat", 7, {25,3,2010});
	repo->add(&p3);
	Product p4((char*)"Milk", (char*)"dairy", 5, {14,4,2005});
	repo->add(&p4);
	Product p5((char*)"Cheese", (char*)"dairy", 6, {1,12,2005});
	repo->add(&p5);
}
bool Controller::updateProduct(char* name, char* category, int quantity, Date expDate, int index)
{

	Product p(name, category, quantity, expDate);
	if (repo->update(&p,index)== 1)
		return true;
	return false;
}

void test_updateProduct()
{
	ProductRepo r(100);
	Controller c = (&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"a", (char*)"dairy", 1, d);
	c.repo->add(&p);
	d.day = 2;
	d.month = 2;
	d.year = 2000;
	Product p1((char*)"b", (char*)"meat", 2, d);
	assert(c.repo->update(&p1,0) == 1);
	assert(strcmp(c.repo->getElems()[0].getName(),"b") == 0);
	assert(strcmp(c.repo->getElems()[0].getName(), "a") != 0);
}

void Controller::displayByNameProduct(char* str)
{
	repo->displayByName(str);
}


void Controller::displayByCategoryProduct(char* category, int x, Date d)
{
	repo->displayByCategory(category, x, d);
}

void Controller::Undo()
{
	Element e;
	e = undo.pop();
	if (e.type == OperationType::ADD)
		repo->remove(&e.p);
	else if (e.type== OperationType::REMOVE)
		repo->add(&e.p);
	redo.push(e);
}

void Controller::Redo()
{
	Element e;
	e = redo.pop();
	if (e.type == OperationType::ADD)
		repo->add(&e.p);
	else if (e.type == OperationType::REMOVE)
		repo->remove(&e.p);
	undo.push(e);
}