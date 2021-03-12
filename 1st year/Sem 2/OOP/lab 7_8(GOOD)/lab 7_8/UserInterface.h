#pragma once
#include "Controller.h"
class UI {
private:
	Controller* ctrl;
public:
	float points;
	int k;
	char sub[20];
	int numberOfQuestions;

	UI(Controller* c);

	void uiLoadData();

	void uiAddToElems();

	void uiRemoveToElems();

	void uiUpdateToElems();

	void uiAddToReview(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[]);

	void uiRemoveToReview(int id);

	void uidisplayBySubject();

	void uiDisplayElems();

	void uiDisplayReview();

	void displayUI();

	void displayUITeacher(int q);

	void displayUIStudent(int q);

	void answerElems();

	void answerReview();
};