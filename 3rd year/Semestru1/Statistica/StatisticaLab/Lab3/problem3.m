function f = problem3()
data = [1;9;7;7;10;8;10;6;6;8;6;9;5;6;7;6;6;6;7;7;10;8;7;1;10;9;8;8;6;10;7;8;3;7;6;8;6;4;5;8;10;8;8;9;6;9;5;5;6;7;10;6;3;6;6;6;10;7;6;2;6;4;10;4;8;7;8;4;7;9;8;9;10;8;2;5;8;6;10;4;4;10;7;6;3;7;7;8;1;10;10;7;7;7;10;3;5;5;8;8];
vect = sort(data,1)
%a
n = 1 + (10/3)*log10(size(data,1))
l = (vect(size(vect,1))-vect(1))/n
x = 1:l:10
ncount = histc(data,x)
relativefreq = ncount/length(data)
bar(x-l/2, relativefreq,1,"red")
xlim([min(x) max(x)])
set(gca, 'xtick', x)




%b
n = size(data,1)
M = median(data)
q1range = n*(1/4)
q2range = n*(1/2)
q3range = n*(3/4)
tbl = tabulate(data)
t = array2table(tbl,'VariableNames',{'Value','Count','Percent'})

b=cumsum(t.Count, 1)
i1 = 0
i2 = 0
i3 = 0
for i = 1:size(b,1)
    if(b(i)>q1range)&&(i1==0)
        i1 = i
    end

    if(b(i)>q2range)&&(i2==0)
        i2 = i
    end

    if(b(i)>q3range)&&(i3==0)
        i3 = i
    end
end

t.Value(i1)
t.Value(i2)
t.Value(i3)
inqr=iqr(data)
inqdev=inqr/2
capat1=t.Value(i1)-(3/2)*inqr
capat2=t.Value(i3)+(3/2)*inqr

i=1
while(t.Value(i)<capat1)&&(i<=size(t.Value,1))
    t.Value(i)
    i = i+1
end

i=1
while(t.Value(i)>capat2)&&(i<=size(t.Value,1))
    t.Values(i)
    i = i+1
end




%c
sum_data = 0
for i = 1:size(t.Value)
    sum_data = sum_data + t.Value(i)^2 * t.Count(i)
end
moment2 = sum_data/size(data,1)
var(data)
var_coeff = sqrt(var(data))/mean(data)




%d
boxplot(data)
