#ifndef DOMAIN_H
#define DOMAIN_H


#define _CRT_SECURE_NO_WARNINGS
#include <string.h>
#include <stdio.h>
typedef struct {
	int day;
	int month;
	int year;
}Date;


class Product
{
private:
	char name[100];
	char category[100];
	int quantity;
	Date expDate;

public:
	Product() = default;
	Product(char* nameProduct, char* categoryProduct, int quantityProduct, Date dProduct);
	void update(char* nameProduct, char* categoryProduct, int quantityProduct, Date dProduct);
	char* getName();
	char* getCategory();
	int getQuantity();
	Date getExpDate();
	void setName(char* newName);
	void setCategory(char* newCategory);
	void setQuantity(int newQuantity);
	void setExpDate(Date newDate);
	void toString(char str[]);

};

class AutomateRefill :public Product
{
private:
	char orderLink[100];
public:
	AutomateRefill(char* linkm, char* name, char* category, int quantity, Date d);
	void toStringAutomate();
};

class ManualRefill : public Product
{
private:
	char shopAddress[100];
public:
	ManualRefill(char* adress, char* name, char* category, int quantity, Date d);
	void toStringManual();
};
#endif // !DOMAIN_H