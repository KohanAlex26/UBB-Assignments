from infrastructure.repoPassengers import PassengerRepo
from infrastructure.repoPlanes import PlaneRepo
from domain.logic import Passanger
from domain.planes import Plane
# def updatePassenger(p,first,last,l):
#     i = 0
#     while i < len(l.getlist()) and  l.getlist()[i].getfirst() != first and l.getlist()[i].getlast()!=last:
#         i=i+1
#     l.getlist()[i]=p

def updatePlane(p,name,l):
    i = 0
    while i < len(l.getlist()) and  l.getlist()[i].getname()!=name:
        i=i+1
    l.getlist()[i]=p

def deleteAPassengerWithAGivenName(first,last,l):
    i=0
    while i<len(l.getlist()) and l.getlist()[i].getfirst()!=first and l.getlist()[i].getlast()!=last:
        i=i+1
    del l.getlist()[i]

def deleteAPlaneWithAGivenName(name,l):
    i=0
    while i<len(l.getlist()) and l.getlist()[i].getname()!=name:
        i=i+1
    del l.getlist()[i]

def planesWithNumberLower(number,l):
    l1=[]
    for i in range(len(l.getlist())):
        if l.getlist()[i].getnumberOfPassengers()<number:
            l1.append(l.getlist()[i])
    # for i in range(len(l1)):
    #     print(str(l1[i]))
    return l1

def passengersWithNameStartingWithAGivenString(s,l):
    l1 = []
    for i in range(len(l.getlist())):
        if l.getlist()[i].getfirst()[0]==s:
            l1.append(l.getlist()[i])
    # for i in range(len(l1)):
    #     print(l1[i])
    return l1

def planesWithGivenDestination(destination,l):
    l1 = []
    for i in range(len(l.getlist())):
        if l.getlist()[i].getDestionation()==destination:
            l1.append(l.getlist()[i])
    # for i in range(len(l1)):
    #     print(l1[i],"\n")
    return l1

# l=PassengerRepo([Passanger("a","b",1),Passanger("a","d",2),Passanger("e","f",3)])
# print(l)
# passengersWithNameStartingWithAGivenString("a",l)
# print(l)


# print(l4)
# planesWithGivenDestination("Bucuresti",l4)
# print(l4)


# l1=[Passanger("alex","c",1),Passanger("deni","b",2),Passanger("andrei","a",3)]
# l=Plane("a","b",123,"Bucuresti",l1)



#
l1=[Passanger("alex","k",123),Passanger("deni","r",1234),Passanger("andrei","m",123)]
l2=[Passanger("bizo","d",4),Passanger("arica","f",5)]
l3=[Passanger("daniel","m",1),Passanger("george","a",2),Passanger("ion","c",3)]
l4=PlaneRepo([Plane("a","b",123,"Cucuresti",l1),Plane("c","d",300,"Vienna",l2),Plane("e","f",400,"Bucuresti",l3)])
def identifyPlanesWith3Numbers(list):
    def f(x):
        a=x.passengers()[0].getnumber()
        while a>999:
            a=a/10
        for i in range(1,len(x.passengers())):
            b = x.passengers()[i].getnumber()
            while b>999:
                b=b//10
            if a==b:
                return True
            return False
    return MySearch(list.returnObject().getlist(),f)
def MySearch(l,f):
    a=[]
    for elem in l:
        if f(elem)==True:
            a.append(elem)
    return a
def identifyPassenger(plane,str):
    def f(x):
        if str in x.getfirst() or str in x.getlast():
            return True
        return False
    return MySearch(plane.passengers(),f)
# d=identifyPassenger(Plane("a","b",123,"Cucuresti",[Passanger("alex","k",123),Passanger("deni","r",1234),Passanger("mndrei","a",123)]).passengers(),"a")
# for i in range(len(d)):
#     print(d[i])
def searchGuyInPlane(list,first,last):
    def f(x):
        for i in range(len(x.passengers())):
            if x.passengers()[i].getfirst()==first and x.passengers()[i].getlast()==last:
                return True
            return False
    return MySearch(list.returnObject().getlist(),f)
# c=identifyPlanesWith3Numbers(l4.getlist())
# for i in range(len(c)):
#     print(c[i])
# b=searchGuyInPlane(l4.getlist(),"bizo","d")
# for i in range(len(b)):
#     print(b[i])
#  def sortPassengersInAPlane(plane):
#     def f(x,y):
#             if x()[i].getlast()>x.passengers()[i].getlast()
# sortPassengersInAPlane(l4)


def bubbleSort(l,f):
    ok=False
    while not ok:
        ok=True
        for i in range(len(l)-1):
            if not f(l[i],l[i+1]):
                l[i],l[i+1]=l[i+1],l[i]
                ok=False

def sortPlanesByNumberOfPassengers(l):
    def f(x,y):
        a=len(x.passengers())
        b=len(y.passengers())
        return a<=b
    return bubbleSort(l.returnObject().getlist(),f)



# print(l4)
# print(l4)
# sortPlanesByNumberOfPassengers(l4)
# print(l4)

def sortPassengersInAPlaneByName(l):
    def f(x,y):
        if x.getlast()<y.getlast():
            return 1
        return 0
    for i in range(len(l.returnObject().getlist())):
        bubbleSort(l.returnObject().getlist()[i].passengers(),f)

# sortPassengersInAPlaneByName(l4)
# print(l4)

def sortPlanesByAStr(l,str):
    def f(x,y):
        a=0
        b=0
        for i in range(len(x.passengers())):
            if len(x.passengers()[i].getfirst())>=len(str):
                ok=1
                for j in range(len(str)):
                    if x.passengers()[i].getfirst()[j]!=str[j]:
                        ok=0
                if ok==1:
                    a=a+1
        for i in range(len(y.passengers())):
            if len(y.passengers()[i].getfirst())>= len(str):
                ok = 1
                for j in range(len(str)):
                    if y.passengers()[i].getfirst()[j] != str[j]:
                        ok = 0
                if ok == 1:
                    b = b + 1
        return a<=b
    bubbleSort(l.returnObject().getlist(),f)
# sortPlanesByAStr(l4,"a")
# print(l4)

def sortByConcatNumberOfPassAndDest(l):
    def f(x,y):
        a=""
        b=""
        a=a+str(len(x.passengers()))
        b=b+str(len(y.passengers()))
        a=a+x.getDestination()
        b=b+y.getDestination()
        return a<=b
    bubbleSort(l.returnObject().getlist(),f)
# sortByConcatNumberOfPassAndDest(l4)
# print(l4)
