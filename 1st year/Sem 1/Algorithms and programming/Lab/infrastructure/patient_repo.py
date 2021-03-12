from domain.patient import Patient


class PatientRepository:
    """
    A patient repository is a structure that manages multiple departments of class Patient
    """
    def __init__(self):
        """
        Create a new instance of PatientRepository
        """
        self.__data = []
    
    def get_patients(self):
        """
        Return the list of patients
        for testing purposes
        """
        return self.__data
    
    def add_patient(self, patient):
        """
        Add a patient to the repository
        """
        if len(self.__data) > 0:
            for p in self.__data:
                if p.get_pnc() == patient.get_pnc():
                    raise AttributeError("A patient with the provided PNC already exists.")

        self.__data.append(patient)
    
    def update_patient(self, first_name, last_name, patient):
        """
        Update a patient with a given name
        """
        if type(first_name) != str or type(last_name) != str:
            raise TypeError("The names must be strings.")
        elif len(first_name) == 0 or len(last_name) == 0:
            raise ValueError("The names must NOT be an empty strings.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough patients in the repository to perform this operation")
        
        for i in range(len(self.__data)):
            if self.__data[i].get_first_name() + self.__data[i].get_last_name() == first_name + last_name:
                self.__data[i] = patient
                    
    def delete_patient(self, first_name, last_name):
        """
        Delete a patient with a given name
        """
        if type(first_name) != str or type(last_name) != str:
            raise TypeError("The names must be strings.")
        elif len(first_name) == 0 or len(last_name) == 0:
            raise ValueError("The names must NOT be an empty strings.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough patients in the repository to perform this operation")
        
        for i in range(len(self.__data) - 1, -1, -1):
            if self.__data[i].get_first_name() + self.__data[i].get_last_name() == first_name + last_name:
                del self.__data[i]
                    
    def get_patients_whose_name_begin_with(self, prefix):
        """
        Return a list with the patients whose name
        start with the given parameter
        """
        if type(prefix) != str:
            raise TypeError("The prefix must be a string.")
        elif len(prefix) == 0:
            raise ValueError("The prefix must NOT be an empty string.")
        elif len(self.__data) == 0:
            raise IndexError("There are not enough patients in the repository to perform this operation")
        
        temp = []

        for patient in self.__data:
            if patient.get_first_name().startswith(prefix):
                temp.append(patient)

        return temp
    
    def __len__(self):
        """
        Return the size of the PatientRepository
        """
        return len(self.__data)
