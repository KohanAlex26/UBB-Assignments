import socket
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
s.listen(5)
while True:
    cs, addr = s.accept()
    print("Accepted a connection request")
    b=cs.recv(120)
    msg=b.decode();

    cs, addr = s.accept()
    b = cs.recv(120)
    msg=msg+b.decode();

    cs.send(msg.encode())
    cs.close()