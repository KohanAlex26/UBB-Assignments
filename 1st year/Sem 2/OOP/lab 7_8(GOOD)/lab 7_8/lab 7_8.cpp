
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "domain.h"
#include "Repository.h"
#include "Controller.h"
#include "UserInterface.h"
int main()
{
    test_addProduct();
    test_removeProduct();
    test_updateProduct();
    QuestionRepo qr;
    Controller ctrl(&qr);
    UI ui(&ctrl);
    ui.uiLoadData();
    ui.displayUI();
}

