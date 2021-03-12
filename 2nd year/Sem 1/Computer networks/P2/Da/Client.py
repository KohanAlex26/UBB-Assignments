import socket

while True:
    msg1=input("Message:")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("127.0.0.1", 7777))
    s.send(msg1.encode())

    msg2 = input("Message:")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("127.0.0.1", 7777))
    s.send(msg2.encode())

    print(s.recv(120).decode())
    s.close()
