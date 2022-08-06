#include <iostream>

using namespace std;

class MapleTree {
public:
 virtual string getName() { return "maple tree"; }
 string toString() { return "name = " + getName(); }
};

class SugarMapleTree : public MapleTree {
public:
 string getName() { return "sugar maple tree"; }
};

int main()
{
    MapleTree maple;
cout << maple.toString() << endl;
SugarMapleTree smaple;
cout << smaple.toString() << endl;
    return 0;
}
