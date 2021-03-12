def quicksort(a, low, hi):
    if low<hi:
        p = partition(a, low, hi)
        quicksort(a, low, p - 1)
        quicksort(a, p + 1, hi)
def getPivot(a,low,hi):
    mid=(low+hi)//2
    pivot=hi
    if a[low]<a[mid] and a[mid]<a[hi]:
        pivot=mid
    elif a[hi]<a[mid] and a[mid]<a[low]:
        pivot=mid
    elif a[mid]<a[low] and a[low]<a[hi]:
        pivot=low
    elif a[hi]<a[low] and a[low]<a[mid]:
        pivot=low
    # print(low,pivot,hi)
    print(a)
    return pivot


def partition(a, low, hi):
    pivotIndex=getPivot(a,low,hi)
    pivotValue=a[pivotIndex]
    a[low],a[pivotIndex]=a[pivotIndex],a[low]
    border=low

    for i in range(low,hi+1):
        if a[i]<pivotValue:
            border+=1
            a[border],a[i]=a[i],a[border]
    a[border],a[low]=a[low],a[border]

    return border

quicksort([17,41,5,22,54,6,29,3,13],0,8)