package main.run;


import main.model.Car;
import main.repo.RepositorySerialization;
import main.model.CarReservation;

public class mainSerialization {
    public static void main(String[] args)
    {

        RepositorySerialization rs=new RepositorySerialization("citire.txt");
        System.out.println("add");
        rs.add(new CarReservation(1,"a","a","a",new Car("a","a","a",1,1),"a",false));
        for(CarReservation el:rs.findall()) {
            System.out.println(el);
        }
        System.out.println("");


        System.out.println("update");
        rs.update(1,new CarReservation(1,"c","a","a",new Car("a","a","a",1,1),"a",false));
        /*rs.readFromFile();*/
        for(CarReservation el:rs.findall()) {
            System.out.println(el);
        }
        System.out.println("");


        System.out.println("delete");
        rs.delete(new CarReservation(1,"a","a","a",new Car("a","a","a",1,1),"a",false));
        for(CarReservation el:rs.findall()) {
            System.out.println(el);
        }
    }
}
