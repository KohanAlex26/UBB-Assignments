from infrastructure.repo import BookRepository
from domain.ex import Book
class BookControler:
    def __init__(self,repository):
        self.__repository=repository
    def createBook(self,code,author,title,year,price):
        book=Book(code,author,title,year,price)
        self.__repository.addBook(book)
    def GetByYear(self,y):
        return self.__repository.getAllBooks(y)
    def deleteByAuthor(self,a):
        self.__repository.delByAuthor(a)
    def deleteByYears(self,y1,y2):
        self.__repository.deleteBooksBetween(y1,y2)
    def get2Authors(self):
        return self.__repository.getBooksby2()