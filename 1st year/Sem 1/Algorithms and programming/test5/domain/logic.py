class Employee():
    def __init__(self,name,salary,d):
        self.name=name
        self.salary=salary
        self.department=d
    def getname(self):
        return self.name
    def getsalary(self):
        return self.salary
    def getdepartment(self):
        return self.department
    def setname(self,n):
        if n=="":
            raise ValueError("Need a name")
        else:
            self.name=n
    def setsalary(self,s):
        if s<0:
            raise ValueError("Need positive")
        else:
            self.salary=s
    def __str__(self):
        return self.name+" "+str(self.salary)+" "+self.department