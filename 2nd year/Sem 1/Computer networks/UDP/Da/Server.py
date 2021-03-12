#import library socket
import socket
#create a socket
#AF_INET - type of adress(IPv4 in this case) but it exists IPv6 too
#SOCK_DGRAM is for UDP protocol
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
#bind() assigns a socket to an address
#we use "0.0.0.0" to bind our socket to all addresses
#In this case it is not bound to a specific IP address
# but will be able to receive data send to any IP address of the machine
#7777 is the port
#When data arrives at a device, the network software
# looks at the port number and sends it to the right program
s.bind(("0.0.0.0",7777))

print("Server open")
while True:
    #in the data we have the message
    #in addr we have the address of the one who sends data
    #recvfrom is the function to recieve data
    #60 is the number of bytes to get
    data,addr=s.recvfrom(60)
    msg=data.decode()
    print(str(addr)+" : "+str(msg))
    ans=input("Answer: ")
    #sendto is used to send data
    s.sendto(ans.encode(),addr)
