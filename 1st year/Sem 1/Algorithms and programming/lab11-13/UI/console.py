from domain.logic import Passanger
from domain.planes import Plane
from infrastructure.repoPassengers import PassengerRepo
from infrastructure.repoPlanes import PlaneRepo
from utils.utility import *
class UI():
    def __init__(self,controller):
        self.controller=controller
    def __str__(self):
        return str(self.controller)
    @staticmethod
    def printMenu():
        menuOptions = ["Exit","Update Passenger", "deleteAPassengerWithAGivenName","passengersWithNameStartingWithAGivenString","create Passenger","See Repo","sortPassengersInAPlaneByName","Add plane","sortPlanesByNumberOfPassengers","sortPlanesByAStr","sortByConcatNumberOfPassAndDest","identifyPassenger","searchGuyInPlane","identifyPlanesWith3Numbers"]
        # for i in range(100):
        #     print()
        for i in range(len(menuOptions)):
            print((" " if i < 10 else "") + str(i) + "." + menuOptions[i])
        print()

    def handleMenu(self):
        while True:
            try:
                UI.printMenu()
                i = int(input("Give me option:"))
                if i==0:
                    print("Good bye")
                    break
                elif i==1:
                    self.controller.updatePassengerControll(Passanger("alex","k",2),"Muresean","s")
                elif i==2:
                    deleteAPassengerWithAGivenName("alex","k",self.controller)
                elif i==3:
                    passengersWithNameStartingWithAGivenString("a",self.controller)
                elif i==4:
                    first=input("Give me first:")
                    last = input("Give me last:")
                    number=int(input("give me number:"))
                    self.controller.createPassenger(first,last,number)
                elif i==5:
                    print(self.controller)
                    input("Press ENTER to continue.")
                elif i==6:
                    sortPassengersInAPlaneByName(self.controller)
                elif i==7:
                    self.controller.addPlane()
                elif i==8:
                    sortPlanesByNumberOfPassengers(self.controller)
                elif i==9:
                    a=input("give me str= ")
                    sortPlanesByAStr(self.controller,a)
                elif i==10:
                    sortByConcatNumberOfPassAndDest(self.controller)
                elif i==11:
                    p=self.controller.createPlane()
                    a=identifyPassenger(p,"a")
                    for elem in a:
                        print(elem)
                        print("\n")
                    input("Press ENTER to continue.")
                elif i==12:
                    a=searchGuyInPlane(self.controller,"alex","k")
                    for elem in a:
                        print(elem)
                        print("\n")
                    input("Press ENTER to continue.")
                elif i==13:
                    a=identifyPlanesWith3Numbers(self.controller)
                    for elem in a:
                        print(elem)
                        print("\n")
                    input("Press ENTER to continue.")
                if i!=0:
                    print()
                    # input("Press ENTER to continue.")
            except Exception as e:
                print("Error",e)