import unittest
from domain.patient import Patient
from domain.department import Department
from infrastructure.patient_repo import PatientRepository


class DepartmentTest(unittest.TestCase):
    """
    Test the Department class
    """
    def test_create(self):
        """
        Test Department creation
        """
        patient1 = Patient("David", "Horvath", 500, "Common Cold")
        patient2 = Patient("Michael", "Black", 200, "Scoliosis")
        
        repo1 = PatientRepository()
        repo1.add_patient(patient1)
        
        repo2 = PatientRepository()
        repo2.add_patient(patient1)
        repo2.add_patient(patient2)
        
        department = Department(1, "Main", 50, repo1)

        """
        Test getters
        """
        self.assertEqual(department.get_identifier(), 1)
        self.assertEqual(department.get_name(), "Main")
        self.assertEqual(department.get_bed_count(), 50)
        self.assertEqual(department.get_patients(), repo1)
        self.assertEqual(str(department), "Department Main with identifier 1, 50 beds, and 1 patients.")

        """
        Test setters
        """
        department.set_identifier(5)
        department.set_name("Secondary")
        department.set_bed_count(25)
        department.set_patients(repo2)

        self.assertEqual(department.get_identifier(), 5)
        self.assertEqual(department.get_name(), "Secondary")
        self.assertEqual(department.get_bed_count(), 25)
        self.assertEqual(department.get_patients(), repo2)
        self.assertEqual(str(department), "Department Secondary with identifier 5, 25 beds, and 2 patients.")


if __name__ == "__main__":
    unittest.main()

