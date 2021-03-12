from tkinter import *
root=Tk()

myLabel1= Label(root, text="Hello World!")
myLabel2= Label(root,text="What's up?")
myButton=Button(root,text="Good")

myLabel1.pack()
myLabel2.pack()
myButton.pack()

root.mainloop()