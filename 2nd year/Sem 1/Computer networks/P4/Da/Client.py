import socket

while True:
    msg=input("Write something: ")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("127.0.0.1", 7777))
    s.send(msg.encode())
    print(s.recv(120).decode())
    s.close()
