#ifndef DOMAIN_H
#define DOMAIN_H


#define _CRT_SECURE_NO_WARNINGS

typedef struct {
	int day;
	int month;
	int year;
}Date;


typedef struct {
	char name[100];
	char category[100];
	int quantity;
	Date expDate;

}Product;

Product createProduct(char name[], char category[], int quantity, Date expDate);

char* getName(Product* p);

char* getCategory(Product* p);

int getQuantity(Product* p);

Date getExpDate(Product* p);

void toString(Product p, char str[]);

#endif // !DOMAIN_H