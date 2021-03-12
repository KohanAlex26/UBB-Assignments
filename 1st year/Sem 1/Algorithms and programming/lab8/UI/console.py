from domain.logic import MyVector
from infrastructure.repo import VectorRepository
def createVector():
    n=input("Give me name:")
    c=input("Give me color:")
    if c!="r" and c!="g" and c!="b" and c!="y" and c!="m":
        print()
        print("Try another color")
        print()
        c=input("Give me color:")
    t=int(input("Give me type:"))
    v=input("Give me values:")
    return MyVector(n,c,t,v)

menuOptions=["Exit","Add a vector to the repository","Get all vectors","Get a vector at an index","Update a vector by index","Update a vector by name","Delete a vector by index","Delete a vector by name","Plot all vectors"]

def handleMenu(repo,i):
    if i==0:
        print("Good bye")
    elif i==1:
        repo.addp(createVector())
    elif i==2:
        print(repo.getVectors())
    elif i==3:
        index=int(input("Give me index:"))
        print(repo.vectorAtIndex(index))
    elif i==4:
        index = int(input("Give me index:"))
        repo.updateVector(index,createVector())
    elif i==5:
        name=input("Give me name:")
        repo.updateVectorByName(name,createVector())
    elif i==6:
        index = int(input("Give me index"))
        repo.deleteByIndex(index)
    elif i==7:
        name=input("Give me name:")
        repo.deleteByName(name)
    elif i==8:
        repo.plot_vectors()

    if i!=0:
        print()
        input("Press ENTER to continue.")

def printMenu():
    for i in range(100):
        print()
    for i in range(len(menuOptions)):
        print((" " if i<10 else "") + str(i) + "."+ menuOptions[i])
    print()

def run():
    repo=VectorRepository()
    i=-1
    while i!=0:
        printMenu()
        i=int(input("Give me option:"))
        handleMenu(repo,i)

run()