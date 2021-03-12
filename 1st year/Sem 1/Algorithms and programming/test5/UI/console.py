from domain.logic import Employee
from infrastructure.repo import EmployeeRepo
from utility.utils import *
class UI():
    def __init__(self,controller):
        self.controller=controller
    def __str__(self):
        return str(self.controller)
    @staticmethod
    def printMenu():
        menuOptions = ["Exit","highestPaidIT", "highestPaidHR","getlistIT","getlistHR"]
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
                    repo=EmployeeRepo([Employee("Maria",4000,"IT"),Employee("Ion",4700,"IT"),Employee("Ana",5200,"IT")],[])
                    print(MySearch(repo.getlistIT()))
                elif i==2:
                    repo = EmployeeRepo([],[Employee("Vlad",3400,"HR"),Employee("Tudor",4300,"HR"),Employee("Carina",3100,"HR")])
                    print(MySearch(repo.getlistHR()))
                elif i==3:
                    print(self.getlistIT())
                elif i==4:
                    print(self.getlistHR())
                if i!=0:
                    print()
                    # input("Press ENTER to continue.")
            except Exception as e:
                print("Error",e)