class MyVector():
	def __init__(self,n,c,t,v):
		self.__name_id=n
		self.__colour=c
		self.__type=t
		self.__values=v
	def getname(self):
		return self.__name_id
	def getcolour(self):
		return self.__colour
	def gettype(self):
		return self.__type
	def getvalues(self):
		return self.__values
	def setname(self,n):
		self.__name_id=n
	def setcolour(self,c):
		if c!='r' and c!='g' and c!='b' and c!='y' and c!='m':
			raise ValueError("Invalid colour")
		else:
			self.__colour=c
	def settype(self,t):
		self.__type=t
	def setvalues(self,v):
		self.__values=v
	def setp(self,x,y,z):
		self.__name_id=x
		self.__colour=y
		self.__type=z
	def __str__(self):
		s=""
		s=s+"Name: "+str(self.__name_id)+" of colour "+self.__colour+", of type "+str(self.__type)+" is "+str(self.__values)
		return s
	def addscalar(self,s):
		for i in range(len(self.__values)):
			self.__values[i]+=s
	def addtwovectors(self,v):
		z=[]
		for i in range(len(self.__values)):
			z.append(self.__values[i]+v[i])
		return z
	def substracttwovectors(self,v):
		z=[]
		for i in range(len(self.__values)):
			z.append(self.__values[i]-v[i])
		return z
	def multiplytwovectors(self,v):
		z=[]
		for i in range(len(self.__values)):
			z.append(self.__values[i]*v[i])
		return z
	def suminvector(self):
		s=0
		for elem in self.__values:
			s+=elem
		return s
	def productinvector(self):
		p=1
		for elem in self.__values:
			p*=elem
	def minimum(self):
		mn=self.__values[0]
		for i in range(1,len(self.__values)):
			if self.__values[i]<mn:
				mn=self.__values[i]
		return mn
	def maximum(self):
		mx=self.__values[0]
		for i in range(1,len(self.__values)):
			if self.__values[i]>mx:
				mx=self.__values[i]
		return mx


# a=MyVector("nr_1","red",1,[1,2,3])
# print(a.maximum())
# print(a.addtwovectors([1,2,3]))
# a.addscalar(2)
# print(a)
# try:
# 	s=""
# 	a=int(input("give me a:"))
# 	s=s+a
# except ValueError as e:
# 	print(e)