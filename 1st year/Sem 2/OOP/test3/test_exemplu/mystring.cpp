#include "mystring.h"
#include <string>
int Rectangle::CREATED_COUNT = 0;
Point::Point()//the default constructor
{
    x = 0;
    y = 0;
}

Point::Point(int newX,int newY)
{
    x = newX;
    y = newY;
}


Point::~Point()
{
    x = 0;
    y = 0;
}

int Point::getX() const
{
    return x;
}

void Point::setX(int newX)
{
   x=newX;
}

int Point::getY() const
{
    return y;
}

void Point::setY(int newY)
{
    y=newY;
}

//Point Point::operator+(const Point &other)
//{
//
//    char *dataConcat = new char[other.m_capacity + m_capacity];
//    strcpy(dataConcat,m_data);
//    strcat(dataConcat,other.m_data);
//    //get data from dataConcat to str;
//    Point str{dataConcat};
//    return str;
//}
//
//Point &Point::operator=(const Point &other)
//{
//    if(this == &other)
//        return *this;
//    m_capacity = other.m_capacity;
//    m_length = other.m_length;
//
//    if(m_data != nullptr)// avoid mem leaks
//    {
//        delete[] m_data;
//        m_data = nullptr;
//    }
//    m_data = new char[m_capacity];
//    strcpy(m_data,other.m_data);
//    return *this;
//}
//
//bool Point::operator<(const Point &b) const
//{
//    return (strcmp(m_data,b.m_data)<0);
//}

//used for cout <<
ostream & operator<<(ostream &stream, const Point &p)
{
    stream << p.x << " " << p.y;
    return stream;
}

istream & operator>>(istream& stream, Point& p)
{
    cout << "read x:";
    int x1, y1;
    stream >> x1;
    cout << "read y:";
    stream >> y1;
    p.setX(x1);
    p.setY(y1);

    return stream;
}

ostream& operator<<(ostream& stream, const Rectangle& r)
{
    stream << r.left.getX() << " " << r.left.getY() << " " << r.right.getY() << " " << r.right.getY();
    return stream;
}

istream& operator>>(istream& stream, Rectangle& r)
{
    cout << "read x(Left):";
    int x1, y1,x2,y2;
    stream >> x1;
    cout << "read y(Left):";
    stream >> y1;

    cout << "read x(Right):";
    stream >> x2;
    cout << "read y(Right):";
    stream >> y2;

    r.left.setX(x1);
    r.left.setY(y1);
    r.right.setX(x2);
    r.right.setY(y2);

    return stream;
}

bool Rectangle::operator&(const Rectangle& r) const
{
    return(r.getRight().getX()>left.getX() && r.getRight().getX()<right.getX() && r.getRight().getY() > left.getY() && r.getRight().getY() < right.getY())||(r.getLeft().getX() < right.getX() && r.getRight().getX() > left.getX() && r.getLeft().getY() < right.getY() && r.getLeft().getY() > left.getY())

}

void RectangleDrawing::displayRectangles()
{
    for (int i = 0;i <= v.size() - 2;i++)
    {
        int ok = 1;
        for (int j = i + 1;j <= v.size() - 1;j++)
            if ((v[i] & v[j]) == 1)
                ok = 0;
        if (ok == 1)
            cout << v[i];
    }
}

Rectangle::Rectangle()//the default constructor
{
    left.setX(0);
    left.setY(0);
    right.setX(0);
    right.setY(0);
    CREATED_COUNT++;
}
RectangleDrawing::RectangleDrawing()
{

}
