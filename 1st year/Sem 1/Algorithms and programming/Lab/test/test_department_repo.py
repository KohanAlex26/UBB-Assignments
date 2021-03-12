import unittest
from infrastructure.department_repo import DepartmentRepository
from infrastructure.patient_repo import PatientRepository
from domain.department import Department
from domain.patient import Patient


class DepartmentRepositoryTest(unittest.TestCase):
    """
    Test the DepartmentRepository class
    """
    def test_create(self):
        """
        Test DepartmentRepository creation
        """
        department_repo = DepartmentRepository()
        self.assertEqual(len(department_repo), 0)
        
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        patient_repo.add_patient(patient1)
        patient_repo.add_patient(patient2)
        
        department = Department(1, "Main", 50, patient_repo)
        department_repo.add_department(department)
        self.assertEqual(len(department_repo), 1)
        
    def test_update_department(self):
        """
        Test the update_department function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo = PatientRepository()
        patient_repo.add_patient(patient1)
        patient_repo.add_patient(patient2)
        
        department = Department(1, "Main", 50, patient_repo)
        department_repo = DepartmentRepository()
        department_repo.add_department(department)
        
        new_department = Department(5, "Secondary", 25, patient_repo)
        department_repo.update_department(1, new_department)
        
        self.assertEqual(department_repo.get_departments(), [new_department])
        
    def test_delete_department(self):
        """
        Test the delete_department function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo1 = PatientRepository()
        patient_repo1.add_patient(patient1)
        patient_repo1.add_patient(patient2)
        
        patient_repo2 = PatientRepository()
        patient_repo2.add_patient(patient1)
        
        department1 = Department(1, "Main", 50, patient_repo1)
        department2 = Department(5, "Secondary", 25, patient_repo2)
        
        department_repo = DepartmentRepository()
        department_repo.add_department(department1)
        department_repo.add_department(department2)
        
        department_repo.delete_department(1)
        self.assertEqual(department_repo.get_departments(), [department2])
        
        department_repo.delete_department(3)
        self.assertEqual(department_repo.get_departments(), [department2])
        
        department_repo.delete_department(5)
        self.assertEqual(department_repo.get_departments(), [])
        
    def test_get_departments_with_patient_count_less_than(self):
        """
        Test the get_departments_with_patient_count_less_than function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo1 = PatientRepository()
        patient_repo1.add_patient(patient1)
        patient_repo1.add_patient(patient2)
        
        patient_repo2 = PatientRepository()
        patient_repo2.add_patient(patient1)
        
        department1 = Department(1, "Main", 50, patient_repo1)
        department2 = Department(5, "Secondary", 25, patient_repo2)
        
        department_repo = DepartmentRepository()
        department_repo.add_department(department1)
        department_repo.add_department(department2)
        
        self.assertEqual(department_repo.get_departments_with_patient_count_less_than(2), [department2])
        self.assertEqual(department_repo.get_departments_with_patient_count_less_than(3), [department1, department2])
        self.assertEqual(department_repo.get_departments_with_patient_count_less_than(1), [])

    def test_get_departments_with_bed_count(self):
        """
        Test the get_departments_with_bed_count function
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        patient_repo1 = PatientRepository()
        patient_repo1.add_patient(patient1)
        patient_repo1.add_patient(patient2)
        
        patient_repo2 = PatientRepository()
        patient_repo2.add_patient(patient1)
        
        department1 = Department(1, "Main", 50, patient_repo1)
        department2 = Department(5, "Secondary", 25, patient_repo2)
        
        department_repo = DepartmentRepository()
        department_repo.add_department(department1)
        department_repo.add_department(department2)
        
        self.assertEqual(department_repo.get_departments_with_bed_count(50), [department1])
        self.assertEqual(department_repo.get_departments_with_bed_count(25), [department2])
        self.assertEqual(department_repo.get_departments_with_bed_count(0), [])


if __name__ == "__main__":
    unittest.main()

