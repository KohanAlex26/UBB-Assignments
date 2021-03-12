package com.company;

public class Employee {
    private String name;
    private int salary;
    private boolean isWorking;
    private int age;
    Employee()
    {
        name= "";
        salary=0;
        isWorking=false;
        age=0;
    }
    public Employee(String name,int salary,boolean isWorking,int age)
    {
        this.name=name;
        this.salary=salary;
        this.isWorking=isWorking;
        this.age=age;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setSalary(int salary)
    {
        this.salary=salary;
    }
    public void setIsWorking(boolean isWorking)
    {
        this.isWorking=isWorking;
    }
    public void setAge(int age)
    {
        this.age=age;
    }
    public String getName()
    {
        return this.name;
    }
    public int getSalary()
    {
        return this.salary;
    }
    public boolean getIsWorking()
    {
        return this.isWorking;
    }
    public int getAge()
    {
        return this.age;
    }

    @Override
    public String toString()
    {
        String str=name+", "+salary+", "+isWorking+", "+age;
        return str;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Employee)
        {
            Employee e=(Employee)obj;
            return this.name.equals(e.name) && this.salary==e.salary && this.isWorking==e.isWorking;
        }
        return false;
    }

    public int compareTo(Employee e)
    {
        if(e.salary>this.salary)
            return -1;
        if(e.salary<this.salary)
            return 1;
        return 0;
    }
}
