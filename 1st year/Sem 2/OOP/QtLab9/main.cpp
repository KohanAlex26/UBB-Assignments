#include <iostream>

#include <QJsonDocument>
#include <QApplication>
#include <QDebug>

#include "Gui.h"

int main(int argc, char *argv[])
{

    QApplication app(argc, argv);
    QuestionGUI QuestionUI;
    QuestionUI.show();
    return app.exec();
}
