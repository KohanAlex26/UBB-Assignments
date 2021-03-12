
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "domain.h"
#include "Repository.h"
#include "Controller.h"
#include "UserInterface.h"
int main()
{
    /*int possibleAnswers[11] = { 1,2,3,4,5,6,7,8,9,0 };
    int correctAnswers[11] = { 4,5,6,5,6,7,8,9,0,1,2 };
    Question q(1, (char*)"cf", 'a', (char*)"a", possibleAnswers, correctAnswers);*/
    //q.toStringStudent();
    //q.toStringTeacher();

    QuestionRepo qr;
    Controller ctrl(&qr);
    UI ui(&ctrl);
    ui.uiLoadData();
    printf("Read 1 for student and any number for Teacher: ");
    int c;
    scanf(" %d", &c);
    if (c == 1)
        ui.displayUIStudent();
    else
        ui.displayUITeacher();

    /*q.toStringStudent();*/
}

