function f = problem2()
cigaretteconsumption=[480,500,380,1100,1100,230,490,250,300,510,1300]
deaths=[180,150,170,350,460,60,240,90,110,250,200]
%a
p = polyfit(cigaretteconsumption,deaths,1);
sum=0;
alpha=p(1)
beta=p(2)
for k=1:11
    sum=sum+(alpha*cigaretteconsumption(k)+beta-deaths(k))^2;
end
%yCalc1 = p(1)*cigaretteconsumption;
%hold on
%plot(cigaretteconsumption,yCalc1)
sum

%optional
alpha=p(1)
beta=p(2)
scatter(cigaretteconsumption,deaths)
xi=linspace(min(cigaretteconsumption),max(cigaretteconsumption))
yi=alpha*xi+beta;
hold on
plot(xi,yi,'b')
%


%b
yfit=p(1) * 600 + p(2)
yfit=p(1) * 850 + p(2)
yfit=p(1) * 1000 + p(2)