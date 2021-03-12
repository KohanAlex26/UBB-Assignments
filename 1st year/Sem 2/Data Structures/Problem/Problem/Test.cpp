#include <assert.h>
#include "PriorityQueue.h"
#include <string>
#include "Test.h"
#include <iostream>
using namespace std;

void tesNumberQueue() {

	PriorityQueue b("<");
	Name e("a");
	Number p(1);
	Client client(e, p);

    Name e1("b");
    Number p1(0);
    Client client1(e1, p1);

    Name e2("c");
    Number p2(-1);
    Client client2(e2, p2);

    Name e3("d");
    Number p3(4);
    Client client3(e3, p3);

	Number p4(5);

	assert(b.push(&client) == 1);
	assert(b.push(&client1) == 1);
	assert(b.push(&client2) == 1);
	assert(b.push(&client3) == 1);
	assert(b.top() == &client3);


	assert(b.isEmpty() == 0);
	Name e6;
	Number p6;

	assert(b.pop(&e6,&p6) == 1);
	assert(b.pop(&e6, &p6) == 1);
	assert(b.pop(&e6, &p6) == 1);
	assert(b.pop(&e6, &p6) == 1);
	assert(b.pop(&e6, &p6) == 0);
	assert(b.isEmpty() == 1);


}