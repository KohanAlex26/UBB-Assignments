#pragma once
#include "domain.h"

typedef struct{
	Product* elems;
	int length;
	int capacity;
}ProductRepo;

ProductRepo createProductRepo(int capacity);

bool add(ProductRepo* repo, Product p);

bool remove(ProductRepo* repo, Product p);

void update(Product* repo, char* name, char* category, int quantity, Date expDate, int index);

void displayByName(ProductRepo* repo, char* str);

void displayByCategory(ProductRepo* repo, char* category, int x, Date d);	