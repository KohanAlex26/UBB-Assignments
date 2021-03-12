#pragma once
#include "Product.h"
#include <stdlib.h>
#include <stdio.h>
#include <vector>

class ProductRepo {
private:
	/*vector<Product> elems;*/
	Product* elems;
	int length;
	int capacity;
public:
	ProductRepo(int capaciyRepo);
	bool add(Product* p);
	bool remove(Product* p);
	bool update(Product *p, int index);
	void displayByName(char* str);
	void displayByCategory(char* category, int x, Date d);
	int getLength();
	Product* getElems();
	int getCapacity();
};