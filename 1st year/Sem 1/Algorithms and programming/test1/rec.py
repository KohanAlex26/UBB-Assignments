def product(list):
    if len(list)==0:
        return 1
    else:
        return list[0]*product(list[1:])

print(product([1,2,3,4,5,6]))