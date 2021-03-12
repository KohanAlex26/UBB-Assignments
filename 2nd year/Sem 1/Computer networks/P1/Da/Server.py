import socket
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0",7777))

print("Server open")
while True:
    data,addr=s.recvfrom(60)
    msg=data.decode()
    print(str(addr)+" : "+str(msg))
    ans=msg+msg
    s.sendto(ans.encode(),addr)
