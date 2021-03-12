import math
import random
# file=open("read.txt","w")
# l1="abc"
# l2="def"
# l3="ghi"
# l4="\n"

# l="abc","\n","dee"
# file.write('{}\n{}\n{}\n'.format(l1,l2,l3))
# file.writelines([l1,l4,l2,l4,l3])
# s=""
# for employee in file.readlines():
#     s=s+str(employee)
# print(s)
# file.close()
# class MyVector():
# 	def __init__(self,n,c,t,v):
# 		self.__name_id=n
# 		self.__colour=c
# 		self.__type=t
# 		self.__values=v
# 	def getname(self):
# 		return self.__name_id
# 	def getcolour(self):
# 		return self.__colour
# 	def gettype(self):
# 		return self.__type
# 	def getvalues(self):
# 		return self.__values
# 	def setname(self,n):
# 		self.__name_id=n
# 	def setcolour(self,c):
# 		if c!='r' and c!='g' and c!='b' and c!='y' and c!='m':
# 			raise ValueError("Invalid colour")
# 		else:
# 			self.__colour=c
# 	def settype(self,t):
# 		self.__type=t
# 	def setvalues(self,v):
# 		self.__values=v
# 	def setp(self,x,y,z):
# 		self.__name_id=x
# 		self.__colour=y
# 		self.__type=z
# 	def getMyVector(self):
# 		s=""
# 		s=s+"Name: "+str(self.__name_id)+" of colour "+self.__colour+", of type "+str(self.__type)+" is "+str(self.__values)
# 		ret
# class MyVectorUtils(MyVector):
# 	def __init__(self,n,c,t,v,id):
# 		MyVector.__init__(self,n,c,t,v)
# 		self.number=id
# 	def getID(self):
# 		return self.getMyVector()+" , "+str(self.number)
#
# x=MyVector("alex","red",1,[1,2,3])
# y=MyVectorUtils("sel","blue",2,[6,7,9],45)
# print(x.getMyVector())
# print(y.getMyVector())
# print(y.getID())
# def isPrime(x):
# 	if x<0:
# 		raise ValueError("Need positive number!")
# 	else:
# 		if x<2:
# 			return 0
# 		if x==2:
# 			return 1
# 		if x%2==0:
# 			return 0
# 		d=3
# 		while d*d<=x:
# 			if x%d==0:
# 				return 0
# 			d=d+2
# 		return 1
#
# for i in range(-100,1):
# 	try:
# 		isPrime(i)
# 		assert False
# 	except ValueError as e:
# 		print("some errors: ", str(e),str(i))
# 		pass

# primes=[2,3,5,7,11,13,17,19]
# for i in range(-4,2):
# 	assert isPrime(i)==(i in primes),"this is the value where it fails: " + str(i)
# def division(a,b):
# 	if b==0:
# 		raise ZeroDivisionError("bai prost, nu imparti la 0")
# 	else:
# 		c=int(a/b)
# 		return c
#
# a=2
# b=0
# try:
# 	division(a,b)
# 	assert False
# except ZeroDivisionError as e:
# 	print(e)
# a=3
# b=0
# try:
# 	division(a,b)
# 	assert False
# except ZeroDivisionError as e:
# 	print(e)
# def isPrime(n):
# 	if n <= 0:
# 		raise ValueError("The number needs to be positive")
# 	if n == 1:
# 		return False
# 	if n <= 3:
# 		return True
# 	for i in range(2, n):
# 		if n % i == 0:
# 			return False
# 	return True
# from unittest import TestCase
# import unittest
# class IsPrimeBlackBoxTest(unittest.TestCase):
# 	def setUp(self):
# 		unittest.TestCase.setUp(self)
# 	def tearDown(self):
# 		unittest.TestCase.tearDown(self)
# 	def test_IsPrimeBlackBox(self):
# 		for i in range(-100, 0):
# 			try:
# 				isPrime(i)
# 				assert False
# 			except ValueError:
# 				assert True
# 	primes = [2, 3, 5, 7, 11, 13, 17, 19]
# 	for i in range(2, 20):
# 		self.assertTrue(isPrime(i) == (i in primes),"The value where it fails: " + str(i))
# class Person(object):
# 	def __init__(self, name, age):
# 		self.__name = name
# 		self.__age = age
# 	def __str__(self):
# 		return self.__name + " , " + str(self.__age)
# 	def getName(self):
# 		return self.__name
# 	def setName(self, name):
# 		self.__name = name
# 	def getAge(self):
# 		return self.__age
# 	def setAge(self, age):
# 		self.__age = age
# 	def incrementAge(self):
# 		self.__age += 1
# import unittest
# # from domain.Person import Person
# class PersonTest(unittest.TestCase):
# 	def setUp(self):
# 		self.person = Person("Simpson", 8)
# 	def test_IncrementAge(self):
# 		self.person.setAge(9)
# 		self.person.incrementAge()
# 		self.assertEqual(self.person.getAge(), 10)
# if __name__ == "__main__":
# 	unittest.main()

# s="1,2,3,4,5"
# a=s.split(",")
# for i in range(len(a)):
#     a[i]=int(a[i])
# print(a)
# a=[1,2,3]
# b=a[:]
# b.reverse()
# print(a,b)
