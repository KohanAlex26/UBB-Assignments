 clc
 clear
 close all
 
 
 x=0:0.05:3;
 
 [y1,y2,y3]=Functii(x);
 
 figure(1)
 plot(x,y1,'Color',[1,1,0],'LineWidth',3,'LineStyle',':')
 title('Grafic')
 xlabel('x')
 ylabel('f1(x)')
 
 figure(2)
 hold on
 box on
 plot(x,y1)
 plot(x,y2)
 legend('gra1f','graf2')
 
 figure(3)
 subplot(2,3,1)
 plot(x,y1)
 subplot(2,3,2)
subplot(2,3,3)
plot(x,y2)
subplot(2,3,4)
subplot(2,3,5)
 plot(x,y3)
 subplot(2,3,6)
 plot(x,y3)
 
 
  figure(4)
 subplot(1,3,1)
 plot(x,y1)
 subplot(1,3,2)
subplot(1,3,3)
plot(x,y2)

  figure(5)
 subplot(3,1,1)
 plot(x,y1)
 subplot(3,1,2)
subplot(3,1,3)
plot(x,y2)
 
 
 
  
 