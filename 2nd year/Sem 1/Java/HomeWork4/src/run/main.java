package run;


import CarDomain.Car;
import Repository.RepositorySerialization;
import model.CarReservation;

public class main {
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
