#pragma once
#include "Product.h"
#include "ProductRepo.h"
#include "cstack.h"
class Controller{
private:
	Stack undo;
	Stack redo;
public:
	ProductRepo* repo;

	Controller(ProductRepo* newRepo);

	void update(ProductRepo* newRepo);

	bool addProduct(char* planetName, char* category, int quantity, Date date);

	bool removeProduct(char* planetName);

	bool updateProduct(char* name, char* category, int quantity, Date expDate, int index);

	void displayByNameProduct(char* str);

	void displayByCategoryProduct(char* category, int x, Date d);

	void loadData();

	void Undo();

	void Redo();
};

void test_addProduct();
void test_removeProduct();
void test_updateProduct();