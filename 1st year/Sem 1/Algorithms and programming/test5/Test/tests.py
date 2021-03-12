import unittest
from domain.logic import Employee
from infrastructure.repo import EmployeeRepo
class test(unittest.TestCase):
    def test_setname(self):
        e=Employee("ana",400,"IT")
        try:
            e.setname("")
        except ValueError:
            print("Try again")
            assert True
        e=Employee("ana",400,"IT")
        e.setname("man")
        self.assertEqual(e.getname(),"man")
    def test_setsalary(self):
        e=Employee("ana",400,"IT")
        try:
            e.setsalary(-1)
        except ValueError:
            print("Try again")
            assert True
        e = Employee("ana", 400, "IT")
        e.setsalary(200)
        self.assertEqual(e.getsalary(),200)

    def test_highestpaidIT(self):
        repo=EmployeeRepo([Employee("Maria",4000,"IT"),Employee("Ion",4700,"IT"),Employee("Ana",5200,"IT")],[])
        self.assertEqual(repo.highestPaidIT(),Employee("Ana",5200,"IT"))
    def test_highestpaidHR(self):
        repo = EmployeeRepo([],[Employee("Vlad",3400,"HR"),Employee("Tudor",4300,"HR"),Employee("Carina",3100,"HR")])
        self.assertEqual(repo.highestPaidHR(),Employee("Tudor",4300,"HR"))