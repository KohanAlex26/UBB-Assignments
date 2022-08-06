function f = subjectA()
x=[20 21 22 23 24 25 26 27;
    2 1 3 6 5 9 2 2]
y=[75 76 77 78 79 80 81 82;
    3 2 2 5 8 8 1 1]
%a
m1=mean(x)
m2=mean(y)
%b
v1=var(x)
v2=var(y)
%c
c1=cov(x)
c2=cov(y)
%d
corrcoef1=corrcoef(x)
corrcoef2=corrcoef(y)
