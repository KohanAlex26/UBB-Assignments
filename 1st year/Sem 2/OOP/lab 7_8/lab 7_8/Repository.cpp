#include "Repository.h"
#include <stdlib.h>
#include <cstring>
#include <ctype.h>
#include <iostream>
using namespace std;

QuestionRepo::QuestionRepo() {

}

QuestionRepo::~QuestionRepo() {

}
vector<Question> QuestionRepo::getElems()
{
	return elems;
}
int QuestionRepo::getLength() {
	return elems.size();
}

vector<Question> QuestionRepo::getReview()
{
	return review;
}
int QuestionRepo::findPozProduct(Question p) {
	for (int i = 0; i < elems.size(); i++) {
		if (elems[i].getId()== p.getId()) {
			return i;
		}
	}
	return -1;
}
int QuestionRepo::findPozProductReview(Question p) {
	for (int i = 0; i < review.size(); i++) {
		if (review[i].getId() == p.getId()) {
			return i;
		}
	}
	return -1;
}
bool QuestionRepo::addToElems(Question p) {
	int poz = findPozProduct(p);
	if (poz == -1) {
		elems.push_back(p);
		return true;
	}
	else {
		/*elems[poz].setQuantity(elems[poz].getQuantity() + p.getQuantity());*/
		return false;
	}
}

bool QuestionRepo::removeToElems(Question p) {
	int poz = findPozProduct(p);
	if (poz != -1) {
		for (int i = poz; i < elems.size() - 1; i++) {
			elems[i] = elems[i + 1];
		}
		elems.pop_back();
		return true;
	}
	return false;
}

bool QuestionRepo::updateToElems(Question p, int index)
{
	if (index<0 || index>elems.size())
		return false;
	elems[index] = p;
	return true;
}

bool QuestionRepo::addToReview(Question p) {
	int poz = findPozProduct(p);
	if (poz == -1) {
		review.push_back(p);
		return true;
	}
	else {
		/*elems[poz].setQuantity(elems[poz].getQuantity() + p.getQuantity());*/
		return false;
	}
}

bool QuestionRepo::removeToReview(Question p) {
	int poz = findPozProductReview(p);
	if (poz != -1) {
		for (int i = poz; i < elems.size() - 1; i++) {
			review[i] = review[i + 1];
		}
		review.pop_back();
		return true;
	}
	return false;
}
void QuestionRepo::DisplayElems()
{
	for (int i = 0; i < elems.size(); i++)
		elems[i].toStringTeacher();
}

void QuestionRepo::DisplayReview()
{
	for (int i = 0; i < review.size(); i++)
		elems[i].toStringStudent();
}

void QuestionRepo::displayBySubject(char* subject)
{
	int i, j;
	if (elems.size() == -1)
		printf("Empty repository\n\n");
	else if (strcmp((char*)"", subject) == 0)
	{
		for (i = 0;i <= elems.size() - 1;i++)
		{
			elems[i].toStringStudent();
		}
	}
	else
	{
		for (i = 0;i <= elems.size() - 1;i++)
		{
			if (strcmp(elems[i].getSubject(), subject) != NULL)
			{
				elems[i].toStringStudent();
			}
		}
	}
}


