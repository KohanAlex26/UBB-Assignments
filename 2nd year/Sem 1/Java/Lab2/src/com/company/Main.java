package com.company;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Department dp=new Department();
        dp.addEmployee(new Employee("ana",100,false,20));
        dp.addEmployee(new Employee("ion",200,true,30));
        dp.addEmployee(new Employee("ghita",300,true,40));
        dp.addEmployee(new Employee("ana",50,false,25));
        Iterator<Employee> it=dp.employeeIterator();
        for(;it.hasNext();)
        {
            System.out.println(it.next());
        }
        System.out.println(dp.biggestSalary());
        System.out.println(dp.youngestEmployee());
        String name1="ana";
        dp.givenName(name1);
    }
}
