#include "Product.h"
#include "ProductRepo.h"
#include "Controller.h"
#include "UserInterface.h"
#include "cstack.h"
#include <iostream>
#include <stdio.h>
using namespace std;

//template class ProductRepo<Product>;
//template class Controller<Product>;

int main() {

	/*test_addProduct();
	test_removeProduct();
	test_updateProduct();*/

	ProductRepo<Product> repo;
	Product p((char*)"a",(char*)"dairy",100,{1,1,1});
	repo.add(p);
	/*Controller<Product> ctrl(&repo);
	UI<Product> ui(&ctrl);
	ui.uiLoadData();*/
	/*ui.displayUI(&ctrl);*/
	return 0;
}