package service;

import model.Car;
import model.CarReservation;
import model.carStatus;
import repo.CarRepository;
import repo.CarReservationRepository;
import repo.RepositoryException;

import java.util.List;

public class CarServices {

    private CarReservationRepository reservationRepository;

    private CarRepository carRepository;

    public CarServices(CarRepository carRepository,CarReservationRepository reservationRepository) {
        this.reservationRepository=reservationRepository;
        this.carRepository=carRepository;
    }

    public int addCar(String model, String color, String brand, int seats) throws ServicesException{
        try {
            Car car = new Car(model,color,brand,seats);
            Car newCar = carRepository.add(car);
            return newCar.getID();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding car"+ex);
        }
    }

    public void deleteCar(Integer id) throws ServicesException{
        try {
            carRepository.delete(id);
        }catch (RepositoryException ex){
            throw new ServicesException("Error deleting car"+ex);
        }
    }

    public void updateCar(Integer id,String model, String color, String brand, int seats) throws ServicesException{
        try {
            Car car = new Car(model,color,brand,seats);
            car.setID(id);
            carRepository.update(id,car);
        }catch (RepositoryException ex){
            throw new ServicesException("Error updating car"+ex);
        }
    }
    public List<Car> getCarsNotReservedByModel(String model){
        return carRepository.filterByModelAndStatus(model, carStatus.New);

    }

    public List<Car> getCarsWithCertainBrand(String brand)
    {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getCarsWithCertainColor(String color)
    {
        return carRepository.findByColor(color);
    }
    public List<Car> getCarsWithCertainSeats(int seats)
    {
        return carRepository.findBySeats(seats);
    }
    public List<Car> getCars()
    {
        return carRepository.findAllCars();
    }

    public void addCarReservation(Car car,String ownerName,String ownerAddress,String phoneNumber, String date) throws ServicesException{
        try {
            car.setStatus(carStatus.Reserved);
            CarReservation carReservation = new CarReservation(ownerName,ownerAddress,phoneNumber,date,car);
            carRepository.update(car.getId(),car);
            reservationRepository.add(carReservation);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding car reservation"+er);
        }

    }
    public List<Car> getReservedCars(){
        return carRepository.filterdByStatus(carStatus.Reserved);

    }

    public List<Car> getCarByStatus(carStatus status){
        return carRepository.filterdByStatus(status);

    }
}
