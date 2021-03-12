package com.company;
import java.util.*;
public class Department {
    private ArrayList<Employee> employees;

    public Department()
    {
        employees=new ArrayList<Employee>();
    }
    public boolean addEmployee(Employee addedEmployee)
    {
        employees.add(addedEmployee);
        return true;
    }
    public Iterator<Employee> employeeIterator()
    {
        return employees.iterator();
    }
    public Employee biggestSalary()
    {
        return Collections.max(employees,new EmployeeComparator());
    }
    public Employee youngestEmployee()
    {
        return Collections.min(employees,new EmployeeComparator1());
    }
    public void givenName(String name)
    {
        int i;
        for (i = 0; i < employees.size(); i++)
            if(employees.get(i).getName().equals(name))
                System.out.println(employees.get(i));
    }
}
