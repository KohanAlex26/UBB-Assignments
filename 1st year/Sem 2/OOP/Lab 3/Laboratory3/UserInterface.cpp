#include "UserInterface.h"
#include "cstack.h"
#include <stdio.h>
#include <stdlib.h>
#include <cstring>

void printOptions() {
	printf("Please select an option: \n \t a\t - add a new product\n \t r\t - remove a product\n \t u\t - update a Product\n \t n\t - display by name\n \t c\t - display by category\n \t g\t - undo\n \t h\t - redo\n \t e\t - exit\n");
}

void uiAddNewProduct(Controller* ctrl, Stack* undo) {
	/*system("@cls||clear");*/
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


	ok = addProduct(ctrl, productName, category, quantity, date, undo);

	if (ok == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}


void uiRemoveProduct(Controller* ctrl, Stack* undo) {
	system("@cls||clear");
	char productName[30];
	printf("\tProduct name: ");
	scanf("%*c%s", productName);



	if (removeProduct(ctrl, productName,undo) == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}

void uiUpdateProduct(Controller* ctrl)
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


	updateProduct(ctrl, productName, category, quantity, date, index);
}

void uiDisplayByName(Controller* ctrl)
{
	char str[100];
	char c;
	scanf("%c", &c);
	printf("\tString: ");
	gets_s(str);
	displayByNameProduct(ctrl, str);
}

void uiDisplayByCategory(Controller* ctrl)
{
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
	displayByCategoryProduct(ctrl, category, x, date);
}
void uiUndo(Controller* ctrl,Stack* undo,Stack* redo)
{
	Element e;
	e = pop(undo);
	if (e.type == ADD)
		remove(ctrl->repo, e.product);
	else if (e.type == REMOVE)
		add(ctrl->repo, e.product);
	push(redo,e);
}

void uiRedo(Controller* ctrl, Stack* undo, Stack* redo)
{
	Element e;
	e = pop(redo);
	if (e.type == ADD)
		add(ctrl->repo, e.product);
	else if (e.type == REMOVE)
		remove(ctrl->repo, e.product);
	push(undo, e);
}


void displayUI(Controller* ctrl, ProductRepo* repo, Stack* undo, Stack* redo) {
	char op;
	do {
		printOptions();
		scanf(" %c", &op);
		switch (op)
		{
		case 'a':
			uiAddNewProduct(ctrl,undo);
			break;
		case 'r':
			uiRemoveProduct(ctrl,undo);
			break;
		case 'u':
			uiUpdateProduct(ctrl);
			break;
		case 'n':
			uiDisplayByName(ctrl);
			break;
		case 'c':
			uiDisplayByCategory(ctrl);
			break;
		case 'g':
			uiUndo(ctrl,undo,redo);
			break;
		case 'h':
			uiRedo(ctrl, undo, redo);
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


