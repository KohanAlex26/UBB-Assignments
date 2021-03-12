class Book:
    def __init__(self,code,listAuthor,title,year,price):
        self.__bCode=code
        self.__bListAuthor=listAuthor
        self.__bTitle=title
        self.__bYear=year
        self.__bPrice=price
    def getCode(self):
        return self.__bCode
    def setCode(self,newCode):
        self.__bCode=newCode
    def getAuthor(self):
        return self.__bListAuthor
    def setAuthor(self,newAuthor):
        self.__bListAuthor=newAuthor
    def getTitle(self):
        return self.__bTitle
    def setTitle(self,newTitle):
        self.__bTitle=newTitle
    def getYear(self):
        return self.__bYear
    def setYear(self,newYear):
        self.__bYear=newYear
    def getPrice(self):
        return self.__bPrice
    def setPrice(self,newPrice):
        self.__bPrice=newPrice
    def __str__(self):
        return "Code: "+ str(self.__bCode)+ " with authors: "+str(self.__bListAuthor)+ " of title "+self.__bTitle+" in year: "+ str(self.__bYear)+" costs "+ str(self.__bPrice)