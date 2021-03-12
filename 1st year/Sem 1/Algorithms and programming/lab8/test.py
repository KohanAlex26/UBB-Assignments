# import matplotlib.pyplot as plt
# x=[1,2]
# y=[2,3]
# plt.axes()

# circle = plt.Circle((0, 0), radius=0.75, fc='y')
# plt.gca().add_patch(circle)
# plt.plot(10,20,"ro")
# plt.plot(30,50,"bs")
# plt.plot(100,200,"g^")
# # plt.axis('scaled')
# plt.show()
# rectangle = plt.Rectangle((10, 10), 100, 100, fc='r')
# plt.gca().add_patch(rectangle)
# plt.plot([1,5,4,2],marker="^")
# plt.show()
# import numpy as np
# values=[1.2,2,3]
# c=np.array(values)
# print(c)
# print(c*9 / 5 + 32)
# print(type(c))
def pascal(n):
    if n==1:
        return [1]
    else:
        p=pascal(n-1)
        print("p=",p)
        l=[1]
        for i in range(len(p)-1):
            l.append(p[i]+p[i+1])
        l.append(1)
        print("l=",l)
        return l
print(pascal(4))