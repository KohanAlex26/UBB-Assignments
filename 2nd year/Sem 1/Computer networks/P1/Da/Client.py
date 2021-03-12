import socket

ip="127.0.0.1"
port=7777
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

while True:
    msg=input("Message:")
    s.sendto(msg.encode(),(ip,port))
    ans,addr=s.recvfrom(60)
    print(str(addr)+" : "+ans.decode())
