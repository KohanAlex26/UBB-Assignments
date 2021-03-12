def sum(l):
    if len(l)==0:
        return 0
    elif len(l)==1:
        return l[0]
    return sum(l[2:])+l[0]-l[1]
print(sum([2,1,3,2,4]))