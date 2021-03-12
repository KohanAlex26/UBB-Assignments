class MyPoint():
	def __init__(self,x,y,z):
		self.__x=x
		self.__y=y
		self.__z=z
	def getx(self):
		return self.__x
	def gety(self):
		return self.__y
	def getcolour(self):
		return self.__z
	def setx(self,x):
		self.__x=x
	def sety(self,y):
		self.__y=y
	def setz(self,z):
		if z=="red" or z=="blue" or z=="green":
			self.__z=z
		else:
			raise ValueError("Colour "+ z + " not allowed")
		self.__z=z
	def setp(self,x,y,z):
		self.__x=x
		self.__y=y
		self.__z=z
	def __str__(self):
		s=""
		s=s+"Point ("+str(self.__x)+","+str(self.__y)+") of colour "+self.__z
		return s