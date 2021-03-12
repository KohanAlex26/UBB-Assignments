#include "Number.h"
#include "Name.h"
#include "Client.h"
#include "PriorityQueue.h"
#include <string>
#include "Test.h"
#include <iostream>

void printMenu() {
    cout << "1. Add a client" << endl;
    cout << "2. Remove the client with the highest priority" << endl;
    cout << "3. Print root" << endl;
    cout << "0. Exit" << endl;
}

void application() {
    int option = 1;
    string rel;
    printf("Read the relation of the PriorityQueue('<' or '>'):");
    std::cin >> rel;
    PriorityQueue  t(rel);

    Name e("alin");
    Number p(1);
    Client client(e, p);

    Name e1("bogdan");
    Number p1(-1);
    Client client1(e1, p1);

    Name e2("calin");
    Number p2(0);
    Client client2(e2, p2);

    Name e3("darius");
    Number p3(4);
    Client client3(e3, p3);

    Name e4("emanuel");
    Number p4(5);
    Client client4(e4, p4);

    t.push(&client);
    t.push(&client1);
    t.push(&client2);
    t.push(&client3);
    t.push(&client4);

    while (option != 0) {
        printMenu();
        cin >> option;
        printf("\n");
        if (option == 1) {
            string s;
            printf("Read the name of the customer:");
            cin >> s;
            Name e(s);
            int value;
            printf("Read the value(int):");
            cin >> value;
            Number p(value);
            Client* client = new Client(e, p);
            t.push(client);
        }
        else if (option == 2) {
            Name e;
            Number p;
            t.pop(&e, &p);
            cout << "Removed" << endl;
        }
        else if (option == 3)
        {
            if (t.isEmpty()==0)
                t.top()->representation();
        }
        else if (option != 0) {
            cout << "This is not a valid option. Try again!" << endl;
        }
    }
}
