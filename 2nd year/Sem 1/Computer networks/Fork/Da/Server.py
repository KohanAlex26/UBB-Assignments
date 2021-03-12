import time
import socket
from threading import Thread
def f(cs,i):
 print ("Procesez client"+str(i))
 b=cs.recv(10)
 time.sleep(10)
 cs.send(str(i).encode())
 cs.close()
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
s.listen(5)
i=0
while (1==1):
 i=i+1
 cs,addr=s.accept()
 t=Thread(target=f,args=(cs,i,))
 t.start()
import socket
import os
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
s.listen(5)
while True:
    cs, addr = s.accept()
    print(os.fork())
    if os.fork()==0:
        b=cs.recv(120)
        print(b)
        cs.send("Hello")
        cs.close()
        os._exit(0)
    print("se asteapta conexiune")


    # print("Accepted a connection request")
    # b=cs.recv(120)
    # print(str(addr)+" : "+b.decode())
    # cs.send("Hello".encode())
    # cs.close()