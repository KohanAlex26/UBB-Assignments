#include "UserInterface.h"
#include <stdio.h>
#include <stdlib.h>
#include <cstring>

void printOptionsTeacher() {
	printf("Please select an option: \n \t 1\t - display the questions\n \t 2\t - add a question\n \t 3\t - remvove a question\n \t 4\t - update at index\n\t 5\t - swap to student\n\t 6\t - exit\n");
}

void printOptionsStudent() {
	printf("FIRST command should be display by subject(because you should choose a subject first)\n\n");
	printf("Please select an option: \n \t 1\t - display by subject\n\t 2\t - add to the review list(display next question first)\n \t 3\t - display next question\n\t 4\t - answer the selected question(display next question first)\n \t 5\t - swap to teacher\n\t 6\t - answer first question from review list(don't forget to delete the question after you answered)\n\t 7\t - remove first question from review  \n\t 8\t - display reviews  \n \t 9\t - exit\n");
}

UI::UI(Controller* c)
{
	ctrl = c;
	strcpy(sub, (char*)"");
	numberOfQuestions = 0;
	points = 0;
	k = -1;
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
	int possibleAnswers[10] = { 0 };
	int correctAnswers[10] = { 0 };

	int ok = false;
	printf("Insert new Question\n");
	printf("\tProduct id(integer): ");
	scanf("%*c%d", &id);
	printf("\tText(string): ");


	char c;
	scanf("%c", &c);
	gets_s(text);

	//scanf("%[^\n]%*c", text);
	//scanf("%s", &text);


	printf("\tType(character): ");
	scanf("%c", &type);
	printf("\tSubject(string): ");
	scanf("%s",&subject);
	printf("\tPossibleAnswers(Until you read number '0')(integers) ");
	int i = 0;
	int answer;
	scanf("%d", &answer);
	while (answer != 0)
	{
		possibleAnswers[i] = answer;
		scanf("%d", &answer);
		i++;
	}


	printf("\tCorrectAnswers(Until you read number '0')(integers) ");

	int j = 0;
	int correct;
	scanf("%d", &correct);
	while (correct != 0)
	{
		correctAnswers[j] = correct;
		scanf("%d", &correct);
		j++;
	}

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
	int id;
	char text[100];
	char type;
	char subject[20];
	int possibleAnswers[10] = { 0 };
	int correctAnswers[10] = { 0 };
	int index;

	int ok = false;
	printf("Insert new Question\n");
	printf("\tProduct id(integer): ");
	scanf("%*c%d", &id);
	printf("\tText(string): ");


	char c;
	scanf("%c", &c);
	gets_s(text);

	//scanf("%[^\n]%*c", text);
	//scanf("%s", &text);


	printf("\tType(character): ");
	scanf("%c", &type);
	printf("\tSubject(string): ");
	scanf("%s", &subject);
	printf("\tPossibleAnswers(Until you read number '0')(integers) ");
	int i = 0;
	int answer;
	scanf("%d", &answer);
	while (answer != 0)
	{
		possibleAnswers[i] = answer;
		scanf("%d", &answer);
		i++;
	}


	printf("\tCorrectAnswers(Until you read number '0')(integers) ");

	int j = 0;
	int correct;
	scanf("%d", &correct);
	while (correct != 0)
	{
		correctAnswers[j] = correct;
		scanf("%d", &correct);
		j++;
	}
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
	system("@cls||clear");
	if ((*ctrl).addQuestionToReview(id, text, type, subject, possibleAnswers, correctAnswers) == 1)
		printf("Good\n\n");
	else
		printf("Not good\n\n");
	/*printf((*ctrl).repo->getReview()[0].getSubject());*/
}

void UI::uiRemoveToReview(int id)
{
	/*system("@cls||clear");*/
	(*ctrl).removeQuestionToReview(id);
}

void UI::uidisplayBySubject()
{
	system("@cls||clear");
	char subject[20];
	char c;
	scanf("%c", &c);
	printf("\tSubject: ");
	gets_s(subject);
	strcpy(sub, subject);
	numberOfQuestions=(*ctrl).displayBySubjectQuestion(subject);
}

void UI::uiDisplayElems()
{
	system("@cls||clear");
	(*ctrl).displayQuestionElems();
}

void UI::uiDisplayReview()
{
	system("@cls||clear");
	(*ctrl).displayQuestionReview();
}

void UI::displayUI()
{
	int q = 0;
	printf("Read 1 for student and any number for Teacher: ");
	int k;
	scanf("%d", &k);
	if (k == 1)
		displayUIStudent(q);
	displayUITeacher(q);
}

void UI::answerElems()
{
	system("@cls||clear");
	int r, nr = 0, good = 0;
	printf("Read answers until you read 0: ");
	scanf("%d", &r);
	while (r != 0)
	{
		nr=nr+1;
		for (int i = 0;i <= 9;i++)
			if ((*ctrl).repo->getElems()[k].getCorrectAnswers()[i] == r)
				good=good+1;
		scanf("%d", &r);
	}
	points = points + (float)good / nr;
}
void UI::answerReview()
{
	system("@cls||clear");
	int r, nr = 0, good = 0;
	printf("Read answers until you read 0: ");
	scanf("%d", &r);
	while (r != 0)
	{
		nr = nr + 1;
		for (int i = 0;i <= 9;i++)
			if ((*ctrl).repo->getReview()[0].getCorrectAnswers()[i] == r)
				good = good + 1;
		scanf("%d", &r);
	}
	points = points + (float)good / nr;
}

void UI::displayUITeacher(int q) {
	
	char op;
		do {
			printOptionsTeacher();
			scanf(" %c", &op);
			switch (op)
			{
			case '1':
				uiDisplayElems();
				break;
			case '2':
				uiAddToElems();
				break;
			case '3':
				uiRemoveToElems();
				break;
			case '4':
				uiUpdateToElems();
				break;
			case '5':
				displayUIStudent(q);
				break;
			case '6':
				printf("Exit.\n");
				exit(0);
			default:
				printf("Unknown option %c \n", op);
				continue;
			}
		} while (true);
}

void UI::displayUIStudent(int q) {
	int grade=0;
	char op;
	do {
		printOptionsStudent();
		scanf(" %c", &op);
		switch (op)
		{
		case '1':
			uidisplayBySubject();
			break;
		case '2':
			uiAddToReview((*ctrl).repo->getElems()[k].getId(), (*ctrl).repo->getElems()[k].getText(), (*ctrl).repo->getElems()[k].getType(), (*ctrl).repo->getElems()[k].getSubject(), (*ctrl).repo->getElems()[k].getPossibleAnswers(), (*ctrl).repo->getElems()[k].getCorrectAnswers());
			break;
		case '3':
			system("@cls||clear");
			k++;
			if (k == ctrl->repo->getLength())
				k = 0;
			if (strcmp(sub, (char*)"") != 0)
			{
				while (k <= ctrl->repo->getLength() - 1 and strcmp((*ctrl).repo->getElems()[k].getSubject(), sub) != 0)
				{
					k++;
					/*printf("%d", strcmp((*ctrl).repo->getElems()[k].getSubject(), sub));*/

				}
				/*printf("1");*/
				if (k == ctrl->repo->getLength())
				{
					k = -1;
					while (k <= ctrl->repo->getLength() - 1 and strcmp((*ctrl).repo->getElems()[k].getSubject(), sub) != 0)
						k++;
				}
			}
			(*ctrl).repo->getElems()[k].toStringStudent();
		break;
		case '4':
			answerElems();
			break;
		case '5':
			displayUITeacher(q);
			break;
		case '6':
			answerReview();
			break;
		case '7':
			uiRemoveToReview((*ctrl).repo->getReview()[0].getId());
			break;
		case '8':
			uiDisplayReview();
			break;
		case '9':
			printf("%.2f", (float)points);
			printf(" out of ");
			printf("%d\n", numberOfQuestions);
			printf("Exit.\n");
			exit(0);
		default:
			printf("Unknown option %c \n", op);
			continue;
		}
	} while (true);
}

