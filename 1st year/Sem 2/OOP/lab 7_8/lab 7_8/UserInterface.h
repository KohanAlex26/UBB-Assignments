#pragma once
#include "Controller.h"
class UI {
private:
	Controller* ctrl;
public:
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

	void displayUITeacher();

	void displayUIStudent();
};