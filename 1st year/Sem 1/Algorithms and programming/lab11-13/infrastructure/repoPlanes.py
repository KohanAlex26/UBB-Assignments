from domain.logic import Passanger
from domain.planes import Plane
class PlaneRepo():
    def __init__(self,l):
        self.list=l[:]
    def getlist(self):
        return self.list
    def add(self,p):
        self.list.append(p)
    def __str__(self):
        s=""
        for i in range(len(self.list)):
            s=s+str(self.list[i])+"\n"+"\n"
        return s