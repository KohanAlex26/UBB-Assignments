import socket
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0",7777))

print("Server open")
while True:
    data,addr=s.recvfrom(60)
    msg1=int(data.decode())
    print(str(addr)+" connected")

    data, addr = s.recvfrom(60)
    msg2 = int(data.decode())
    print(str(addr) + " connected")

    ans=msg1+msg2
    s.sendto(str(ans).encode(),addr)
