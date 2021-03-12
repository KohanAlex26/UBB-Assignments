package main.repo;

import main.model.Car;
import main.model.CarReservation;

import java.util.ArrayList;
import java.util.List;

public class CarReservationRepository extends AbstractRepository<CarReservation,Integer> {
    private List<CarReservation> myList= new ArrayList<CarReservation>();
    public CarReservationRepository(){
        CarReservation c1=new CarReservation(1,"a","a","0",new Car("a","a","a",1,1),"a",false);
        CarReservation c2=new CarReservation(2,"a","b","1",new Car("a","a","a",1,1),"a",false);
        CarReservation c3=new CarReservation(3,"b","c","2",new Car("a","a","a",1,1),"a",false);
        CarReservation c4=new CarReservation(4,"b","c","3",new Car("a","a","a",1,1),"a",false);
        CarReservation c5=new CarReservation(5,"c","c","4",new Car("a","a","a",1,1),"a",false);
        CarReservation c6=new CarReservation(6,"d","d","5",new Car("a","a","a",1,1),"a",false);
        myList.add(c1);
        myList.add(c2);
        myList.add(c3);
        myList.add(c4);
        myList.add(c5);
        myList.add(c6);
    }

    @Override
    public CarReservation add(CarReservation entity) {
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public CarReservation findbyId(Integer e) {
        return map.get(e);
    }

    @Override
    public Iterable<CarReservation> findall() {
        return map.values();
    }




    public void ownerNameMap()
    {
        System.out.println("\nThe owner of the carReservations(distinct)");
        myList.stream()
                .map(CarReservation::getOwnerName)
                .distinct()
                .forEach(System.out::println);
    }

    public void certainOwnerAdress()
    {
        System.out.println("\nAll the carReservations from a certain address");
        myList.stream()
                .filter(s->s.getOwnerAddress().equals("c"))
                .forEach(System.out::println);

    }
    public void higherThanAGivenNumber() {
        System.out.println("\nAll the carReservations with more than a phone number");
        myList.stream()
                .filter(s -> Integer.parseInt(s.getPhoneNumber()) >= 2)
                .forEach(System.out::println);
    }

    public void lessThanAGivenNumber(){
        System.out.println("\nAll the model carReservations with less than a given phone number");
        myList.stream()
                .filter(s -> Integer.parseInt(s.getPhoneNumber()) < 2)
                .forEach(System.out::println);
    }

    public void dateOfACertainCar(){
        System.out.println("\nThe date of a certain Car ");
        myList.stream()
                .filter(s->s.getId()==1)
                .forEach(s->{System.out.println(s.getDate());});
    }

    public void ownerNameOfACertainId(){
        System.out.println("\nThe ownerName of a certain Id ");
        myList.stream()
                .filter(s->s.getId()==1)
                .forEach(s->{System.out.println(s.getOwnerName());});
    }

    public void numberOfAddress(){
        System.out.println("\nThe number of ownerAdrresses with more than given phoneNumber ");
        long j= myList.stream()
                .filter(s->s.getOwnerAddress().equals("c"))
                .filter(s->Integer.parseInt(s.getPhoneNumber())>=3)
                .count();
        System.out.println(j);

    }
}