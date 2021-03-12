def run():
    repo=BookRepository()
    contr=BookControler(repo)
    UI=BookUI(contr)
    UI.controlerOfUI()
class BookTest(unittest.TestCase):
    def sestUp(self):
    def test
    def setUp(self):
        self.__book=Book(1,["Ioan Slavici"],"Moara cu noroc",1818,13)
    def testCreate(self):
        self.assertEqual(self.__book.getTitle(),"Moara cu noroc")
    if __name__="__main__"
        unittest.main()