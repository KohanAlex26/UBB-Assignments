from domain.ex import Book
class BookRepository():
    def __init__(self):
        self.__library={}
    def addBook(self,b):
        if b.getCode() in self.__library:
            raise ValueError("It already exists")
        else:
            self.__library[b.getCode()]=b
    def getAllBooks(self,year):
        years={}
        for key in self.__library:
            if self.__library[key].getYear()==year:
                years[key]=self.__library[key]
        return years
    def delByAuthor(self,author):
        for key in range(len(self.__library)-1,-1,-1):
            if author in self.__library[key].getAuthor():
                del self.__library[key]
    def deleteBooksBetween(self,y1,y2):
        # if y1<y2
        for i in range(len(self.__library)-1,-1,-1):
            if self.__library[i].getYear()>=y1 and self.__library[i].getYear()<=y2:
                del self.__library[i]
    def getBooksWithAtLeastTwoAuthors(self):
        books=[]
        for key in self.__library:
            if len(self.__library[key].getAuthor()>1):
                books.append(self.__library[key])
        return books
    def updateByCode(self,code,newBook):
        self.__library[code]=newBook
    def getAllBooksWithTitlesStartingWithASpecifiedLetter(self,t):
        books=[]
        for key in self.__library:
            if self.__library[key].getTitle()[0]==t:
                books.append(self.__library[key])
        return books
    def __str__(self):
        s=""
        for key in self.__library:
            s=s+str(self.__library[key])+"\n"
        return s

x=Book(0,["Ioan S","Daaa"],"Ruperea",1981,25)
y=Book(1,["Marin Preda","tuu"],"raaaaa",2941,230)
z=Book(2,["caa S","Dghhhha"],"Ruperea",3924,400)
a=Book(3,["Ifghvc","555"],"Ruperea",4962,500)



d={
    x.getCode(): x,
    y.getCode(): y,
    z.getCode(): z,
    a.getCode(): a
}
# print(d)
bookrepository=BookRepository()
bookrepository.addBook(x)
bookrepository.addBook(y)
bookrepository.addBook(z)
bookrepository.addBook(a)

print(bookrepository)
bookrepository.delByAuthor("Ioan S")
print(bookrepository)
