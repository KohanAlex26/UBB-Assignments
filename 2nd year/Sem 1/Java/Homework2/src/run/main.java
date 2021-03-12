package run;

import Repository.RequestRepository;
import model.CarReservation;
import CarDomain.*;
import java.util.Iterator;
public class main {
    public static void main(String[] args)
    {
        RequestRepository rr=new RequestRepository();


        Car c=new Car("f","b","s",0);
        rr.add(new CarReservation(0,"a","b","c",c,"",true));
        System.out.println(rr.findbyId(0));



        Car c1=new Car("b","c","a",4);
        rr.update(0,new CarReservation(0,"r","t","y",c1,"",true));
        System.out.println(rr.findbyId(0));



        rr.delete(new CarReservation(0,"a","b","c",c,"",true));
        System.out.println(rr.findbyId(0));


        CarReservation cr1=new CarReservation(1,"a","b","c",c,"",false);
        CarReservation cr2=new CarReservation(0,"a","b","c",c,"",true);
        System.out.println(cr1.equals(cr2));
    }
}
