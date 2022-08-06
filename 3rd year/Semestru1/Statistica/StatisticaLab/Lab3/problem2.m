function f = problem2()
data = [47;55;18;24;27;41;50;38;33;29;15;77;64;22;19;35;39;41;67;55;121;77;80;34;41;48;60;30;22;28;84;55;26;105;62;30;17;23;31;28;56;64;88;104;115;39;25;18;21;30;57;40;38;29;19;46;40;49;72;70;37;39;18;22;29;52;94;86;23;36];
vect = sort(data,1)
%a
%5 class intervals
l = (vect(size(vect,1))-vect(1))/5
x = 15:l:121;
ncount = histc(data,x)
relativefreq = ncount/length(data)
bar(x-l/2, relativefreq,1,"green")
xlim([min(x) max(x)])
set(gca, 'xtick', x)
hold on


%b
%10 class intervals
figure(2)
l = (vect(size(vect,1))-vect(1))/10
x = 15:l:121;
ncount = histc(data,x)
relativefreq = ncount/length(data)
bar(x-l/2, relativefreq,1,"yellow")
xlim([min(x) max(x)])
set(gca, 'xtick', x)



%c
figure(3)
n = 1 + (10/3)*log10(size(data,1))
vect(size(vect,1))
vect(1)
l = vect(size(vect,1))-vect(1)
l = l/round(n)
x = 15:l:121;
ncount = histc(data,x)
relativefreq = ncount/length(data)
bar(x-l/2, relativefreq,1,"red")
xlim([min(x) max(x)])
set(gca, 'xtick', x)
%I think the frequency with 10 class intervals is more informative because
%it contains more intervals



