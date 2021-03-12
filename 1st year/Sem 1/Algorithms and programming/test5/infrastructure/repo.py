from domain.logic import Employee
class EmployeeRepo():
    def __init__(self,l1,l2):
        self.listIT=l1[:]
        e1=Employee("Maria",4000,"IT")
        e2=Employee("Ion",4700,"IT")
        e3=Employee("Ana",5200,"IT")
        self.listIT=[e1,e2,e3]
        self.listHR=l2[:]
        e4=Employee("Vlad",3400,"HR")
        e5=Employee("Tudor",4300,"HR")
        e6=Employee("Carina",3100,"HR")
        self.listHR=[e4,e5,e6]
    def getlistIT(self):
        return self.listIT
    def getlistHR(self):
        return self.listHR
    def addIT(self,e):
        self.listIT.append(e)
    def addHR(self,e):
        self.listHR.append(e)
    # def highestPaidIT(self):
    #     mx=0
    #     em=0
    #     for i in range(len(self.listIT)):
    #         if self.listIT[i].getsalary()>mx:
    #             mx= self.listIT[i].getsalary()
    #             em=self.listIT[i]
    #     return em
    # def highestPaidHR(self):
    #     mx=0
    #     em=0
    #     for i in range(len(self.listHR)):
    #         if self.listHR[i].getsalary()>mx:
    #             mx= self.listHR[i].getsalary()
    #             em=self.listHR[i]
    #     return em
    def __str__(self):
        s=""
        for i in range(len(self.listIT)):
            s=s+str(self.listIT[i])+"\n"
        s=s+"\n"
        for i in range(len(self.listHR)):
            s=s+str(self.listHR[i])+"\n"
        return s