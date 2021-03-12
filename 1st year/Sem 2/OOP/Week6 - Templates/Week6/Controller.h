#pragma once
#include "Product.h"
#include "ProductRepo.h"
#include "cstack.h"
template <class T>
class Controller{
private:
	Stack undo;
	Stack redo;
public:
	ProductRepo<T>* repo;

	Controller(ProductRepo<T>* newRepo) :repo {newRepo}{
	};

	void update(ProductRepo<T>* newRepo);

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