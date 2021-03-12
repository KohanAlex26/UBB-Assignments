from domain.department import Department
from infrastructure.patient_repo import PatientRepository


class DepartmentRepository:
    """
    A department repository is a structure that manages multiple departments of class Department
    """
    def __init__(self):
        """
        Create a new instance of DepartmentRepository
        """
        self.__data = []
        
    def get_departments(self):
        """
        Return the list of departments
        for testing purposes
        """
        return self.__data
        
    def add_department(self, department):
        """
        Add a department to the repository
        """
        if len(self.__data) > 0:
            for e in self.__data:
                if e.get_identifier() == department.get_identifier():
                    raise AttributeError("A department with the provided identifier already exists.")

        self.__data.append(department)
        
    def update_department(self, identifier, department):
        """
        Update a department with a given identifier
        """
        if type(identifier) != int:
            raise TypeError("The department's identifier must be an integer.")
        elif identifier < 0:
            raise ValueError("The department's identifier must be greater than or equal to 0.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough departments in the repository to perform this operation.")
        
        for i in range(len(self.__data)):
            if self.__data[i].get_identifier() == identifier:
                self.__data[i] = department
                
    def delete_department(self, identifier):
        """
        Delete a department with a given identifier
        """
        if type(identifier) != int:
            raise TypeError("The department's identifier must be an integer.")
        elif identifier < 0:
            raise ValueError("The department's identifier must be greater than or equal to 0.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough departments in the repository to perform this operation.")
        
        for i in range(len(self.__data) - 1, -1, -1):
            if self.__data[i].get_identifier() == identifier:
                del self.__data[i]
                
    def get_departments_with_patient_count_less_than(self, n):
        """
        Return a list with the departments that have
        a number of patients less than the given parameter
        """
        if type(n) != int:
            raise TypeError("The patient count must be an integer.")
        elif n < 1:
            raise ValueError("The patient count must be greater than or equal to 1.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough departments in the repository to perform this operation.")
        
        temp = []
        for department in self.__data:
            if len(department.get_patients()) < n:
                temp.append(department)

        return temp
    
    def get_departments_with_bed_count(self, count):
        """
        Return a list with the departments that have
        the number of beds given by the parameter
        """
        if type(count) != int:
            raise TypeError("The bed count must be an integer.")
        elif count < 0:
            raise ValueError("The bed count must be greater than or equal to 1.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough departments in the repository to perform this operation.")
        
        temp = []
        for department in self.__data:
            if department.get_bed_count() == count:
                temp.append(department)

        return temp
    
    def __len__(self):
        """
        Return the size of the DepartmentRepository
        """
        return len(self.__data)
