from domain.logic import MyPoint
from math import sqrt
import matplotlib.pyplot as plt
class PointRepository():
	def __init__(self):
		p1=MyPoint(1,1,"blue")
		p2=MyPoint(1,2,"red")
		p3=MyPoint(2,2,"green")
		p4=MyPoint(2,1,"yellow")
		p5=MyPoint(5,5,"blue")
		p6=MyPoint(6,6,"blue")
		self.__l=[]
		self.__l.append(p1)
		self.__l.append(p2)
		self.__l.append(p3)
		self.__l.append(p4)
		self.__l.append(p5)
		self.__l.append(p6)

	def addp(self,p):
		self.__l.append(p)
	def getPoints(self):
		return self.__l
	def pointAtIndex(self,i):
		return self.__l[i]
	def pointsAtColour(self,c):
		list=[]
		for i in range(len(self.__l)):
			if self.__l[i].getcolour()==c:
				list.append(self.__l[i])
		return list
	def pointsInsideSquare(self,x,y,l):
		list=[]
		for i in range(len(self.__l)):
			if self.__l[i].getx()>x and self.__l[i].getx()<x+l and self.__l[i].gety()<y and self.__l[i].gety()>y-l:
				list.append(self.__l[i])
		return list
	def pointsInsideRectangle(self,x,y,l,w):
		list=[]
		for i in range(len(self.__l)):
			if self.__l[i].getx()>x and self.__l[i].getx()<x+l and self.__l[i].gety()<y and self.__l[i].gety()>y-w:
				list.append(self.__l[i])
		return list
	def pointsInsideCricle(self,x,y,r):
		list=[]
		for i in range(len(self.__l)):
			if sqrt((self.__l[i].getx()-x)**2+(self.__l[i].gety()-y)**2)<r:
				list.append(self.__l[i])
		return list
	def minDistance(self):
		mind=-1
		for i in range(len(self.__l)-1):
			for j in range(i+1,len(self.__l)):
				if mind==-1:
					mind=sqrt((self.__l[j].getx()-self.__l[i].getx())**2+(self.__l[j].gety()-self.__l[i].gety())**2)
				else:
					z=sqrt((self.__l[j].getx()-self.__l[i].getx())**2+(self.__l[j].gety()-self.__l[i].gety())**2)
					if z<mind:
						mind=z
		return mind
	def maxDistance(self):
		maxd=-1
		for i in range(len(self.__l)-1):
			for j in range(i+1,len(self.__l)):
				if maxd==-1:
					maxd=sqrt((self.__l[j].getx()-self.__l[i].getx())**2+(self.__l[j].gety()-self.__l[i].gety())**2)
				else:
					z=sqrt((self.__l[j].getx()-self.__l[i].getx())**2+(self.__l[j].gety()-self.__l[i].gety())**2)
					if z>maxd:
						maxd=z
		return maxd
	def setpoint(self,i,p):
		self.__l[i]=p
	def updateColour(self,x,y,c):
		i=0
		while i<len(self.__l) and self.__l[i].getx()!=x and self.__l[i].gety()!=y:
				i=i+1
		self.__l[i].setz(c)
	def shiftOnX(self):
		for i in range(len(self.__l)):
			self.__l[i].sety(0)
	def shiftOnY(self):
		for i in range(len(self.__l)):
			self.__l[i].setx(0)
	def delPointByIndex(self,i):
		del self.__l[i]
	def delPointByCoord(self,x,y):
		i = 0
		while i < len(self.__l) and self.__l[i].getx() != x and self.__l[i].gety() != y:
				i = i + 1
		del self.__l[i]
	def delInsideSquare(self,x,y,l):
		i=0
		while i<len(self.__l):
			if self.__l[i].getx()>x and self.__l[i].getx()<x+l and self.__l[i].gety()<y and self.__l[i].gety()>y-l:
				del self.__l[i]
			else:
				i=i+1
	def plot_points(self):
		for i in range(len(self.__l)):
			plt.scatter(self.__l[i].getx(),self.__l[i].gety(),c=self.__l[i].getcolour())
		plt.show()