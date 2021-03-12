class ui():
    def __init__(self,controller):
        self.controller=controller
    @staticmethod
    def print_menu():
        menu=["a","b","c","d","e","f","g","h","i","j","k"]
        # for i in range(100):
        #     print()
        for i in range(len(menu)):
            print((" " if i<10 else "")+str(i)+"."+menu[i])
    def handle_menu(self):
        while True:
            try:
                    ui.print_menu()
                    o=int(input("give me option"))
                    if o==0:
                        print("bye")
                        break
                    elif o==1:
                        print(2)
                    else:
                        print("not good")
            except ValueError as e:
                print(e)
controller=0
ui=ui(controller)
ui.handle_menu()

class controller():
    def __init__(self,repo):
        self.repo=repo
    def createVector(self):
        a=Myvector()
        self.repo.add(a)

# repo=vecRepo()
# controller=controller(repo)
# ui=ui(controller)
# ui.handle_menu()
def MySearch(l,f):
    a=[]
    for elem in l:
        if f(elem)==True:
            a.append(elem)
    return a
def serachPlanes(l):
    def f(x):
        if x.getlast()=="a":
            return True
        return False
    return MySearch(l.passengers())

def bubbleSort(l,f):
    ok=False
    while ok==False:
        ok=True
        for i in range(len(l)-1):
            if f(l[i],l[i+1])==False:
                l[i],l[i+1]=l[i+1],l[i]
                ok=False
def sort(l,str):
    def f(x,y):
        if x.getlast()<=
    bubbleSort(l.getobject().getlist().getpassengers(),f)