package Controller;

import CarDomain.Car;
import Repository.CarReservationRepository;
import model.CarReservation;
import model.Identifiable;


public class ControllerCarReservatioRepository {
    private CarReservationRepository crr;
    public ControllerCarReservatioRepository(){
    }
    public ControllerCarReservatioRepository(CarReservationRepository crr){
        this.crr=crr;
    }
    public void add(int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.add(cr);
    }
    public boolean delete(int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.delete(cr);
        return true;
    }
    public void update(int updateId,int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.update(updateId,cr);
    }
    public CarReservation findById(int Id)
    {
        return crr.findbyId(Id);
    }
    public Iterable<CarReservation> findAll()
    {
        return crr.findall();
    }
    public void controllerOwnerNameMap()
    {
        crr.ownerNameMap();
    }
    public void controllerCertainOwnerAdress(){
        crr.certainOwnerAdress();
    }
    public void controllerHigherThanAGivenNumber()
    {
        crr.higherThanAGivenNumber();
    }
    public void controllerLessThanAGivenNumber(){
        crr.lessThanAGivenNumber();
    }
    public void controllerDateOfACertainCarr()
    {
     crr.dateOfACertainCar();
    }
    public void controllerOwnerNameOfACertainId()
    {
        crr.ownerNameOfACertainId();
    }
    public void controllerNumberOfAddress(){
     crr.numberOfAddress();
    }
}

