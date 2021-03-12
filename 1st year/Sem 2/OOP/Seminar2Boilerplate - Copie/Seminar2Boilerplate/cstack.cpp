//#include "cstack.h"
//
//Stack createStack() {
//    Stack st;
//    st.top = -1;
//    return st;
//}
//
//Element pop(Stack* st) {
//    if (st->top < 0) {
//        cout << "Stack Underflow" << endl;
//        Element e;
//        e.type = ADD;
//        Planet p1;
//        p1.day = 1;
//        return e;
//    }
//    else {
//        Element topEl = st->elements[(st->top)--];
//        /*std::cerr << topEl << " popped out from the stack" << endl;*/
//        return topEl;
//    }
//}
//
//bool push(Stack* st, Element e) {
//    if (st->top >= (MAX_STACK_SIZE - 1)) {
//        cout << "Stack Overflow";
//        return false;
//    }
//    else {
//        st->elements[++(st->top)] = e;
//        /*cout << e << " pushed into stack" << endl;*/
//        return true;
//    }
//}
//
//bool isEmpty(Stack* st)
//{
//    return (st->top < 0);
//}
//
//void print(Stack* s)
//{
//    cout << "Stack content: " << endl << "------ ";
//    for (int idx = s->top; idx >= 0; idx--) {
//        cout << toString(s->elements[idx]) << " ";
//    }
//    cout << endl;
//}
//
//OperationType toString(Element e)
//{
//    return e.type;
//}