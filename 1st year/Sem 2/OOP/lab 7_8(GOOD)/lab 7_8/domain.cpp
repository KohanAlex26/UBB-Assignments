#include "domain.h"
#include <string.h>
#include <stdio.h>
#include <iostream>
Question::Question(int newid,char* newtext,char newtype, char* newsubject,int newpossibleAnswers[],int newcorrectAnswers[])
{
	int i;
	id = newid;
	strcpy(text, newtext);
	type = newtype;
	strcpy(subject, newsubject);
	for (i = 0;i <= 9;i++)
		possibleAnswers[i] = newpossibleAnswers[i];
	for (i = 0;i <= 9;i++)
		correctAnswers[i] = newcorrectAnswers[i];
}
void Question::update(int newid, char* newtext, char newtype, char* newsubject, int newpossibleAnswers[], int newcorrectAnswers[])
{
	int i;
	id = newid;
	strcpy(text, newtext);
	type = type;
	strcpy(subject, newsubject);
	for (i = 0;i <= 9;i++)
		possibleAnswers[i] = newpossibleAnswers[i];
	for (i = 0;i <= 9;i++)
		correctAnswers[i] = newcorrectAnswers[i];

}

int Question::getId()
{
	return id;
}

char* Question::getText()
{
	return text;
}

char Question::getType()
{
	return type;
}

char* Question::getSubject()
{
	return subject;
}

int* Question::getPossibleAnswers()
{
	return possibleAnswers;
}
int* Question::getCorrectAnswers()
{
	return correctAnswers;
}
void Question::setId(int newid)
{
	id = newid;
}
void Question::setText(char* newtext)
{
	strcpy(text, newtext);
}
void Question::setType(char newtype)
{
	type = newtype;
}
void Question::setSubject(char* newsubject)
{
	strcpy(subject, newsubject);
}
void Question::setPossibleAnswers(int newpossibleAnswers[])
{
	int i;
	for (i = 0;i <= 9;i++)
		possibleAnswers[i] = newpossibleAnswers[i];
	
}
void Question::setCorrectAnswers(int newcorrectAnswers[])
{
	int i;
	for (i = 0;i <= 9;i++)
		correctAnswers[i] = newcorrectAnswers[i];
}

void Question::toStringTeacher()
{
	int i;
	printf("Id: %d. \n", id);
	printf("Text: %s \n", text);
	printf("Type: %c\n", type);
	printf("Subject: %s\n", subject);
	printf("PossibleAnswers:");
	for (i = 0;i <= 9;i++)
		if (possibleAnswers[i] != 0)
		printf("%d ", possibleAnswers[i]);
	printf("\n");
	printf("CorrectAnswers:");
	for (i = 0;i <= 9;i++)
		if(correctAnswers[i]!=0)
		printf("%d ", correctAnswers[i]);
	printf("\n\n");
}

void Question::toStringStudent()
{
	int i;
	printf("%s\n", text);
	printf("PossibleAnswers:");
	for(i=0;i<=9;i++)
		if(possibleAnswers[i]!=0)
			printf("%d ", possibleAnswers[i]);
	/*printf("\n");
	printf("CorrectAnswers:");
	for (i = 0;i <= 9;i++)
		if (correctAnswers[i] != 0)
			printf("%d", correctAnswers[i]);*/
	printf("\n\n");
}