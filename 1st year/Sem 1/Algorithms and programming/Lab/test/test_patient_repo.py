import unittest
from infrastructure.patient_repo import PatientRepository
from domain.patient import Patient


class PatientRepositoryTest(unittest.TestCase):
    """
    Test the PatientRepository class
    """
    def test_create(self):
        """
        Test PatientRepository creation
        """        
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        self.assertEqual(len(patient_repo), 0)
        
        patient_repo.add_patient(patient1)
        self.assertEqual(len(patient_repo), 1)
        
        patient_repo.add_patient(patient2)
        self.assertEqual(len(patient_repo), 2)
        
    def test_update_patient(self):
        """
        Test the update_patient function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        patient_repo.add_patient(patient1)
        
        patient_repo.update_patient("David", "Horvath", patient2)
        self.assertEqual(patient_repo.get_patients(), [patient2])
        
    def test_delete_patient(self):
        """
        Test the delete_patient function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        patient_repo.add_patient(patient1)
        patient_repo.add_patient(patient2)
        
        patient_repo.delete_patient("David", "Horvath")
        self.assertEqual(patient_repo.get_patients(), [patient2])
        
        patient_repo.delete_patient("David", "White")
        self.assertEqual(patient_repo.get_patients(), [patient2])
        
        patient_repo.delete_patient("Michael", "Black")
        self.assertEqual(patient_repo.get_patients(), [])
        
    def test_get_patients_whose_name_begin_with(self):
        """
        Test the get_patients_whose_name_begin_with function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Damian", "Woods", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        patient_repo.add_patient(patient1)
        patient_repo.add_patient(patient2)
        
        self.assertEqual(patient_repo.get_patients_whose_name_begin_with("Da"), [patient1, patient2])
        self.assertEqual(patient_repo.get_patients_whose_name_begin_with("Dav"), [patient1])
        self.assertEqual(patient_repo.get_patients_whose_name_begin_with("Mich"), [])


if __name__ == "__main__":
    unittest.main()
