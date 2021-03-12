#pragma once
#include "domain.h"
#include <stdlib.h>
#include <stdio.h>
#include <vector>

using std::vector;

class QuestionRepo {
private:
	vector<Question> elems;
	vector<Question> review;
public:
	QuestionRepo();
	~QuestionRepo();
	vector<Question> getElems();
	vector<Question> getReview();
	int getLength();
	int findPozProduct(Question p);
	bool addToElems(Question p);
	bool removeToElems(Question p);
	bool updateToElems(Question p, int index);
	int findPozProductReview(Question p);
	bool addToReview(Question p);
	bool removeToReview(Question p);
	void DisplayElems();
	void DisplayReview();
	int displayBySubject(char* subject);
};