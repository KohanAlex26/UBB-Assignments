#ifndef MYSTRING_H
#define MYSTRING_H

#include <iostream>
#include <ostream>
using namespace std;
class MyString
{
public:
	MyString();
	MyString(const char* data);
	MyString(const MyString &other);// !! const reference to the other object
	~MyString();

	int length() const;
	void setLength(int length);

	int capacity() const;
	void setCapacity(int capacity);

	MyString operator+(const MyString&);

	friend ostream& operator<<(ostream & stream, const MyString & str);
	bool operator <(const MyString& b) const;
	MyString& operator=(const MyString &other);

	static int getLivingCount() { return LIVING_COUNT; }
	static int getCreatedCount() { return CREATED_COUNT; }
private:
	char* m_data;
	int m_length;
	int m_capacity;

	//static member variable;
	static int CREATED_COUNT;
	static int LIVING_COUNT;
};
#endif // !MYSTRING_H

