from domain.logic import Passanger
from domain.planes import Plane
class PassengerRepo():
    def __init__(self,l):
        self.list=l[:]
    def getlist(self):
        return self.list
    def add(self,p):
        self.list.append(p)
    def __str__(self):
        s=""
        for i in range(len(self.list)):
            s=s+str(self.list[i])+"\n"
        return s

# a=Passanger("a","b",1)
# b=Passanger("c","d",2)
# c=Passanger("e","f",3)
# l=PassengerRepo()
# l.add(a)
# l.add(b)
# l.add(c)
# l.getlist()[0]=Passanger("c","d",2)
# print(l)