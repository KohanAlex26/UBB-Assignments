#include "Product.h"
#include "ProductRepo.h"
#include "Controller.h"
#include "UserInterface.h"
#include "cstack.h"
#include <iostream>
using namespace std;

int main() {

	test_addProduct();
	test_removeProduct();
	test_updateProduct();

	Stack undo = createStack();
	Stack redo = createStack();
	ProductRepo repo;
	int repoSize = 100;
	repo = createProductRepo(repoSize);
	Controller ctrl;
	ctrl = createController(&repo);
	displayUI(&ctrl, &repo,&undo,&redo);

	return 0;
}