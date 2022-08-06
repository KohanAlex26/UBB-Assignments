function [x,y]=Binomial_Grafic(n,p)

for k=1:n
    x(k)=pdf('bino',k,n,p)
    y(k)=cdf('bino',k,n,p)
end
figure(1)
plot(x)
title('PDF')

figure(2)
plot(y)
title('CDF')