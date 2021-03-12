def MySearch(l):
    mx=0
    for elem in l:
        if elem.getsalary()>mx:
            mx=elem
    return mx
# def highestPaidIT(l):
#     def f(x):
#         return x.getsalary()
#     return MySearch(l.getlistIT(),f)
# def highestPaidHR(l):
#     def f(x):
#         mx=0
#         em=0
#         for i in range(l):
#             if l[i].getsalary()>mx:
#                 mx= l[i].getsalary()
#                 em=l[i]
#         return em
#     return MySearch(l.getlistHR(),f)