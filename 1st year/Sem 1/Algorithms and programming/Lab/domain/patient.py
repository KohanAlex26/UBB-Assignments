class Patient:
    """
    A patient is identified by
    first_name, last_name, pnc, and disease
    """
    def __init__(self, first_name, last_name, pnc, disease):
        """
        Create a new instance of Patient
        """
        self.set_first_name(first_name)
        self.set_last_name(last_name)
        self.set_pnc(pnc)
        self.set_disease(disease)

    def get_first_name(self):
        """
        Return the first name of the patient
        """
        return self.__first_name

    def get_last_name(self):
        """
        Return the last name of the patient
        """
        return self.__last_name

    def get_pnc(self):
        """
        Return the personal numerical code
        of the patient
        """
        return self.__pnc

    def get_disease(self):
        """
        Return the disease of the patient
        """
        return self.__disease

    def set_first_name(self, first_name):
        """
        Set the first name of the patient
        """
        if type(first_name) != str:
            raise TypeError("The patient's last name must be a string.")
        elif len(first_name) == 0:
            raise ValueError("The patient's last name must NOT be an empty string.")
        
        self.__first_name = first_name

    def set_last_name(self, last_name):
        """
        Set the last name of the patient
        """
        if type(last_name) != str:
            raise TypeError("The patient's last name must be a string.")
        elif len(last_name) == 0:
            raise ValueError("The patient's last name must NOT be an empty string.")
        
        self.__last_name = last_name

    def set_pnc(self, pnc):
        """
        Set the personal numerical code
        of the patient
        """
        if type(pnc) != int:
            raise TypeError("The patient's pnc must be an integer.")
        elif pnc < 0:
            raise ValueError("The patient's pnc must be greater than or equal to 0.")
        
        self.__pnc = pnc

    def set_disease(self, disease):
        """
        Set the disease of the patient
        """
        if type(disease) != str:
            raise TypeError("The patient's disease must be a string.")
        elif len(disease) == 0:
            raise ValueError("The patient's disease must NOT be an empty string.")
        
        self.__disease = disease
        
    def __str__(self):
        """
        Return a string representation of Patient
        """
        return "Patient " + self.__first_name + " " + self.__last_name + " with pnc " + str(self.__pnc) + " and disease " + self.__disease + "."
