class Student():
    def __init__(self,name,grade):
        self.name=name
        self.grade=grade
    def getName(self):
        return self.name
    def getGrade(self):
        return self.grade
    def __str__(self):
        return self.name+" "+ str(self.grade)
# a=Student("abc",3)
# print(a.leng())