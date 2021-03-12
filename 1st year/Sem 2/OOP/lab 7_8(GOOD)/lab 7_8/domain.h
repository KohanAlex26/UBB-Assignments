#ifndef DOMAIN_H
#define DOMAIN_H


#define _CRT_SECURE_NO_WARNINGS
#include <string.h>
#include <stdio.h>


class Question
{
private:
	int id;
	char text[100];
	char type;
	char subject[20];
	int possibleAnswers[10];
	int correctAnswers[10];

public:
	Question() = default;
	Question(int newid, char* newtext, char newtype, char* newsubject, int newpossibleAnswers[], int newcorrectAnswers[]);
	void update(int newid, char* newtext, char newtype, char* newsubject, int newpossibleAnswers[], int newcorrectAnswers[]);
	int getId();
	char* getText();
	char getType();
	char* getSubject();
	int* getPossibleAnswers();
	int* getCorrectAnswers();
	void setId(int newid);
	void setText(char* newtext);
	void setType(char newtype);
	void setSubject(char* newsubject);
	void setPossibleAnswers(int newpossibleAnswers[]);
	void setCorrectAnswers(int newcorrectAnswers[]);
	void toStringTeacher();
	void toStringStudent();
};

#endif // !DOMAIN_H