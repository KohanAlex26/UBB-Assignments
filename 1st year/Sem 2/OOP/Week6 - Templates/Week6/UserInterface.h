#pragma once
#include "Controller.h"
template <class T>
class UI {
private:
	Controller<T>* ctrl;
public:
	UI(Controller<T>* c) :ctrl{ c } {
	};
	void uiLoadData();
	void uiAddNewProduct();
	void uiRemoveProduct();
	void uiUpdateProduct();
	void uiDisplayByName();
	void uiDisplayByCategory();
	void uiUndo();
	void uiRedo();
	void displayUI(Controller<T>* repo);
};