# # import unittest
# # class TestRepo(unnittest.TestCase):
# #     v1=MyVector([5,3,7],1)
# #     v2=MyVector([1,2,3],2)
# #     r=repoVector()
# #     assert(s.avgAllVect()==0)
# #         r.add(v1)
# #         r.add(v2)
# #     assert(r.avgAllVect()==3.5)
# #
# #
# #     s=0
# #     for a,b in zip(self.elem,other.elem):
# #         s=s+a+b
# #     return s
# #
# # s=0
# # for t in zip(self.elem,other.elen):
# #     s=s+sum(t)
# # return s
# a=[1,2,3]
# l1=[1,2,3]
# l2=[1,2,3]
# l3=[]
# l4=[]
# # s=0
# # s=s+val:for val in a
# # return s
#
#
# l3=[a+b for a,b in zip(l1,l2)]
# l4=[sum(t) for t in zip(l1,l2)]
# print(l4)
# # l3=[sum(t): for t in zip(l1,l2)]
#
# l5=[]
# def sum(l1,l2):
#     l5=l2
#     for el in l1:
#         if (not el in l5):
#             l5.append(el)
#     return l5
# def reun(l1,l2):
#     if l1==[]and l2==[]:
#         return []
#     else:
#         if l1![]
#             if l1[0] in l2:
#                 return reun(l1[1:],l2)
#             else:
#                 return [l,[0]]+reun(l1[1:],l2)
#         else:
def sum():
    a=2
    b=3
    yield a+b
    b=3
    c=3
    yield b+c
# def gener2(n):
#     for i in range(2,n+1):
#         if isPrime(i):
#             yield i
# print(sum())
# def contr2():
#     n=int(input("give me n:"))
#     l=[next(genePr(n))]

for value in sum():
    print(value)
