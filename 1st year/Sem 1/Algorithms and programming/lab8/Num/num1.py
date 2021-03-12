import numpy as np
def addscalar1():
    a=[1,2,3]
    c=np.array(a)
    c=c+2
    return c
def addtwovectors():
    a=[1,2,3]
    b=[1,2,3]
    c=np.array(a)
    d=np.array(b)
    print(c+d)
def substracttwovectors():
    a=[1,2,3]
    b=[1,2,3]
    c=np.array(a)
    d=np.array(b)
    print(c-d)
def multiplytwovectors():
    a=[1,2,3]
    b=[1,2,3]
    c=np.array(a)
    d=np.array(b)
    print(c*d)
def sumofelem():
    a=[1,2,3]
    c = np.array(a)
    print(sum(c))

def productofelem():
    a=[1,2,3,4]
    c = np.array(a)
    print(dot(a,b))
def maxofavector():
    a=[1,2,3]
    return max(a)
def minofavector():
    a=[1,2,3]
    return min(a)
maxofavector()