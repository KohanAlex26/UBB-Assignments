#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "domain.h"
#include <vector>
#include <iostream>
#include <QVector>

class Controller {
private:
    QVector<Question> m_repo;
public:
    Controller();
    ~Controller();

    bool dumpToJson(QString jsonPath);

    bool addQuestion(Question q);
    bool removeQuestion(Question q);
    bool updateQuestion(int index,Question q);

    bool saveToFile(QString filepath);

//    QStringList getQuestionAnswers();

    const QVector<Question> &repo();
};

#endif // CONTROLLER_H
