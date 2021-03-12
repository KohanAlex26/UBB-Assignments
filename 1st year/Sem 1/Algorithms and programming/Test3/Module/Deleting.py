from domain.logic import House
from Infrastructure.repo import HouseRepository
# list=[]
# o1=House(25,100)
# o2=House(30,200)
# o3=House(40,300)
# list.append(o1)
# list.append(o2)
# list.append(o3)
def delete(list):
    price=list.getElem(0).getPrice()
    for i in range(1,len(list)):
        if list.getElem(i).getPrice()>price:
            price=list.getElem(i).getPrice()
    for i in range(len(list)-1,-1,-1):
        if list.getElem(i).getPrice()==price:
            list.delete1(i)
    return list


# House(25,100)
# o1=House(25,100)
# o2=House(30,200)
# o3=House(40,300)
list=HouseRepository()
# list.append(o1)
# list.append(o2)
# list.append(o3)
# print(list)

def test_delete():
    delete(list)
    assert str(list)=="The House of area: 25 costs: 100"+"\n"+"The House of area: 30 costs: 200"+"\n"

# o4=House(25,100)
# def test_getPricePerSquareMeter(o4):
#     assert o4.test_getPricePerSquareMeter()==4

# def test_getNumberOfHouses(list):


# print(delete(list))