#pragma once
#include "domain.h"
#include "Repository.h"
typedef struct {
	ProductRepo* repo;
}Controller;

Controller createController(ProductRepo* repo);

bool addProduct(Controller* ctrl, char* planetName, char* category, int quantity, Date date);

bool removeProduct(Controller* ctrl, char* planetName);

void updateProduct(Controller* ctrl, char* name, char* category, int quantity, Date expDate, int index);