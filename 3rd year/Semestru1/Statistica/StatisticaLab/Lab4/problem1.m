function f = problem1()
distance=[5.2,4.4,3.0,1.2,7.5,9.4];
damage=[36.2,28.8,22.6,8.8,41.5,25.4];
%a
scatter(distance,damage)
%b
covariance=cov(distance,damage);
covariance
corelation=corrcoef(distance,damage);
corelation
%c
p = polyfit(distance,damage,1);
sum=0;
alpha=p(1)
beta=p(2)
for k=1:6
    sum=sum+(alpha*distance(k)+beta-damage(k))^2;
end
sum
xi=linspace(min(distance),max(distance))
yi=alpha*xi+beta;
hold on
plot(xi,yi,'b')
% yCalc1 = p(1)*distance;
% hold on
% plot(distance,yCalc1)