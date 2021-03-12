#include "Product.h"
#include "ProductRepo.h"
#include "Controller.h"
#include "UserInterface.h"
#include "cstack.h"
#include <iostream>
#include <stdio.h>
using namespace std;

int main() {
	//Product p((char*)"a", (char*)"dairy", 100, { 1,1,1 });
	//cout << (&p)->getName();
	//Stack st;
	//Operation e;
	////cout << e.getType()<<endl;
	//char aux[100] = "";
	//cout << *(e.getProduct())->getName();
	//cout << aux;
	/*char aux[100] = "";
	e.getProduct()->toString(aux);
	cout << aux;
	Product p((char*)"a", (char*)"dairy", 100, { 1,1,1 });*/
	/*e.update(ADD,&p);*/
	/*Product p((char*)"a", (char*)"dairy", 100, { 1,1,1 });
	Operation e;
	e.update(ADD,&p);*/
	/*ProductRepo repo(100);
	Controller ctrl(&repo);*/
	/*Product p;
	cout << p.getQuantity()<<endl;
	p.update((char*)"a", (char*)"dairy", 100, { 1,1,1 });
	char aux[100];
	p.toString(aux);
	cout << aux;*/
	/*ProductRepo r;*/
	//Stack undo;
	//Product p1((char*)"a", (char*)"dairy", 1, { 1,1,1 });
	//Product p2((char*)"b", (char*)"meat", 2, { 1,1,1 });
	//ProductRepo r(100);
	//Controller ctrl(&r);
	//ctrl.addProduct((char*)"a", (char*)"dairy", 1, { 1,1,1 },&undo);
	//ctrl.updateProduct((char*)"b", (char*)"meat", 2, { 1,1,1 }, 0);
	////ctrl.removeProduct((char*)"a", &undo);
	//std::cout<<ctrl.repo->getElems()[0].getName();
	/*r.add(&p1);
	r.displayByCategory((char*)"dairy", 5, {1,1,1 });*/
	//printf("%s %d %d %d %s %d\n", p.getCategory(), p.getExpDate().day, p.getExpDate().month, p.getExpDate().year, p.getName(), p.getQuantity());
	//char a[100] = "";
	//p.toString(a);
	////printf(a);
	//p.update((char*)"b", (char*)"da", 0, { 0,1,1 });
	//p.toString(a);
	//printf(a);
	//ProductRepo r(100);
	//r.add(&p1);
	/*cout <<endl<< r.getCapacity() << endl << r.getLength()<<endl;*/
//	cout << r.getElems()[0].getName();
	/*ProductRepo pr(100);
	Controller ctrl(&pr);
	Product p((char*)"a", (char*)"dairy", 100, { 1,1,1 });
	Stack undo;

	ctrl.addProduct((char*)"a", (char*)"dairy", 100, { 1,1,1 }, &undo);
	cout<<&undo.getElements()[0].getProduct()->getQuantity();*/
	/*Operation o;
	o.setType(ADD);
	o.setProduct(&p);
	char aux[100];*/
	/*Operation o;
	o.setType(ADD);
	o.setProduct(&p);
	undo.push(o);
	cout << undo.getElements()[0].getProduct()->getQuantity();*/

	/*ctrl.addProduct((char*)"a", (char*)"dairy", 100, { 1,1,1 }, &undo);
	cout << undo.getElements()[0].getProduct*/
	/*undo.getElements()[0].getProduct()->toString(aux);
	cout << aux;*/
	/*undo.Representation(undo.getElements()[0],aux);*/

	/*undo.pop();*/
	/*undo.push(o);
	Element e;
	undo.Representation(undo.getElements()[0], aux);
	cout << aux;
	e = undo.pop();*/

	/*undo.pop();
	undo.Representation(o, aux);
	cout << aux;*/

	/*test_addProduct();
	test_removeProduct();
	test_updateProduct();

	ProductRepo repo(100);
	Controller ctrl(&repo);
	UI ui(&ctrl);
	ui.uiLoadData();
	ui.displayUI(&ctrl);*/
	char c;
	strcpy(&c, 'a');
	return 0;
}