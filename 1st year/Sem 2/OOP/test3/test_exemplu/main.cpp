#include <iostream>
#include "mystring.h"
#include <vector>
using namespace std;

int main()
{
    //Point p;
    //cin >> p;
    ////cout << p;
    //Point p2;
    //cin >> p2;
    //cout << p2;
    //Rectangle r(p, p2);
    Rectangle r;
    cin >> r;
    //cout << r<<endl;
    //cout<<r.getCreatedCount();
    RectangleDrawing rd;
    rd.addRectangle(r);
    cout << rd.getV()[0];
    rd.displayRectangles();
   
    return 0;
}
