#ifndef PERSISTENCEENGINE_H
#define PERSISTENCEENGINE_H

#include <QFile>

#include <QString>
#include <QVector>
#include <QJsonArray>
#include <QJsonObject>
#include <QJsonDocument>

template<typename T>
class PersistenceEngine
{
public:
    PersistenceEngine(QVector<T> &array):m_array{array}{}
    virtual bool save(const QString &filepath) = 0;
protected:
    QVector<T> &m_array;
};

template<typename T>
class PersistenceEngineJSON: public PersistenceEngine<T>{

public:
    PersistenceEngineJSON(QVector<T> &array):PersistenceEngine<T>{array}{}

    bool save(const QString &filepath) override{
        QJsonObject obj;
        QJsonArray elemsJson;
        for(T elem: m_array){
            elemsJson.append(elem.toJsonObject());
        }
        obj["elements"] = elemsJson;

        QFile file{filepath};

        QJsonDocument jsonDoc{obj};
        bool opened = file.open(QFile::WriteOnly | QFile::Text);
        if(!opened)
            return false;
        file.write(jsonDoc.toJson());
        file.close();

        return true;
    }
};

template<typename T>
class PersistenceEngineCSV: public PersistenceEngine<T>{

public:
    PersistenceEngineCSV(QVector<T> &array):PersistenceEngine<T>{array}{}

    bool save(const QString &filepath) override{
        QFile file{filepath};
        bool opened = file.open(QFile::WriteOnly | QFile::Text);
        if(!opened)
            return false;
        for(T elem: m_array){
            file.write(elem.toString().toUtf8());
        }

        file.close();
        return true;
    }
};




#endif // PERSISTENCEENGINE_H
