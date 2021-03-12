#include "Header.h"
#include <string.h>

int MyString::CREATED_COUNT=0;
int MyString::LIVING_COUNT = 0;
MyString::MyString()
{
	m_capacity = 100;
	m_data = new char[m_capacity];
	m_length = 0;
	m_data[0] = '\0';
	CREATED_COUNT++;
	LIVING_COUNT++;

}

MyString::MyString(const char* data)
{
	int size = strlen(data);
	m_capacity = 2 * size;
	m_length = size;
	m_data = new char[m_capacity];
	strcpy(m_data, data);
	CREATED_COUNT++;
	LIVING_COUNT++;
}

MyString::MyString(const MyString& other)
{
	m_capacity = other.m_capacity;
	m_length = other.m_length;

	///m_data=other.m_data !!! only copies pointer, shallow copy
	/// deep copy below
	m_data = new char[m_capacity];
	strcpy(m_data, other.m_data);
	CREATED_COUNT++;
	LIVING_COUNT++;
}

MyString::~MyString()
{
	delete[] m_data;
	m_capacity = m_length = 0;
	LIVING_COUNT--;
}

int MyString::length() const
{
	return m_length;
}

int MyString::capacity() const
{
	return m_capacity;
}

void MyString::setLength(int length) const
{
	m_length=length;
}

void MyString::setCapacity(int capacity) const
{
	m_capacity=capacity;
}

//used for cout<<
ostream& operator<<(ostream& stream, const MyString& str)
{
	stream << str.m_data;
	return stream;
}

bool MyString::operator<(const MyString& b) const
{
	return strcmp(m_data, b.m_data)>0;
}

MyString MyString::operator+(const MyString& other)
{
	MyString str;
	char* dataConcat = new char[other.capacity() + m_capacity];
	strcpy(dataConcat, m_data);
	strcat(dataConcat, other.m_data);
	MyString str{dataConcat};
	return str;
}

MyString &MyString::operator=(const MyString &other)
{
	if (this == &other)
		return *this;
	m_capacity = other.m_capacity;
	m_length = other.m_length;

	if (m_data != nullptr)
	{
		delete[] m_data;
		m_data = nullptr;
	}
	m_data = new char[m_capacity];
	strcpy(m_data, other.m_data);

	return *this;
}