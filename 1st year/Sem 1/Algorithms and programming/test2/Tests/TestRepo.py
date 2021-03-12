import unittest
from domain.logic import Student
from infrastructure.repository import Repository
class Test(unittest.TestCase):
    def test_c(self):
        l = Repository()
        a = Student("aabc", 100)
        b = Student("aabsdsdsd", 300)
        c = Student("z", 300)
        l.add(a)
        l.add(b)
        l.add(c)
        self.assertEqual(l.highestGrade("aab"),300)
        l = Repository()
        a = Student("aabc", 100)
        b = Student("aabsdsdsd", 300)
        c = Student("z", 300)
        l.add(a)
        l.add(b)
        l.add(c)
        self.assertEqual(l.highestGrade("cccc"),"Wrong")
    def test_deleteStudents(self):
        l = Repository()
        a = Student("Alex", 100)
        b = Student("Daniel", 200)
        c = Student("Andrei", 300)
        l.add(a)
        l.add(b)
        l.add(c)
        self.assertEqual(l.deleteStudents(200),"Daniel 200"+"\n"+"Andrei 300"+"\n")

Test()