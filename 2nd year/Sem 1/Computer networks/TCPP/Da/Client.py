import socket

while True:
    a=input("Press Enter to send message")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("127.0.0.1", 7777))
    s.send("Salut".encode())
    print(s.recv(120).decode())
    s.close()
