from domain.logic import MyVector
import matplotlib.pyplot as plt
class VectorRepository():
	def __init__(self):
		v1 = MyVector("name1","b",1,[1,1])
		v2 = MyVector("name2", "b", 1, [2, 2, 3,6])
		v3 = MyVector("name3", "g", 2, [4, 5, 7,12])
		v4 = MyVector("name4", "y", 4, [9,10,12,15])
		self.__l=[]
		self.__l.append(v1)
		self.__l.append(v2)
		self.__l.append(v3)
		self.__l.append(v4)
	def addp(self, v):
		self.__l.append(v)
	def __str__(self):
		s=""
		for elem in self.__l:
			s=s+str(elem)+'\n'
		return s
	def getVectors(self):
		s=""
		for elem in self.__l:
			s=s+str(elem)+"\n"
		return s
	def vectorAtIndex(self,i):
		return self.__l[i]
	def updateVector(self,i,p):
		self.__l[i]=p
	def updateVectorByName(self,name_id,v):
		i=0
		while i<len(self.__l) and self.__l[i].getname()!=name_id:
			i=i+1
		self.__l[i]=v
	def deleteByIndex(self,index):
		del self.__l[index]
	def deleteByName(self,name_id):
		i = 0
		while i < len(self.__l) and self.__l[i].getname() != name_id:
			i = i + 1
		del self.__l[i]
	def plot_vectors(self):
		for i in range(len(self.__l)):
			if self.__l[i].gettype()==1:
				plt.plot(self.__l[i].getvalues()[0],self.__l[i].getvalues()[1], c=self.__l[i].getcolour()+"o")
			elif self.__l[i].gettype()==2:
				plt.plot(self.__l[i].getvalues()[0], self.__l[i].getvalues()[1], c=self.__l[i].getcolour()+"s")
			elif self.__l[i].gettype()==3:
				plt.plot(self.__l[i].getvalues()[0], self.__l[i].getvalues()[1], c=self.__l[i].getcolour()+"^")
			# else:
			# 	plt.plot(self.__l[i].getvalues()[0], self.__l[i].getvalues()[1], c=self.__l[i].getcolour() + "d")
		plt.show()
# for i in range(len(self.__l)):
# 	if self.__l[i].gettype() == 1:
# 	circle = plt.Circle((self.__l[i].getvalues()[0], self.__l[i].getvalues()[1]), radius=0.75, fc=self.__l[i].getcolour())
# 	plt.gca().add_patch(circle)
# 	plt.axis('scaled')
# elif self.__l[i].gettype()==2:
# 	rectangle = plt.Rectangle((self.__l[i].getvalues()[0], self.__l[i].getvalues()[1]), self.__l[i].getvalues()[2], self.__l[i].getvalues()[3], fc=self.__l[i].getcolour())
# 	plt.gca().add_patch(rectangle)
# 	plt.axis('scaled')
# elif self.__l[i].gettype()==3:
# 	points = [[2, 1], [8, 1], [8, 4]]
# 	polygon = plt.Polygon(points, fc=self.__l[i].getcolour())
# 	plt.gca().add_patch(polygon)
# 	plt.axis('scaled')
# else:
# 	points = [[2, 4], [2, 8], [4, 6],[6,8]]
# 	polygon = plt.Polygon(points, fc=self.__l[i].getcolour())
# 	plt.gca().add_patch(polygon)
# 	plt.axis('scaled')



# list=VectorRepository()
# a=MyVector("nr_1","red",1,[1,2,3])
# b=MyVector("nr_2","red",1,[3,5,4])
# list.addp(a)
# list.getVectors()
# list.addp(b)
# print(list)
# list.deleteByName("nr_1")
# print(list)
# import matplotlib.pyplot as plt
# x = [1, 2, 3]
# y = [1, 2, 3]
# col = ["red", "green", "blue"]
# plt.scatter(x, y, c=col)
# plt.show()
# x = [1, 2, 3]
# y = [1, 2, 3]
# col = ["red", "green", "blue"]
# plt.scatter(x, y, c=col)
# plt.show()
