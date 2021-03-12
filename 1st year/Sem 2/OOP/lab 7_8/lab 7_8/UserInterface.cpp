#include "UserInterface.h"
#include <stdio.h>
#include <stdlib.h>
#include <cstring>

void printOptionsTeacher() {
	printf("Please select an option: \n \t d\t - display the questions\n \t a\t - add a question\n \t r\t - remvove a question\n \t u\t - update at index\n");
}

void printOptionsStudent() {
	printf("Please select an option: \n \t d\t - display by subject\n \t e\t - exit\n");
}

UI::UI(Controller* c)
{
	ctrl = c;
}
void UI::uiLoadData()
{
	ctrl->loadData();
}
void UI::uiAddToElems() 
{
	system("@cls||clear");
	int id;
	char text[100];
	char type;
	char subject[20];
	int possibleAnswers[10];
	int correctAnswers[10];

	int ok = false;
	printf("Insert new Question\n");
	printf("\tProduct id: ");
	scanf("%*c%d", &id);
	printf("\tText: ");
	scanf("%s", &text);
	printf("\tType: ");
	scanf("%c", &type);
	printf("\tSubject: ");
	scanf("%s",&subject);
	int i;
	printf("\tPossibleAnswers ");
	for (i = 0;i <= 9;i++)
		scanf("%d", &possibleAnswers[i]);
	printf("\tCorrectAnswers ");
	for (i = 0;i <= 9;i++)
		scanf("%d", &correctAnswers[i]);

	ok = (*ctrl).addQuestionToElems(id,text,type,subject,possibleAnswers,correctAnswers);

	if (ok == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}


void UI::uiRemoveToElems() {
	system("@cls||clear");
	int id;
	printf("\tId: ");
	scanf("%*c%d", &id);



	if ((*ctrl).removeQuestionToElems(id) == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");

}

void UI::uiUpdateToElems()
{
	system("@cls||clear");
	system("@cls||clear");
	int id;
	char text[100];
	char type;
	char subject[20];
	int possibleAnswers[10];
	int correctAnswers[10];
	int index;

	int ok = false;
	printf("Insert new Question\n");
	printf("\tProduct id: ");
	scanf("%*c%d", &id);
	printf("\tText: ");
	scanf("%s", &text);
	printf("\tType: ");
	scanf("%c", &type);
	printf("\tSubject: ");
	scanf("%s", &subject);
	int i;
	printf("\tPossibleAnswers ");
	for (i = 0;i <= 9;i++)
		scanf("%d", &possibleAnswers[i]);
	printf("\tCorrectAnswers ");
	for (i = 0;i <= 9;i++)
		scanf("%d", &correctAnswers[i]);
	printf("\tIndex: ");
	scanf("%d", &index);
	ok = (*ctrl).updateQuestionToElems(id, text, type, subject, possibleAnswers, correctAnswers,index);

	if (ok == true)
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");
}

void UI::uiAddToReview(int id, char text[], char type, char subject[], int possibleAnswers[], int correctAnswers[])
{
	(*ctrl).addQuestionToReview(id, text, type, subject, possibleAnswers, correctAnswers);
}

void UI::uiRemoveToReview(int id)
{
	(*ctrl).removeQuestionToReview(id);
}

void UI::uidisplayBySubject()
{
	char subject[20];
	printf("\tSubject: ");
	scanf("%s", &subject);
	(*ctrl).displayBySubjectQuestion(subject);
}

void UI::uiDisplayElems()
{
	(*ctrl).displayQuestionElems();
}

void UI::uiDisplayReview()
{
	(*ctrl).displayQuestionReview();
}
void UI::displayUITeacher() {
	char op;
	do {
		printOptionsTeacher();
		scanf(" %c", &op);
		switch (op)
		{
		case 'd':
			uiDisplayElems();
			break;
		case 'a':
			uiAddToElems();
			break;
		case 'r':
			uiRemoveToElems();
			break;
		case 'u':
			uiUpdateToElems();
			break;
		case 'e':
			printf("Exit.\n");
			exit(0);
		default:
			printf("Unknown option %c \n", op);
			continue;
		}
	} while (true);
}

void UI::displayUIStudent() {
	int grade=0;
	char op;
	do {
		printOptionsStudent();
		scanf(" %c", &op);
		switch (op)
		{
		case 'd':
			uidisplayBySubject();
			int k;
			for (int i = 0;i < ctrl->repo->getLength() - 1;i++)
			{
				printf("Enter k(1 to answer and 2 to add it to Review List) \n)");
				scanf("%d", &k);
				if(k==1)
					uiAddToReview((*ctrl).repo->getElems()[i].getId(), (*ctrl).repo->getElems()[i].getText(), (*ctrl).repo->getElems()[i].getType(), (*ctrl).repo->getElems()[i].getSubject(), (*ctrl).repo->getElems()[i].getPossibleAnswers(), (*ctrl).repo->getElems()[i].getCorrectAnswers());
				else
				{
					int answer;
					printf("Answer: ");
					scanf("%d", &answer);
					for (int j = 0;j <= 9;j++)
						if (j == (*ctrl).repo->getElems()[i].getCorrectAnswers()[j])
							grade++;
					uiRemoveToReview((*ctrl).repo->getElems()[i].getId());
				}
			}
			break;
		//case 'a':
		//	uiAddToReview();
		//	break;
		//case 'u':
		//	uiUpdateProduct();
		//	break;
		//case 'n':
		//	uiDisplayByName();
		//	break;
		//case 'c':
		//	uiDisplayByCategory();
		//	break;
		case 'e':
			printf("$d", grade);
			printf(" out of ");
			printf("%d", ctrl->repo->getLength());
			printf("Exit.\n");
			exit(0);
		default:
			printf("Unknown option %c \n", op);
			continue;
		}
	} while (true);
}

