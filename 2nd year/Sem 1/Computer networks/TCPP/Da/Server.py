import socket
#SOCK_STREAM is for TCP protocol
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
#we need to use listen for the server to listen for a connection of a client
s.listen(5)
while True:
    #Accept a connection
    #cs is a socket
    #addr is an address
    cs, addr = s.accept()
    print("Accepted a connection request")
    #we recieve data from a client
    #120 bytes of data
    b=cs.recv(120)
    print(str(addr)+" : "+b.decode())
    # send data to a client
    cs.send("Hello".encode())
    #close the socket
    cs.close()