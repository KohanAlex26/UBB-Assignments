import socket
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
s.listen(5)
def sum(x):
    s=0
    while x!=0:
        s=s+x%10
        x=int(x/10)
    return s
while True:
    cs, addr = s.accept()
    ip,port = addr
    print("Accepted a connection request")
    b=cs.recv(120)
    print(str(addr)+" : "+b.decode())
    cs.send(str(sum(port)).encode())
    cs.close()