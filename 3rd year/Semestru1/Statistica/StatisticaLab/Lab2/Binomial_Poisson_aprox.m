function [x,y] = Binomial_Poisson_aprox(n,p)

for k = 1:n
    x(k) = pdf('poiss',k,n*p)
    y(k) = pdf('bino',k,n,p)
end
plot(x,'g^-')
title('Grafic')
hold on
plot(y,'bx-')
legend('poiss','bino')