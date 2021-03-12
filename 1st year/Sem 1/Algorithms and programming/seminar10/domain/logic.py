# l=[5,4,1,2,3,7]
# def minlist(l):
#     min=99999
#     for el in l:
#         if el<min:
#             min=el
#     return min
# def test_minlist():
#     assert(minlist([5,4,1,2,3,7])==1)
#     assert(minlist([5,4,-1,3,1])==-1)
#
# test_minlist()

# def minListRec(l):
#     if l==[]:
#         return 99999
#     else:
#         minSlice=minListRec(l[1:])
#         if l[0]<minSlice:
#             return l[0]
#         else:
#             return minSlice
import math
list=[5,4,3,1,2]
# print(isinstance([1,2,3],list))
# def minListNested(list):
#     minval=99999
#     for el in list:
#         if(isinstance(el,list)==True):
#             val=minListNested(el)
#         else:
#             val=el
#         if val<minval:
#             minval=val
#     return minval
#
# # print(minListNested([5,3,[2,1,3],9,[5,2]]))
# def minListNestedRec(l):
#     if l==[]:
#         return 99999
#     else:
#         minSlice=minListRec(l[1:])
#         if isinstance(l[0],int) == True:
#             if l[0]<minSlice:
#                 return l[0]
#             else:
#                 return minSlice
#         else:
#             min0=minListNestedRec(l[0])
#             if min0<minSlice:
#                 return min0
#             else:
#                 return minSlice
#
# def minListNestedRec2(l):
#     if l==[]:
#         return 99999
#     else:
#         minSlice=minListNestedRec2(l[1:])
#         if isinstance(l[0],int):
#             min0=l[0]
#         else:
#             min0=minListNestedRec2(l[0])
#         if min0<minSlice:
#             return min0
#         else:
#             return minSlice
# def sumListNestedRec2(l):
#     if l==[]:
#         return 99999
#     else:
#         sumSlice=sumListNestedRec2(l[1:])
#         if isinstance(l[0],int):
#             sum0=l[0]
#         else:
#             sum0=sumListNestedRec2(l[0])
#         return sum0+sumSlice
#
# def search(l,x):
#     for i in range(0,len(l)):
#         if l[i]==x:
#             return i
#     return -1
#
# def test_search():
#     assert(search([5,1,3,4],5)==0)
#     assert(search([5,1,3,4],1)==1)
#     assert(search([5,1,3,4],4)==3)
#     assert(search([5,1,3,4],8)==-1)
#
# def binsearch(l,x):
#     if len(l)>0:
#         midPos=len(l)
#         if x==l[midPos]:
#             return midPos
#         elif x<l[midPos]:
#             return binsearch(l[:midpos],x)
#         else:
#             return binsearch(l[midpos+1:],x)
#     else:
#         return -1
# def myFilterEven(l):
#     aux=[]
#     for el in l:
#         if isEven(el)==True:
#             aux.append(el)
#     return aux
# def myfilterGen(l,cond):
#     aux=[]
#     for el in l:
#         if cond(el)==True:
#             aux.append(el)
#     return aux
# def test_myfilterGen():
#     assert(myfilterGen([5,2,7,4],myFilterEven)==[2,4])
#     assert(myfilterGen([5,2,7,4],isPrime)==[5,2,7])
#     assert(filter(isEven,(5,2,7,4))==[2,4])
#     assert(list(map(isEven,[5,2,7,4]))==[False,True,False,True])
# test_myfilterGen()
# def bubbleSort(l):
#     n=len(l)
#     k=1
#     isSort=False
#     while isSort==False:
#         isSort=True
#         for i in range(0,n-k):
#             if l[i]>l[i+1]:
#                 l[i],l[i+1]=l[i+1],l[i]
#                 isSort=False
#         k=k+1
#     return l
#
# print(bubbleSort([9,7,6,5]))
# def selectionSort(l):
#     aux=[]
#     n=len(l)
#     i=0
#     while i<n:
#         minvalPos=minlistpos(l)
#         aux.append(l[minvalPos])
#         del l[minvalPos]
#         i=i+1
#
# def selection2(l):
#     i=0;n=len(l)
#     while i<n:
#         minvalPos=minListPos(l[0:n-i])
#         minval=l[minvalPos]
#         del l[minvalPos]
#         l.append(minval)
#         i=i+1
#     return l
#
# l1=[1,2,3]
# l=[1,2,3]
# l2=[i,j for i,j in l1,l]
# print(l2)
# l=[1,2]
# l2=[3,4]
# l3=zip(l,l2)
# for e in l3:
#     print(e)
# def myzip(list1,list2):
#     r=[]
#     while list1!=[]:
#         r.append([list1[0],list2[0]])
#         list1,list2=list1[1:],list2[1:]
#     print(r)
# myzip([1,2,3],[1,2,3])
# def product(l):
#     if len(l)==1:
#         if l[0]%2==0:
#             return l[0]
#         else:
#             return 1
#     else:
#         m=len(l)//2
#         p1=product(l[0:m])
#         p2=product(l[m:])
#         return p1*p2
# print(product([1,2,3,4]))
def f(x,y):
    global a
    a=2
    b=3
    c=4
    x,y=y,x
    print(a,b,x,y)
a,b,x,y=11,22,33,44
f(19,31)
print(a,b,x,y)