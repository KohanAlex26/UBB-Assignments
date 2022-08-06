function f = problem3()
clc
clear

%Bernoulli
p=0.35;
S=100000;
x=rand(1,S);
y=(x<p);
fr_ap_1=sum(y)


fr_ap_0=S-fr_ap_1
fprintf('Bernoulli\n')
disp([fr_ap_0,fr_ap_1]/S)

%Binomial
n=3;
a=rand(n,S)
b=(a<p)
z=sum(b)


vect_fr=zeros(1,n+1);
uz=unique(z);
for i=1:n+1
   vect_fr(i)=sum(z==uz(i));
end
fprintf('Binomial\n')
disp(vect_fr/S)
disp(pdf('bino',uz,n,p))

%Geometric
G=zeros(1,S);
for i=1:S
    while rand>=p
        G(i)=G(i)+1;
    end
end
G
fprintf('Geometric\n')
unique(G)

%Pascal
%a=rand([1,10])
% x = (0:10);
% y = nbinpdf(x,3,0.5);
% figure;
% plot(x,y,'+')
% xlim([-0.5,10.5])
b=zeros(n,S);
