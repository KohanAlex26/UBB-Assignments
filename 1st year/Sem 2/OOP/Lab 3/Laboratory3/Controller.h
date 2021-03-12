#pragma once
#include "Product.h"
#include "ProductRepo.h"
#include "cstack.h"
typedef struct {
	ProductRepo* repo;
}Controller;


Controller createController(ProductRepo* repo);

bool addProduct(Controller* ctrl, char* planetName, char* category, int quantity, Date date, Stack* undo);

void test_addProduct();

bool removeProduct(Controller* ctrl, char* planetName, Stack* undo);

void test_removeProduct();

bool updateProduct(Controller* ctrl, char* name, char* category, int quantity, Date expDate, int index);

void test_updateProduct();

void displayByNameProduct(Controller* ctrl, char* str);

void displayByCategoryProduct(Controller* ctrl, char* category, int x, Date d);