#pragma once
#include "Controller.h"
class UI {
private:
	Controller* ctrl;
public:
	UI(Controller* c);
	void uiLoadData();
	void uiAddNewProduct();
	void uiRemoveProduct();
	void uiUpdateProduct();
	void uiDisplayByName();
	void uiDisplayByCategory();
	void uiUndo();
	void uiRedo();
	void displayUI(Controller* repo);
};