#include "Product.h"
#include "ProductRepo.h"
#include "Controller.h"
#include "UserInterface.h"
#include "cstack.h"
#include <iostream>
#include <stdio.h>
using namespace std;

int main() {

	test_addProduct();
	test_removeProduct();
	test_updateProduct();

	ProductRepo repo(100);
	Controller ctrl(&repo);
	UI ui(&ctrl);
	ui.uiLoadData();
	ui.displayUI(&ctrl);

	return 0;
}