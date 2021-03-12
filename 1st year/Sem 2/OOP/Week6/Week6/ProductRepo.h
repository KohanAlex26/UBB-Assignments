#pragma once
#include "Product.h"
#include <stdlib.h>
#include <stdio.h>
#include <vector>

using std::vector;

class ProductRepo {
private:
	vector<Product> elems;
public:
	ProductRepo();
	~ProductRepo();
	int getLength();
	int findPozProduct(Product p);
	bool add(Product p);
	bool remove(Product p);
	bool update(Product p, int index);
	void displayByName(char* str);
	void displayByCategory(char* category, int x, Date d);
	vector<Product> getElems();
};