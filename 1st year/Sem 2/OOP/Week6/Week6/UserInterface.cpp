#include "UserInterface.h"
#include "cstack.h"
#include <stdio.h>
#include <stdlib.h>
#include <cstring>

void printOptions() {
	printf("Please select an option: \n \t a\t - add a new product\n \t r\t - remove a product\n \t u\t - update a Product\n \t n\t - display by name\n \t c\t - display by category\n \t g\t - undo\n \t h\t - redo\n \t e\t - exit\n");
}

UI::UI(Controller* c)
{
	ctrl = c;
}
void UI::uiLoadData()
{
	ctrl->loadData();
}
void UI::uiAddNewProduct() {
	system("@cls||clear");
	char productName[30];
	char category[30];
	int quantity;
	Date date;

	int ok = false;
	printf("Insert new product\n");
	printf("\tProduct name: ");
	scanf("%*c%s", productName);
	printf("\tProduct category (dairy, sweets,meat,fruit): ");
	scanf("%s", &category);
	printf("\tQuantity: ");
	scanf("%d", &quantity);
	printf("\tDate: ");
	printf("\tDay: ");
	scanf("%d", &date.day);
	printf("\tMonth: ");
	scanf("%d", &date.month);
	printf("\tYear: ");
	scanf("%d", &date.year);


	ok = (*ctrl).addProduct(productName, category, quantity, date);

	if (ok == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}


void UI::uiRemoveProduct() {
	system("@cls||clear");
	char productName[30];
	printf("\tProduct name: ");
	scanf("%*c%s", productName);



	if ((*ctrl).removeProduct(productName) == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}

void UI:: uiUpdateProduct()
{
	system("@cls||clear");
	char productName[30];
	char category[30];
	int quantity;
	Date date;
	int index;
	printf("Insert new product\n");
	printf("\tNew product name: ");
	scanf("%*c%s", productName);
	printf("\tNew product category (dairy, sweets,meat,fruit): ");
	scanf("%s", &category);
	printf("\tNew quantity: ");
	scanf("%d", &quantity);
	printf("\tNew date: ");
	printf("\tNew day: ");
	scanf("%d", &date.day);
	printf("\tNew month: ");
	scanf("%d", &date.month);
	printf("\tNew year: ");
	scanf("%d", &date.year);
	printf("\tIndex: ");
	scanf("%d", &index);


	(*ctrl).updateProduct(productName, category, quantity, date, index);
}

void UI::uiDisplayByName()
{
	system("@cls||clear");
	char str[100];
	char c;
	scanf("%c", &c);
	printf("\tString: ");
	gets_s(str);
	(*ctrl).displayByNameProduct(str);
}

void UI::uiDisplayByCategory()
{
	system("@cls||clear");
	char category[100];
	int x;
	Date date;
	char c;
	scanf("%c", &c);
	printf("\tProduct category (dairy, sweets,meat,fruit): ");
	gets_s(category);
	printf("\tX days: ");
	scanf("%d", &x);
	printf("\tDate: ");
	printf("\tDay: ");
	scanf("%d", &date.day);
	printf("\tMonth: ");
	scanf("%d", &date.month);
	printf("\tYear: ");
	scanf("%d", &date.year);
	(*ctrl).displayByCategoryProduct(category, x, date);
}
void UI::uiUndo()
{
	ctrl->Undo();
}

void UI::uiRedo()
{
	ctrl->Redo();
}


void UI:: displayUI(Controller* repo) {
	char op;
	do {
		printOptions();
		scanf(" %c", &op);
		switch (op)
		{
		case 'a':
			uiAddNewProduct();
			break;
		case 'r':
			uiRemoveProduct();
			break;
		case 'u':
			uiUpdateProduct();
			break;
		case 'n':
			uiDisplayByName();
			break;
		case 'c':
			uiDisplayByCategory();
			break;
		case 'g':
			uiUndo();
			break;
		case 'h':
			uiRedo();
			break;
		case 'e':
			printf("Exit.\n");
			exit(0);
		default:
			printf("Unknown option %c \n", op);
			continue;
		}
	} while (true);
}


