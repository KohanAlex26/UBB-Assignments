#include "domain.h"
using namespace std;



Question::Question():m_id{0},
    m_text{""},m_type{'a'},m_subject{""},m_imagePath{""},m_possibleAnswers{QVector<int>()},m_correctAnswers{QVector<int>()}
{}

Question::Question(qint8 newid, QString newtext, QString newtype, QString newsubject, QVector<int> newpossibleAnswers, QVector<int> newcorrectAnswers,QString imagePath):m_id{newid},
m_text{newtext},m_type{newtype},m_subject{newsubject},m_possibleAnswers{newpossibleAnswers},m_correctAnswers{newcorrectAnswers},m_imagePath{imagePath}
{}
Question::Question(const Question &other)
{
   m_id = other.m_id;
   m_text = other.m_text;
   m_type = other.m_type;
   m_subject = other.m_subject;
   m_possibleAnswers = other.m_possibleAnswers;
   m_correctAnswers=other.m_correctAnswers;
   m_imagePath=other.m_imagePath;
}

void Question::operator=(const Question &other)
{
   m_id = other.m_id;
   m_text = other.m_text;
   m_type = other.m_type;
   m_subject = other.m_subject;
   m_possibleAnswers = other.m_possibleAnswers;
   m_correctAnswers=other.m_correctAnswers;
m_imagePath=other.m_imagePath;
}

bool Question::operator==(const Question &other)
{
   return !(m_text.compare(other.m_text, Qt::CaseInsensitive));
}

bool Question::operator==(const QString &text)
{
   return !(m_text.compare(text, Qt::CaseInsensitive));
}

void Question::operator=(const QString &text)
{
   m_text=text;
}
QJsonObject Question::toJsonObject(){
   QJsonObject obj;
   obj["id"] = m_id;
   obj["text"] = m_text;
   obj["type"] = m_type;
   obj["subject"]=m_subject;
   QString list;
   QString list1;
   qint8 i;
   for(i=0;i<=m_possibleAnswers.size();i++)
   list=list+QString::number(m_possibleAnswers[i])+" ";
   for(i=0;i<=m_correctAnswers.size();i++)
   list1=list1+QString::number(m_correctAnswers[i])+" ";
   obj["possibleAnswers"] = list;
   obj["correctAnswers"] = list1;
   return obj;
}

QString Question::toString()
{
   QString str = QString::number(m_id)+","+m_type+","+m_subject+"\n";
   for(int i=0;i<=m_possibleAnswers.size();i++)
   str=str+QString::number(m_possibleAnswers[i])+" ";
   str=str+"\n";

   for(int i=0;i<=m_correctAnswers.size();i++)
   str=str+QString::number(m_correctAnswers[i])+" ";
   return str;
}

qint8 Question::id() const
{
   return m_id;
}

void Question::setId(const qint8 &id)
{
   m_id=id;
}

QString Question::text() const
{
   return m_text;
}

void Question::setText(const QString &text)
{
   m_text = text;
}

QString Question::type() const
{
   return m_type;
}

void Question::setType(const QString &type)
{
   m_type = type;
}

QString Question::subject() const
{
   return m_subject;
}

void Question::setSubject(const QString &subject)
{
   m_subject = subject;
}

QVector<int> Question::possibleAnswers() const
{
    return m_possibleAnswers;
}
void Question::setPossibleAnswers(const QVector<int> &possibleAnswers)
{
    m_possibleAnswers=possibleAnswers;
}

QVector<int> Question::correctAnswers() const
{
    return m_correctAnswers;
}
void Question::setCorrectAnswers(const QVector<int> &correctAnswers)
{
    m_correctAnswers=correctAnswers;
}

QString Question::imagePath() const
{
    return m_imagePath;
}

void Question::setImagePath(const QString &imagePath)
{
    m_imagePath = imagePath;
}
