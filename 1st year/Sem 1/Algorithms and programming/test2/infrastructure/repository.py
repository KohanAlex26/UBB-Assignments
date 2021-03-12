from domain.logic import Student
class Repository():
    def __init__(self):
        self.list=[]
    def add(self,o):
        self.list.append(o)
    def highestGrade(self,str):
        hg=0
        for i in range(len(self.list)):
            ok=1
            if len(str)<=len(self.list[i].getName()):
                for j in range(len(str)):
                    if str[j]!=self.list[i].getName()[j]:
                        ok=0
                if ok==1 and self.list[i].getGrade()>hg:
                    hg=self.list[i].getGrade()
        if hg==0:
            return "Wrong"
        return hg

    def deleteStudents(self,mn):
        for i in range(len(self.list)-1,-1,-1):
            if self.list[i].getGrade()<mn:
                del self.list[i]
        a=""
        for i in range(len(self.list)):
           a=a+str(self.list[i])+"\n"
        return a
# l=Repository()
# a=Student("aabc",100)
# b=Student("aabd",200)
# c=Student("asrbc",300)
# l.add(a)
# l.add(b)
# l.add(c)
# l.highestGrade("aab")
# print(l.deleteStudents(200))
# l.characterSequence("abc")
# l.deleteMaxAmountOfMoney()
