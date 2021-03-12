package run;

import CarDomain.Car;
import Repository.ReservationRepositoryFile;
import model.CarReservation;

public class mainNotSerialazable {
    public static void main(String[] args) {


        ReservationRepositoryFile file= new ReservationRepositoryFile("citire2.txt");
        System.out.println("add");
        file.add(new CarReservation(1,"a","a","a",new Car("a","a","a",1,1),"a",false));
        file.add(new CarReservation(2,"b","b","b",new Car("b","b","b",2,2),"b",true));
         for(CarReservation el:file.findall()){
            System.out.println(el);
        }
        System.out.println("");

        System.out.println("update");
        file.update(1,new CarReservation(1,"c","a","a",new Car("a","a","a",1,1),"a",false));
        for(CarReservation el:file.findall()){
            System.out.println(el);
        }
        System.out.println("");

        System.out.println("delete");
        file.delete(new CarReservation(1,"c","a","a",new Car("a","a","a",1,1),"a",false));
        for(CarReservation el:file.findall()){
            System.out.println(el);
        }
        System.out.println("");

    }
}
