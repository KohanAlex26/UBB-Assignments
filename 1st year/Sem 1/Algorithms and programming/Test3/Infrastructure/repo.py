from domain.logic import House
class HouseRepository():
    def __init__(self):
        o1=House(25,100)
        o2=House(30,200)
        o3=House(40,500)
        self.list=[o1,o2,o3]

    def getElem(self,i):
        return self.list[i]
    def delete1(self,i):
        del self.list[i]
    def getNrofHouses(self,k,p):
        c=0
        for i in range(len(self.list)):
            if self.list[i].getArea()>=k and self.list[i].getPrice()<p:
                c=c+1
        return c
    def __str__(self):
        s=""
        for i in range(len(self.list)):
            s=s+str(self.list[i]) + "\n"
        return s
    def __len__(self):
        return len(self.list)
    # def delete1(self):
    #     price = self.list[0].getPrice()
    #     for i in range(1, len(self.list)):
    #         if self.list[i].getPrice() > price:
    #             price = self.list[i].getPrice()
    #     for i in range(len(self.list) - 1, -1, -1):
    #         if self.list[i].getPrice() == price:
    #             del self.list[i]
    # def append(self,p):
    #     self.list.append(p)



# o1=House(25,100)
# o2=House(30,200)
# o3=House(40,300)
# list=HouseRepository()
# list.append(House(25,100))
# list.append(House(30,200))
# list.delete1()
# print(list)

# print(list.getNrofHouses(20,400))
# print(list)