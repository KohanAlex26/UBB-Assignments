from domain.logic import Employee
from infrastructure.repo import EmployeeRepo
class Controller():
    def __init__(self,repo):
        self.repo=repo
    def returnList(self):
        return self.repo
    def __str__(self):
        return str(self.repo)
