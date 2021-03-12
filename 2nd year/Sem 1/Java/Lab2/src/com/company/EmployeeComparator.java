package com.company;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
    public int compare(Employee e1,Employee e2)
    {
        return e1.getSalary()-e2.getSalary();
    }
}
