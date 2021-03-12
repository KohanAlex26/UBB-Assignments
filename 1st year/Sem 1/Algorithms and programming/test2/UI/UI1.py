from domain.logic import Student
from infrastructure.repository import Repository
menu=["Exit","Add","String","Delete"]
def read_Student():
    name=input("Give me name: ")
    grade=int(input("Give me grade: "))
    return Student(name,grade)
def display_menu():
    # for i in range(100):
    print()
    for i in range(len(menu)):
        print((" " if i < 10 else "") + str(i) + ". " + menu[i])
    print()
def menu_option(option, repo):
    if option == 0:
        print("Quitting.")
    elif option == 1:
        repo.add(read_Student())
    elif option == 2:
        str=input("Give me str: ")
        print(repo.highestGrade(str))
    elif option==3:
        mn=int(input("Give me mn: "))
        print(repo.deleteStudents(mn))
def run():
    repo = Repository()
    option = -1

    while option != 0:
        display_menu()
        option = int(input("Option: "))
        menu_option(option, repo)

run()
