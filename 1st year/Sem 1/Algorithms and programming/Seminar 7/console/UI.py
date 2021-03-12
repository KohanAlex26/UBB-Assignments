class BookUI:
    def __init__(self,controler):
        self.__controler=controler
    @staticmethod
    def printMenu():
        print("Hello user!")
        print("These are the current avabile options:")
        # for i in Menu:
        #     print("1.)
    BookUI.printMenu()
    def controlOfUI(self):
        inputi=-1
        while inputi!=0:
            inputi=input()
            if inputi==1:
                title=input("insert title:")
                author=input("insert author:")
                code=input("insert code:")
                year=input("insert year:")
                price=input("insert price:")
                self.__controler.createBook(code,author,title,year,price)
