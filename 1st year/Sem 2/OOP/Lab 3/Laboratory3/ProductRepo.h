#pragma once
#include "Product.h"

typedef struct {
	Product* elems;
	int length;
	int capacity;
}ProductRepo;

ProductRepo createProductRepo(int capacity);

bool add(ProductRepo* repo, Product p);

bool remove(ProductRepo* repo, Product p);

bool update(ProductRepo* repo, Product p, int index);

void displayByName(ProductRepo* repo, char* str);

void displayByCategory(ProductRepo* repo, char* category, int x, Date d);