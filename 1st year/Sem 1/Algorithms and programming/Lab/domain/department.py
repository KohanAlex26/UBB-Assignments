from infrastructure.patient_repo import PatientRepository


class Department:
    """
    A hospital has several departments identified by
    identifier, name, bed_count and patients
    """
    def __init__(self, identifier, name, bed_count, patients):
        """
        Create a new instance of Department
        """
        self.set_identifier(identifier)
        self.set_name(name)
        self.set_bed_count(bed_count)
        self.set_patients(patients)

    def get_identifier(self):
        """
        Return the identifier of the department
        """
        return self.__identifier

    def get_name(self):
        """
        Return the name of the department
        """
        return self.__name

    def get_bed_count(self):
        """
        Return the number of beds of the department
        """
        return self.__bed_count

    def get_patients(self):
        """
        Return a PatientRepository of the patients
        housed in the department
        """
        return self.__patients

    def set_identifier(self, identifier):
        """
        Set the identifier of the department
        """
        if type(identifier) != int:
            raise TypeError("The department's identifier must be an integer.")
        elif identifier < 0:
            raise ValueError("The department's identifier must be greater than or equal to 0.")
        
        self.__identifier = identifier

    def set_name(self, name):
        """
        Set the name of the department
        """
        if type(name) != str:
            raise TypeError("The department's name must be a string.")
        elif len(name) == 0:
            raise ValueError("The department's name must NOT be an empty string.")
        
        self.__name = name

    def set_bed_count(self, bed_count):
        """
        Set the number of beds of the department
        """
        if type(bed_count) != int:
            raise TypeError("The department's bed count must be an integer.")
        elif bed_count < 0:
            raise ValueError("The department's bed count must be greater than or equal to 0.")

        self.__bed_count = bed_count

    def set_patients(self, patients):
        """
        Set PatientRepository of the patients
        housed in the department
        """
        if type(patients) != PatientRepository:
            raise TypeError("The department's patients must be a PatientRepository.")
        
        self.__patients = patients
        
    def __str__(self):
        """
        Return a string representation of Department
        """
        return "Department " + self.__name + " with identifier " + str(self.__identifier) + ", " + str(self.__bed_count) + " beds, and " + str(len(self.__patients)) + " patients."
