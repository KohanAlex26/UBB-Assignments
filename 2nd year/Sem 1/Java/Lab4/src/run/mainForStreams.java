package run;

import CarDomain.Car;

import java.util.ArrayList;
import java.util.List;

public class mainForStreams {
    public static void main(String[] args)
    {
        Car c1=new Car("a","a","a",1,1);
        Car c2=new Car("b","b","b",2,2);
        Car c3=new Car("c","c","c",1,3);
        Car c4=new Car("c","c","c",1,4);
        Car c5=new Car("c","c","c",3,4);
        Car c6=new Car("c","c","c",4,4);
        List<Car> myList= new ArrayList<Car>();
        myList.add(c1);
        myList.add(c2);
        myList.add(c3);
        myList.add(c4);
        myList.add(c5);
        myList.add(c6);

        System.out.println("\nThe brands of the cars(distinct)");
        myList.stream()
                .map(Car::getBrand)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nAll the models from a certain brand");
        myList.stream()
                .filter(s->s.getBrand().equals("c"))
                .forEach(System.out::println);

        System.out.println("\nAll the models cars with more than a given number of seats");
        myList.stream()
                .filter(s->s.getSeats()>=2)
                .forEach(System.out::println);

        System.out.println("\nAll the model cars with less than a given number of seats");
        myList.stream()
                .filter(s->s.getSeats()<2)
                .forEach(System.out::println);

        System.out.println("\nThe color of a certain Car ");
        myList.stream()
                .filter(s->s.getId()==1)
                .forEach(s->{System.out.println(s.getColor());});

        System.out.println("\nThe brand of a certain model ");
        myList.stream()
                .filter(s->s.getModel().equals("b"))
                .forEach(s->{System.out.println(s.getBrand());});

        System.out.println("\nThe number of model build by a given brand with more than a given number of seats ");
        long j= myList.stream()
                .filter(s->s.getBrand().equals("c"))
                .filter(s->s.getSeats()>1)
                .count();
        System.out.println(j);

    }
}
