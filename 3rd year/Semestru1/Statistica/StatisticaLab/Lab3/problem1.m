function f = problem1()
data = [2;2;0;0;5;8;3;4;1;0;0;7;1;7;1;5;4;0;4;0;1;8;9;7;0;1;7;2;5;5;4;3;3;0;0;2;5;1;3;0;1;0;2;4;5;0;5;7;5;1];
%a
table=tabulate(data);
v = table(:, 1)
f = table(:, 2)
fhat = table(:, 3)
%b
bar(v, fhat)
hold on
bar(v, fhat, 0), hold on, plot(v, fhat,'o')
%c
plot(v, fhat), hold on, plot(v, fhat,'*')
%d
figure(2)
freq=cumsum(fhat)
plot(freq)

