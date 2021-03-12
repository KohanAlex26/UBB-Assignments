from domain.logic import Passanger
class Plane():
    def __init__(self,name,company,seats,destination,listOfPassengers):
        self.name=name
        self.company=company
        self.seats=seats
        self.destination=destination
        self.listOfPassengers=listOfPassengers
    def getname(self):
        return self.name
    def getnumberOfPassengers(self):
        return len(self.listOfPassengers)
    def getDestination(self):
        return self.destination
    def passengers(self):
        return self.listOfPassengers

    def __str__(self):
        s="Name of plane: "+self.name+"\n"+"Name of company: "+self.company+"\n"+"Number of seats: "+str(self.seats)+"\n"+\
          "Destination: "+self.destination+"\n"+"List of passengers: "+"["
        # print(type(self.listOfPassengers))
        for i in range(len(self.listOfPassengers)-1):
            s=s+str(self.listOfPassengers[i])+"\n"
        s=s+str(self.listOfPassengers[-1])
        s=s+"]"
        return s
    # def sortByLastName(self):


# l=Plane("a","b",100,"Bucuresti",[Passanger("a","b",1),Passanger("c","d",2),Passanger("e","f",3)])
# print(l)