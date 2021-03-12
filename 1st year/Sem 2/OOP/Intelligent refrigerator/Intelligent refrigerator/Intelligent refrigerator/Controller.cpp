#include "Repository.h"
#include "Controller.h"
#include <stdlib.h>
#include <cstring>
Controller createController(ProductRepo* repo)
{
	Controller ctrl;
	ctrl.repo = repo;
	return ctrl;
}

bool addProduct(Controller* ctrl, char* planetName,char* category,int quantity,Date date)
{
	ProductRepo prCopy = *ctrl->repo;
	bool found = false;
	int i = 0;
	while (i <= ctrl->repo->length && found == false)
	{
		if (strcmp(getName(&ctrl->repo->elems[i]), planetName) == 0)
			found = true;
		else
			i++;
	}
	if (found == true)
	{
		ctrl->repo->elems[i].quantity = ctrl->repo->elems[i].quantity + quantity;
	}
	else
	{
		if (ctrl->repo->capacity == ctrl->repo->length)
		{
			ctrl->repo->elems = (Product*)realloc(ctrl->repo->elems,2 * sizeof(Product) * ctrl->repo->capacity);
			ctrl->repo->capacity = 2 * ctrl->repo->capacity;
			if (ctrl->repo->elems == nullptr)
				return false;
		}
		Product p;
		p = createProduct(planetName, category, quantity, date);
		ctrl->repo->elems[ctrl->repo->length + 1] = p;
		ctrl->repo->length = ctrl->repo->length + 1;
	}
	return true;
}

bool removeProduct(Controller* ctrl, char* planetName)
{
	ProductRepo prCopy = *ctrl->repo;
	int i = 0, j;
	bool found = false;
	while (i <= ctrl->repo->length and found == false)
	{
		if (strcmp(getName(&ctrl->repo->elems[i]), planetName) == 0)
			found = true;
		else
			i++;
	}
	if (found == false)
		return false;
	for (j = i;j <= ctrl->repo->length;j++)
		ctrl->repo->elems[j] = ctrl->repo->elems[j + 1];
	ctrl->repo->length = ctrl->repo->length - 1;
	return true;
}

void updateProduct(Controller* ctrl, char* name, char* category, int quantity, Date expDate, int index)
{
	ProductRepo prCopy = *ctrl->repo;
	strcpy(ctrl->repo->elems[index].name, name);
	strcpy(ctrl->repo->elems[index].category, category);
	ctrl->repo->elems[index].quantity = quantity;
	ctrl->repo->elems[index].expDate = expDate;
}