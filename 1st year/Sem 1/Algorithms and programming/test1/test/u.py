def getKey1(p):
    return p.getlast()
def getKey2(p):
    return len(p.listOfPassengers)
def getKey3(l,str="a"):
    k=0
    for i in range(len(l.listOfPassengers)):
        if str in l.listOfPassengers[i].getfirst():
            k=k+1
    return k
def getKey4(l):
    a=str(len(l.listOfPassengers))+l.destination
    return a
def sort1(l):
    l.getlist().sort(key=getKey1)
    print(l)

def f1(x,y):
    return len(x.listOfPassengers)<=len(y.listOfPassengers)
def f2(x,y,str):
    a=0
    for i in (x.listOfPassengers):
        if str in i:
            a=a+1
    b=0
    for j in range(y.listOfPassengers):
        if str  in j:
            b=b+1
    return a<=b
