class Passanger():
    def __init__(self,first,last,number):
        self.first=first
        self.last=last
        self.number=number
    def getfirst(self):
        return self.first
    def getlast(self):
        return self.last
    def getnumber(self):
        return self.number
    def __str__(self):
        return self.getfirst()+" "+self.getlast()+" "+str(self.number)



#
# l1=[Passanger("alex","k",1),Passanger("deni","r",2),Passanger("andrei","m",3)]
# l2=[Passanger("bizo","d",4),Passanger("rares","f",5)]
# l3=[Passanger("daniel","m",1),Passanger("george","a",2),Passanger("ion","c",3)]
# l4=[Plane("a","b",123,"Bucuresti",l1),Plane("c","d",300,"Vienna",l2),Plane("e","f",400,"Bucuresti",l3)]
# print(type(l4[0].getlist()))


# updatePassenger(Passanger("andrei","m",3),"alex","k")
# updatePlane(Plane("e","f",400,"Bucureti",100),"a")
# deleteAPassengerWithAGivenName("deni","r")
# deleteAPlaneWithAGivenName("a")
# planesWithNumberLower(3)
# passengersWithNameStartingWithAGivenString("a")
# planesWithGivenDestination("Bucuresti")