#ifndef MYSTRING_H
#define MYSTRING_H
#define _CRT_SECURE_NO_WARNINGS


#include <iostream>
#include <ostream>
#include <istream>
#include <vector>
using namespace std;
class Point
{
public:
    Point();//the default constructor
    Point(int newX,int newY);
    ~Point();//destructor

    int getX() const;
    void setX(int newX);

    int getY() const;
    void setY(int newY);

    friend ostream& operator<<(ostream & stream, const Point & p);
    friend istream& operator>>(istream& stream, Point& p);
       
    
private:
    int x;
    int y;
    
};

class Rectangle : public Point
{
private:
    Point left;
    Point right;
public:
    Rectangle();
    Rectangle(Point newLeft, Point newRight) {
        left = newLeft;
        right = newRight;
        CREATED_COUNT++;
    }
    bool operator&(const Rectangle& r) const;

    friend ostream& operator<<(ostream& stream, const Rectangle& r);
    friend istream& operator>>(istream& stream, Rectangle& r);

    Point getLeft() const { return left; }
    void setLeft(Point p) { left = p; }

    Point getRight() const { return right; }
    void setRight(Point p) { right = p; }

    static int getCreatedCount() { return CREATED_COUNT; }

    static int CREATED_COUNT;
};

#endif // MYSTRING_H

class RectangleDrawing
{
private:
    vector<Rectangle> v;
public:
    RectangleDrawing();
    vector<Rectangle> getV(){return v;}
    void displayRectangles();
    void addRectangle(Rectangle r) { v.push_back(r); }
};