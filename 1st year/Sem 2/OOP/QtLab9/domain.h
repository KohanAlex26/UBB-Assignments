#pragma once
#include <string>
#include <vector>
#include <QtDebug>
#include <QtGlobal>
#include <QColor>
#include <QObject>
#include <QJsonArray>
#include <QJsonObject>
#include <QStringList>
#include <QString>

class Question:public QObject
{
private:
    qint8 m_id;
    QString m_text;
    QString m_type;
    QString m_subject;
    QVector<int> m_possibleAnswers;
    QVector<int> m_correctAnswers;
    QString m_imagePath;

public:
    Question();
    Question(qint8 newid, QString newtext, QString newtype, QString newsubject, QVector<int> newpossibleAnswers, QVector<int> newcorrectAnswers, QString imagePath);
    Question(const Question &other);

    void operator=(const Question &other);
    bool operator==(const Question &other);
    bool operator==(const QString &text);
    void operator=(const QString &text);

    QJsonObject toJsonObject();
    QString toString();

    qint8 id() const;
    void setId(const qint8 &id);

    QString text() const;
    void setText(const QString &text);

    QString type() const;
    void setType(const QString &type);

    QString subject() const;
    void setSubject(const QString &sbuject);

    QVector<int> possibleAnswers() const;
    void setPossibleAnswers(const QVector<int> &possibleAnswers);

    QVector<int> correctAnswers() const;
    void setCorrectAnswers(const QVector<int> &correctAnswers);

    QString imagePath() const;
    void setImagePath(const QString &imagePath);

    virtual void print()
    {
        qDebug() << "print base class" << endl;
    }

};
class QuestionWithASingleAnswer:public Question
{
    void print()
    {
        qDebug() << "print derived class" << endl;
    }
};
