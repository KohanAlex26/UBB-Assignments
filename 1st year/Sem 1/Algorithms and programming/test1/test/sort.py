# l=[5,1,3,7,4]
# def insertion(l):
#     for i in range(1,len(l)):
#         aux=l[i]
#         pos=i-1
#         while pos>=0 and aux<l[pos]:
#             l[pos+1]=l[pos]
#             pos=pos-1
#         l[pos+1]=aux
# insertion(l)
# print(l)
# def bubble(l):
#     ok=False
#     while ok==False:
#         ok=True
#         for i in range(len(l)-1):
#             if l[i]>l[i+1]:
#                 l[i],l[i+1]=l[i+1],l[i]
#                 ok=False
# bubble(l)
# print(l)
# print((lambda x:x**3)(2))

# a,b=1,2
# l=[a,b]
# a=7
# l2=[a]
# l.append(l2)
# l2[0]=8
# print(a,l2,l)

# def f(i):
#     return i+1
# def g(h):
#     return h(1)
# def h(x):
#     return x+10
# i=lambda x:x+2
# print(f(f(f(g(i)))))

# def f():
#     return 1
# def g(x=1):
#     return x+1
# def h(x=1,y=2):
#     return x+y
# l=[f,g,h]
# for e in l:
#     print(e())
# h=lambda x=1,y=2:x*y
# print(l[2](3),h(),h(3),h(x=3),h(y=3))
# print(h([2,3]))
# print(h(*[2,3]))

# def quicksort(l,low,hi):
#     return quicksort2(l,low,hi)
# def quicksort2(l,low,hi):
#     if low<hi:
#         p=partition(l,low,hi)
#         quicksort2(l,low,p-1)
#         quicksort2(l,p+1,hi)
# def getpivot(l,low,hi):
#     mid=(low+hi)//2
#     p=low
#     if l[low]<=l[mid] and l[mid]<=l[hi]:
#         p=mid
#     elif l[hi]<=l[mid] and l[mid]<=l[low]:
#         p=mid
#     elif l[low]<=l[hi] and l[hi]<=l[mid]:
#         p=hi
#     elif l[mid]<=l[hi] and l[hi]<=l[low]:
#         p=hi
#     return p
# def partition(l,low,hi):
#     p=getpivot(l,low,hi)
#     pivotvalue=l[p]
#     l[low],l[p]=l[p],l[low]
#     b=low
#     for i in range(low,hi+1):
#         if l[i]<pivotvalue:
#             b=b+1
#             l[b],l[i]=l[i],l[b]
#     l[low],l[b]=l[b],l[low]
#     return b
# l=[5,1,3,7,4]
# quicksort(l,0,4)
# print(l)

















def quicksort2(l,low,hi):
    if low<hi:
        p=partition(l,low,hi)
        quicksort2(l,low,p-1)
        quicksort2(l,p+1,hi)
def quicksort(l,low,hi):
    quicksort2(l,low,hi)
def getpivot(l,low,hi):
    mid=(low+hi)//2
    p=low
    if l[low]<=l[mid] and l[mid]<=l[hi]:
        p=mid
    elif l[hi]<=l[mid] and l[mid]<=l[low]:
        p=mid
    elif l[low]<=l[hi] and l[hi]<=l[mid]:
        p=hi
    elif l[mid]<=l[hi] and l[hi]<=l[low]:
        p=hi
    return p
def partition(l,low,hi):
    p=getpivot(l,low,hi)
    pivotvalue=l[p]
    l[low],l[p]=l[p],l[low]
    b=low

    for i in range(low,hi+1):
        if l[i]<pivotvalue:
            b=b+1
            l[b],l[i]=l[i],l[b]
    l[b],l[low]=l[low],l[b]
    return b
l=[5,1,3,7,4]
quicksort(l,0,4)
print(l)