from domain.logic import Passanger
from infrastructure.repoPassengers import PassengerRepo
from domain.planes import Plane
class Controller():
    def __init__(self,repo):
        self.repo=repo
    def returnObject(self):
        return self.repo
    def createPassenger(self,f,l,n):
        p=Passanger(f, l, n)
        self.repo.add(p)
    def createPlane(self):
        n=input("name")
        c=input("company")
        s=int(input("seats"))
        d=input("destination")
        l=[Passanger("daniel","m",1),Passanger("george","a",2),Passanger("ion","c",3)]
        return Plane(n,c,s,d,l)
    def addPlane(self):
        p=Plane("a", "b", 123, "Cucuresti",[Passanger("alex", "k", 123), Passanger("deni", "r", 1234), Passanger("mndrei", "a", 123)])
        p1=Plane("c","d",300,"Vienna",[Passanger("bizo","f",4),Passanger("brica","d",5)])
        p2=Plane("e","f",400,"Bucuresti",[Passanger("micu","k",123),Passanger("aobi","r",1234),Passanger("alin","m",123)])
        self.repo.add(p)
        self.repo.add(p1)
        self.repo.add(p2)

    def updatePassengerControll(self, p, first, last):
        i = 0
        while i < len(self.repo.getlist()) and self.repo.getlist()[i].getfirst() != first and self.repo.getlist()[i].getlast() != last:
            i = i + 1
        self.repo.getlist()[i] = p
    def __str__(self):
        return str(self.repo)
