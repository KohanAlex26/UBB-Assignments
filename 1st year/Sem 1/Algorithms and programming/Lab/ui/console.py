from application.controller import HospitalController


class HospitalUI:
    """
    Handle all user interface related operations
    """
    menu_options = ["Exit", "Add a new department to the repository", "Update a department with a given ID", "Update a patient with a given name", "Delete a department with a given ID", "Delete a patient with a given name", "Return the departments with the number of patients lower than a given number", "Return the patients with the name starting with a given string", "Return the departments that have a given number of beds"]
    
    def __init__(self, controller):
        """
        Create a new instance of HospitalUI
        """
        self.__controller = controller

    @staticmethod
    def display_menu():
        """
        Clear the console window and display the user interface
        """
        for i in range(100):
            print()

        for i in range(len(HospitalUI.menu_options)):
            print((" " if i < 10 else "") + str(i) + ". " + HospitalUI.menu_options[i])
            
        print()

    def handle_menu_option(self, option):
        """
        Handle user input
        """
        if option == "1":
            self.__controller.add_department()
        elif option == "2":
            self.__controller.update_department_with_id()
        elif option == "3":
            self.__controller.update_patient_with_name()
        elif option == "4":
            self.__controller.delete_department_with_id()
        elif option == "5":
            self.__controller.delete_patient_with_name()
        elif option == "6":
            self.__controller.get_departments_with_patients_less_than()
        elif option == "7":
            self.__controller.get_patients_with_name_prefix()
        elif option == "8":
            self.__controller.get_departments_with_bed_count()
        else:
            print("ERROR: Invalid option.")

        input("\nPress <ENTER> to continue.")

    def main_menu(self):
        """
        Implement the user interface
        """
        while True:
            HospitalUI.display_menu()
            option = input("Option: ")

            if option == "0":
                input("INFO: Quitting.\nPress <ENTER> to continue.")
                return None
            
            self.handle_menu_option(option)
