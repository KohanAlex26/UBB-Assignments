package Controller;

import CarDomain.Car;
import Repository.CarRepository;
import Repository.CarReservationRepository;
import model.CarReservation;

public class ControllerCar {
    private CarRepository cr;
    public ControllerCar(){
    }
    public ControllerCar(CarRepository cr){
        this.cr=cr;
    }
    public void controllerBrandOfCars()
    {
        cr.brandOfCars();
    }
    public void controllerModelOfCertainBrand(){
        cr.modelOfCertainBrand();
    }
    public void controllerModelWithAGivenNumber()
    {
        cr.modelWithAGivenNumber();
    }
    public void controllerLessThanAGivenNumber(){
        cr.lessThanAGivenNumber();
    }
    public void controllerColorOfACertainCar()
    {
        cr.colorOfACertainCar();
    }
    public void controllerBrandOfACertainModel()
    {
        cr.brandOfACertainModel();
    }
    public void controllerModelBuild(){
        cr.modelBuild();
    }
}
