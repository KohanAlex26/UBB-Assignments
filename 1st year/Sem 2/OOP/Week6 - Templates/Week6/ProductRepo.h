#pragma once
#include "Product.h"
#include <stdlib.h>
#include <stdio.h>
#include <vector>

using std::vector;
template <class T>
class ProductRepo {
private:
	vector<T> elems;
public:
	ProductRepo() {};
	~ProductRepo() {};
	int getLength();
	int findPozProduct(T p);
	bool add(T p);
	bool remove(T p);
	bool update(T p, int index);
	void displayByName(char* str);
	void displayByCategory(char* category, int x, Date d);
	vector<T> getElems();
};