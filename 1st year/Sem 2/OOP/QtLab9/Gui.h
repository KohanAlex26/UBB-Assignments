#ifndef GUI_H
#define GUI_H
#include <QLineEdit>
#include <QMainWindow>
#include <QObject>
#include <QLineEdit>
#include <QListWidget>
#include <QTableWidget>
#include <QStack>
#include "domain.h"
#include <QKeyEvent>
#include <stack>

typedef struct {
    Question q;
    int i;
}Element;

class Controller;
class QuestionGUI : public QMainWindow
{

    Q_OBJECT
public:
    explicit QuestionGUI(QWidget *parent = nullptr);

signals:
void keyCaught(QKeyEvent *e);

public slots:
    void onRemoveQuestionPressed();
    void onAddQuestionPressed();
    void onUpdateQuestionPressed();
    void showDialog();
    void repoPopulate();
//    bool undoOperation();
//    bool redoOperation();
    void undo();
    void redo();

private:
    std::stack<int> undoStack;
    std::stack<Question> undoQuestionStack;
    std::stack<int> redoStack;
    std::stack<Question> redoQuestionStack;

    //QStack<Element> undoStack;
   // QStack<Element> redoStack;
    Controller* m_controller;

    QWidget *createAddQuestionWidget();
    QWidget *createCentralWidget();

    QListWidget* m_repoListWidget;
// add new plant widget  GUI elements
    QLineEdit* m_IdEdit;
    QLineEdit* m_TextEdit;
    QLineEdit* m_TypeEdit;
    QLineEdit* m_SubjectEdit;
    QLineEdit* m_PossibleAnswersEdit;
    QLineEdit* m_CorrectAnswersEdit;
    QLineEdit* m_imagePathEdit;
protected:
    void keyPressEvent(QKeyEvent *e);
};

#endif // GUI_H
