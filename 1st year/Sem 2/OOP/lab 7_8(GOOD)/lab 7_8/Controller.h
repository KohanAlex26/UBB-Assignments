#pragma once
#include "domain.h"
#include "Repository.h"

class Controller {
private:

public:
	QuestionRepo* repo;

	Controller(QuestionRepo* newRepo);

	void update(QuestionRepo* newRepo);

	bool addQuestionToElems(int id, char* text, char type, char* subject, int possibleAnswers[], int correctAnswers[]);

	bool removeQuestionToElems(int id);

	bool updateQuestionToElems(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[], int index);

	bool addQuestionToReview(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[]);

	bool removeQuestionToReview(int id);

	int displayBySubjectQuestion(char* subject);

	void displayQuestionElems();

	void displayQuestionReview();

	void loadData();

};

void test_addProduct();
void test_removeProduct();
void test_updateProduct();