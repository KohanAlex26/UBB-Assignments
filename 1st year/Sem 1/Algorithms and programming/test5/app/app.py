# from domain.logic import Employee
# from infrastructure.repo import EmployeeRepo
# from UI.console import UI
# from controller.controller import Controller
# def run():
#     repo=EmployeeRepo([],[])
#     ui=UI(repo)
#     ui.handleMenu()
# run()
def rec(x):
    if x<10:
        print(1)
    else:
        rec(x//10)
        print(2)
# rec(1223)
def myzip(list1,list2):
    list3=[]
    if len(list1)==1:
        list3.append(list1[0])
        list3.append(list2[0])
        print(list3)
    else:
        myzip(list1[:-1],list2[:-1])
        print(list3)
        list3.append(list1[-1])
        list3.append(list2[-1])

# myzip([1,2,3],[1,2,3])

def myzip1(list1,list2):
    if len(list1)==1:
        return [list1[0],list2[0]]
    else:
        return myzip1(list1[:-1],list2[:-1])+[list1[-1]]+[list2[-1]]

print(myzip1([1,2,3],[1,2,3]))