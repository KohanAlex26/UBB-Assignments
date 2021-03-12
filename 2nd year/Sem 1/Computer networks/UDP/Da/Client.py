import socket

#the ip "127.0.0.1" is our ip
ip="127.0.0.1"
#we match the port with the server
port=7777
#we create a socket because a communication realised between at least two sockets
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

while True:
    msg=input("Message:")
    #send data to the server
    #we specify the ip and port of our server
    s.sendto(msg.encode(),(ip,port))
    #we recieve data from server
    ans,addr=s.recvfrom(60)
    print(str(addr)+" : "+ans.decode())
