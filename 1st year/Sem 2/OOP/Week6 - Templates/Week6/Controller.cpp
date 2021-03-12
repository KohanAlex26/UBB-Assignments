#include "Controller.h"
#include "cstack.h"
#include "ProductRepo.h"
#include <iostream>
#include <string.h>
#include <assert.h>
#include <stdio.h>


//template <class T>
//Controller<T>::Controller(ProductRepo<T>* newRepo)
//{
//	repo = newRepo;
//}

template <class T>
void Controller<T>::update(ProductRepo<T>* newRepo)
{
	repo = newRepo;
}

template <class T>
bool Controller<T>::addProduct(char* planetName, char* category, int quantity, Date date)
{
	Product p(planetName, category, quantity, date);
	if (repo->add(p) == 1)
	{
		Element o;
		o.setType(ADD);
		o.setProduct(p);
		undo.push(o);
		return true;
	}
	return false;
}

template <class T>
void  test_addProduct()
{
	ProductRepo<T> r;
	Controller<T> c(&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"a", (char*)"dairy", 1, d);
	assert(c.repo->add(p) == 1);

	//add a product with the same name as before so it will add the quantity
	Product p1 ((char*)"a", (char*)"dairy", 2, d);
	assert(c.repo->add(p1) == 1);

	//verify if the quantity has added
	assert(c.repo->getElems()[c.repo->getElems().size()-1].getQuantity() == 3);

	assert(c.repo->getElems()[c.repo->getElems().size()-1].getExpDate().day == 1);

	assert(strcmp(c.repo->getElems()[c.repo->getElems().size() - 1].getCategory(), "dairy") == 0);
}

template <class T>
bool Controller<T>::removeProduct(char* planetName)
{
	char category[100] = "dairy";
	int quantity = 0;
	Date date;
	date.day = 0;
	date.month = 0;
	date.year = 0;
	Product p(planetName, (char*)"", 0, { 0,0,0 });
	if (repo->remove(p) == 1)
	{
		Operation o;
		o.setType(REMOVE);
		o.setProduct(p);
		undo.push(o);
		return true;
	}
	return false;
}

template <class T>
void test_removeProduct()
{
	ProductRepo<T> r;
	Controller<T> c=(&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"", (char*)"", 0, { 0,0,0 });
	//I try to remove an element which is not in the repo
	assert(c.repo->remove(p) == 0);
	c.repo->add(p);
	//I remove the added element
	assert(c.repo->remove(p) == 1);

	assert(c.repo->getElems().size() == 0);


}

template <class T>
void Controller<T>::loadData() {
	T p1((char*)"Banana", (char*)"fruit", 4, {1,20,2020});
	repo->add(p1);
	T p2((char*)"Cake", (char*)"sweets", 2, {30,2,2019});
	repo->add(p2);
	T p3((char*)"Pork", (char*)"meat", 7, {25,3,2010});
	repo->add(p3);
	T p4((char*)"Milk", (char*)"dairy", 5, {14,4,2005});
	repo->add(p4);
	T p5((char*)"Cheese", (char*)"dairy", 6, {1,12,2005});
	repo->add(p5);
}

template <class T>
bool Controller<T>::updateProduct(char* name, char* category, int quantity, Date expDate, int index)
{

	Product p(name, category, quantity, expDate);
	if (repo->update(p,index)== 1)
		return true;
	return false;
}

template <class T>
void test_updateProduct()
{
	ProductRepo r;
	Controller c = (&r);
	Date d;
	d.day = 1;
	d.month = 1;
	d.year = 2000;
	Product p((char*)"a", (char*)"dairy", 1, d);
	c.repo->add(p);
	d.day = 2;
	d.month = 2;
	d.year = 2000;
	Product p1((char*)"b", (char*)"meat", 2, d);
	assert(c.repo->update(p1,0) == 1);
	assert(strcmp(c.repo->getElems()[0].getName(),"b") == 0);
	assert(strcmp(c.repo->getElems()[0].getName(), "a") != 0);
}

template <class T>
void Controller<T>::displayByNameProduct(char* str)
{
	repo->displayByName(str);
}

template <class T>
void Controller<T>::displayByCategoryProduct(char* category, int x, Date d)
{
	repo->displayByCategory(category, x, d);
}

template <class T>
void Controller<T>::Undo()
{
	Element e;
	e = undo.pop();
	if (strcmp(e.getProduct().getName(), (char*)"") != 0)
	{
		if (e.getType() == ADD)
			repo->remove(e.getProduct());
		else if (e.getType() == REMOVE)
			repo->add(e.getProduct());
		redo.push(e);
	}
}

template <class T>
void Controller<T>::Redo()
{
	Element e;
	e = redo.pop();
	if (strcmp(e.getProduct().getName(), (char*)"") != 0)
	{
		if (e.getType() == ADD)
			repo->add(e.getProduct());
		else if (e.getType() == REMOVE)
			repo->remove(e.getProduct());
	}
	undo.push(e);
}