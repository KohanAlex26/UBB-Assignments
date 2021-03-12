#include "controller.h"
#include "persistenceengine.h"

#include <QString>
#include <QJsonDocument>
#include <QtDebug>
#include <vector>
#include <algorithm>
using namespace std;

Controller::Controller()
{
    // add some data
    QVector<int> possibleAnswers;
    possibleAnswers<<1<<2<<3<<4;
    QVector<int> correctAnswers;
    correctAnswers<<1;
    Question q{0, "What is this?", "a","maths",correctAnswers,correctAnswers,""};

    QVector<int> possibleAnswers2;
    possibleAnswers2<<1<<2<<3<<4<<5;
    QVector<int> correctAnswers2;
    correctAnswers2<<1<<5;
    Question q2{1, "What is that?", "b","maths",correctAnswers,correctAnswers,""};

    QVector<int> possibleAnswers3;
    possibleAnswers3<<1<<2<<3;
    QVector<int> correctAnswers3;
    correctAnswers3<<2<<3;
    Question q3{2, "Is it good?", "c","maths",correctAnswers,correctAnswers,""};

    QVector<int> possibleAnswers4;
    possibleAnswers4<<1<<2;
    QVector<int> correctAnswers4;
    correctAnswers4<<2;
    Question q4{3, "Is this like this?", "d","maths",correctAnswers,correctAnswers,""};


    m_repo<<q<<q2<<q3<<q4;

}

Controller::~Controller()
{}

bool Controller::addQuestion(Question pl)
{
    std::vector<Question> Questions = m_repo.toStdVector();
    if (std::find(Questions.begin(), Questions.end(), pl) != Questions.end())
        return false;
    m_repo.append(pl);
    return true;
}

bool Controller::removeQuestion(Question q)
{
    std::vector<Question> questions = m_repo.toStdVector();
    auto it = std::find(questions.begin(), questions.end(), q);


    if (it == questions.end())
    return false;

    int index = it - questions.begin();
    qDebug()<<index;
    m_repo.remove(index);
    return true;
}
bool Controller::updateQuestion(int index,Question q)
{
//    std::vector<Question> questions = m_repo.toStdVector();
//    auto it = std::find(questions.begin(), questions.end(), q);


//    if (it == questions.end())
//    return false;

//    int index = it - questions.begin();
    qDebug()<<index;
    m_repo[index].setId(q.id());
    m_repo[index].setText(q.text());
    m_repo[index].setType(q.type());
    return true;
}
bool Controller::saveToFile(QString filepath)
{
    PersistenceEngine<Question>* persistenceEngine = nullptr;
    if(filepath.endsWith(".json"))
        persistenceEngine = new PersistenceEngineJSON<Question>(m_repo);
    if(filepath.endsWith(".csv"))
        persistenceEngine = new PersistenceEngineCSV<Question>(m_repo);
    // @todo other types of actual persistence objects (csv, xml etc)
    if(!persistenceEngine)
        return false;

    return persistenceEngine->save(filepath);
}

//QStringList QuestionsController::getQuestionFamilies()
//{
//    /// @todo
//    return QStringList{};
//}

const QVector<Question> &Controller::repo()
{
    return m_repo;
}
