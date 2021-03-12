#include "Controller.h"
#include "Repository.h"
#include <iostream>
#include <string.h>
#include <assert.h>
#include <stdio.h>



Controller::Controller(QuestionRepo* newRepo)
{
	repo = newRepo;
}

void Controller::update(QuestionRepo* newRepo)
{
	repo = newRepo;
}

bool Controller::addQuestionToElems(int id, char* text, char type, char* subject, int possibleAnswers[], int correctAnswers[])
{
	Question p(id, text, type, subject, possibleAnswers, correctAnswers);
	if (repo->addToElems(p) == 1)
		return true;
	return false;
}

//void  test_addProduct()
//{
//	ProductRepo r;
//	Controller c(&r);
//	Date d;
//	d.day = 1;
//	d.month = 1;
//	d.year = 2000;
//	Product p((char*)"a", (char*)"dairy", 1, d);
//	assert(c.repo->add(p) == 1);
//
//	//add a product with the same name as before so it will add the quantity
//	Product p1((char*)"a", (char*)"dairy", 2, d);
//	assert(c.repo->add(p1) == 1);
//
//	//verify if the quantity has added
//	assert(c.repo->getElems()[c.repo->getElems().size() - 1].getQuantity() == 3);
//
//	assert(c.repo->getElems()[c.repo->getElems().size() - 1].getExpDate().day == 1);
//
//	assert(strcmp(c.repo->getElems()[c.repo->getElems().size() - 1].getCategory(), "dairy") == 0);
//}

bool Controller::removeQuestionToElems(int id)
{
	char text[10] = "a";
	char type = 'a';
	char subject[10] = "a";
	int possibleAnswers[10];
	int correctAnswers[10];
	Question p(id, text, type, subject, possibleAnswers, correctAnswers);
	if (repo->removeToElems(p) == 1)
		return true;
	return false;
}

//void test_removeProduct()
//{
//	ProductRepo r;
//	Controller c = (&r);
//	Date d;
//	d.day = 1;
//	d.month = 1;
//	d.year = 2000;
//	Product p((char*)"", (char*)"", 0, { 0,0,0 });
//	//I try to remove an element which is not in the repo
//	assert(c.repo->remove(p) == 0);
//	c.repo->add(p);
//	//I remove the added element
//	assert(c.repo->remove(p) == 1);
//
//	assert(c.repo->getElems().size() == 0);
//
//
//}
void Controller::loadData() {
	int possibleAnswers[10] = { 1,2,3 };
	int correctAnswers[10] = { 2 };
	Question p1(1, (char*)"Is it ok?", 'a', (char*)"maths", possibleAnswers, correctAnswers);
	repo->addToElems(p1);
	int possibleAnswers1[10] = { 1,2,3,4 };
	int correctAnswers1[10] = { 1,2 };
	Question p2(2,(char*)"Is it like this?",'b', (char*)"science", possibleAnswers1, correctAnswers1);
	repo->addToElems(p2);
	int possibleAnswers2[10] = { 1,2,3,4,5,6,7 };
	int correctAnswers2[10] = {1,7 };
	Question p3(3,(char*)"Is this the correct way?",'c', (char*)"it", possibleAnswers2, correctAnswers2);
	repo->addToElems(p3);
	int possibleAnswers3[10] = { 1,2,3,4,5,6,7,8 };
	int correctAnswers3[10] = { 3,4 };
	Question p4(4,(char*)"Is this the correct answer?",'d', (char*)"sports", possibleAnswers3, correctAnswers3);
	repo->addToElems(p4);
	int possibleAnswers4[10] = { 1,2,3,4,5 };
	int correctAnswers4[10] = { 4 };
	Question p5(5,(char*)"What is this?",'e', (char*)"english", possibleAnswers4, correctAnswers4);
	repo->addToElems(p5);
}
bool Controller::updateQuestionToElems(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[], int index)
{

	Question p(id, text, type, subject, possibleAnswers, correctAnswers);
	if (repo->updateToElems(p, index) == 1)
		return true;
	return false;
}

bool Controller::addQuestionToReview(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[])
{
	Question p(id, text, type, subject, possibleAnswers, correctAnswers);
	if (repo->addToReview(p) == 1)
		return true;
	return false;
}

bool Controller::removeQuestionToReview(int id)
{
	char text[10] = "a";
	char type = 'a';
	char subject[10] = "a";
	int possibleAnswers[10];
	int correctAnswers[10];
	Question p(id, text, type, subject, possibleAnswers, correctAnswers);
	if (repo->removeToReview(p) == 1)
		return true;
	return false;
}

//void test_updateProduct()
//{
//	ProductRepo r;
//	Controller c = (&r);
//	Date d;
//	d.day = 1;
//	d.month = 1;
//	d.year = 2000;
//	Product p((char*)"a", (char*)"dairy", 1, d);
//	c.repo->add(p);
//	d.day = 2;
//	d.month = 2;
//	d.year = 2000;
//	Product p1((char*)"b", (char*)"meat", 2, d);
//	assert(c.repo->update(p1, 0) == 1);
//	assert(strcmp(c.repo->getElems()[0].getName(), "b") == 0);
//	assert(strcmp(c.repo->getElems()[0].getName(), "a") != 0);
//}

void Controller::displayBySubjectQuestion(char* subject)
{
	repo->displayBySubject(subject);
}

void Controller::displayQuestionElems()
{
	repo->DisplayElems();
}

void Controller::displayQuestionReview()
{
	repo->DisplayReview();
}
