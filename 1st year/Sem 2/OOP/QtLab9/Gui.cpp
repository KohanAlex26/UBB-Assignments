#include "Gui.h"
#include "controller.h"
#include <QGridLayout>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QDebug>
#include <QFileDialog>
#include <QIcon>
#include <QFrame>
#include <QAction>
#include <QMenuBar>
#include <QStatusBar>


void QuestionGUI::keyPressEvent(QKeyEvent *e)
{
    if((e->modifiers()==Qt::ControlModifier)&&(e->key()==Qt::Key_Z))
    {
      QuestionGUI::undo();
      repoPopulate();
    }
    if((e->modifiers()==Qt::ControlModifier)&&(e->key()==Qt::Key_Y))
    {
      QuestionGUI::redo();
      repoPopulate();
    }

}
QuestionGUI::QuestionGUI(QWidget *parent) : QMainWindow(parent)
{
    undoStack.push(-1);
    redoStack.push(-1);
    Question q;
    undoQuestionStack.push(q);
    redoQuestionStack.push(q);
    //Question q;
    //undoStack.push({q,-1});
    //redoStack.push({q,-1});
    m_controller = new Controller{};
//    m_controller->saveToFile("test.csv");
    this->setWindowTitle("Questions");
    this->setMinimumSize(QSize(800, 200));

    QMenu* fileMenu = this->menuBar()->addMenu("&File"); //mnemonics ALT+F
    QAction* undoAction = new QAction("&Undo");
    QAction* redoAction = new QAction("&Redo");

    fileMenu->addAction(redoAction);
    fileMenu->addAction(undoAction);
    connect(undoAction, &QAction::triggered, this, &QuestionGUI::undo);
    connect(undoAction, &QAction:: triggered, this, &QuestionGUI:: repoPopulate);
    connect(redoAction, &QAction::triggered, this, &QuestionGUI:: redo);
    connect(redoAction, &QAction::triggered, this, &QuestionGUI:: repoPopulate);
    QWidget *centralW = createCentralWidget();

    this->setCentralWidget(centralW);

}
void QuestionGUI::onAddQuestionPressed()
{
    Question p;
    p.setId(m_IdEdit->text().toUInt());
    p.setText(m_TextEdit->text());
    p.setType(m_imagePathEdit->text());
    undoStack.push(0);
    undoQuestionStack.push(p);
//    p.setSubject(m_SubjectEdit->text());
//    p.setPossibleAnswers(m_PossibleAnswersEdit->text().toUInt());
//    p.setCorrectAnswers(m_CorrectAnswersEdit);
   bool added = m_controller->addQuestion(p);

}
void QuestionGUI::onRemoveQuestionPressed()
{
    //Question p;
    //p.setId(m_IdEdit->text().toUInt());
    //bool added = m_controller->removeQuestion(p.id());
    int selectedRow = m_repoListWidget->currentRow();
    undoStack.push(1);
    undoQuestionStack.push(m_controller->repo()[selectedRow]);
    m_controller->removeQuestion(m_controller->repo()[selectedRow]);
}
void QuestionGUI::onUpdateQuestionPressed()
{
    Question q;
    q.setId(m_IdEdit->text().toUInt());
    q.setText(m_TextEdit->text());
    q.setType(m_imagePathEdit->text());
    int selectedRow = m_repoListWidget->currentRow();
    m_controller->updateQuestion(selectedRow,q);
}
void QuestionGUI::showDialog()
{
    QString path;
    path=QFileDialog:: getOpenFileName(this, "Select picture", "", "(*.jpg *.png)");
    m_imagePathEdit->setText(path);
}

void QuestionGUI::repoPopulate()
{

    QVector<Question>Question_repo= m_controller->repo();

    m_repoListWidget->clear();
    for(auto pl: Question_repo){
        if(!pl.imagePath().isEmpty()){
            QIcon icon(pl.imagePath());
            if(!icon.isNull()){
                new QListWidgetItem(icon, pl.text(),m_repoListWidget);
            }
            else
                new QListWidgetItem(pl.text(),m_repoListWidget);
        }
        else
            new QListWidgetItem(pl.text(),m_repoListWidget);
    }
}

//bool QuestionGUI::undoOperation()
//{
//    if(!undoStack.empty())
//    {
//        Question q=this->undoStack.top().q;
//        switch (this->undoStack.top().i)
//        {
//        case 1:
//            this->m_controller->removeQuestion(q);
//            this->redoStack.push({q,1});
//            break;
//        case 2:
//            this->m_controller->addQuestion(q);
//            this->redoStack.push({q,2});
//            break;
//        default:
//            throw  std::string("Unable undo operation!");
//        }
//        this->undoStack.pop();
//        return true;
//    }
//    else throw std::string("No available undo steps");
//}

//bool QuestionGUI::redoOperation()
//{
//    if(!redoStack.empty())
//    {
//        Question q=this->redoStack.top().q;
//        switch (this->redoStack.top().i)
//        {
//        case 1:
//            this->m_controller->addQuestion(q);
//            this->undoStack.push({q,1});
//            break;
//        case 2:
//            this->m_controller->removeQuestion(q);
//            this->undoStack.push({q,2});
//            break;
//        default:
//            throw  std::string("Unable redo operation!");
//        }
//        this->redoStack.pop();
//        return true;
//    }
//    else throw std::string("No available redo steps");
//}

QWidget *QuestionGUI::createAddQuestionWidget()
{
    QWidget* widget = new QWidget(this);

    QGridLayout* grid = new QGridLayout(this);
    QLabel *labelN = new QLabel("id", this);
    m_IdEdit = new QLineEdit(this);
    QLabel *labelNS = new QLabel("text", this);
    m_TextEdit = new QLineEdit(this);
    QLabel *labelI = new QLabel("image", this);
    m_imagePathEdit = new QLineEdit(this);

    QPushButton *button = new QPushButton("Browse",this);
    QPushButton *buttonAdd = new QPushButton("Add Question",this);
    QPushButton *buttonRemove = new QPushButton("Remove Question",this);
    QPushButton *buttonUpdate= new QPushButton("Update Question",this);

    grid->addWidget(labelN,0,0);
    grid->addWidget(m_IdEdit,0,1,1,2);
    grid->addWidget(labelNS,1,0);
    grid->addWidget(m_TextEdit,1,1,1,2);
    grid->addWidget(labelI,2,0);
    grid->addWidget(m_imagePathEdit,2,1);
    grid->addWidget(button,2,2);
    grid->addWidget(buttonAdd,3,2);
    grid->addWidget(buttonRemove,3,3);
    grid->addWidget(buttonUpdate,3,4);

    widget->setLayout(grid);

    connect(buttonAdd, &QPushButton:: pressed, this, &QuestionGUI:: onAddQuestionPressed);
    connect(buttonRemove, &QPushButton:: pressed, this, &QuestionGUI:: onRemoveQuestionPressed);
    connect(button, &QPushButton:: pressed, this, &QuestionGUI:: showDialog );
    connect(buttonAdd, &QPushButton:: pressed, this, &QuestionGUI:: repoPopulate);
    connect(buttonRemove, &QPushButton:: pressed, this, &QuestionGUI:: repoPopulate);
    connect(buttonUpdate, &QPushButton:: pressed, this, &QuestionGUI:: onUpdateQuestionPressed);
    connect(buttonUpdate, &QPushButton:: pressed, this, &QuestionGUI:: repoPopulate);
    return widget;
}

QWidget *QuestionGUI::createCentralWidget()
{
    QWidget* widget=new QWidget(this);
    m_repoListWidget=new QListWidget(this);

    QWidget* addNewQuestionW = createAddQuestionWidget();
    QVBoxLayout* vBoxLayout = new QVBoxLayout;

    vBoxLayout->addWidget(addNewQuestionW);

    QFrame *line =  new QFrame(this);
    line->setFrameShape(QFrame::HLine);
    line->setFrameShadow(QFrame::Sunken);
    vBoxLayout->addWidget(line);

    vBoxLayout->addWidget(m_repoListWidget);
    widget->setLayout(vBoxLayout);

    repoPopulate();

    return widget;
}
void QuestionGUI::undo()
{
    qDebug()<<"undo";
    if(undoStack.top()==0)
    {
        redoStack.push(0);
        redoQuestionStack.push(undoQuestionStack.top());
        m_controller->removeQuestion(undoQuestionStack.top());
        undoStack.pop();
        undoQuestionStack.pop();
    }
    if(undoStack.top()==1)
    {
        redoStack.push(1);
        redoQuestionStack.push(undoQuestionStack.top());
        m_controller->addQuestion(undoQuestionStack.top());
        undoStack.pop();
        undoQuestionStack.pop();
    }
}
void QuestionGUI::redo()
{
    if(redoStack.top()==0)
    {
        undoStack.push(0);
        undoQuestionStack.push(redoQuestionStack.top());
        m_controller->addQuestion(redoQuestionStack.top());
        redoStack.pop();
        redoQuestionStack.pop();
    }
    if(redoStack.top()==1)
    {
        undoStack.push(1);
        undoQuestionStack.push(redoQuestionStack.top());
        m_controller->removeQuestion(redoQuestionStack.top());
        redoStack.pop();
        redoQuestionStack.pop();
    }
}

