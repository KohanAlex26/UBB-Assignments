function [x,y] = Binomial_Normal_aprox(n,p)

for k = 1:n
    x(k) = pdf('norm',k,n*p,sqrt(n*p*(1-p)))
    y(k) = pdf('bino',k,n,p)
end
plot(x,'g^-')
title('Grafic')
hold on
plot(y,'bx-')
legend('norm','bino')