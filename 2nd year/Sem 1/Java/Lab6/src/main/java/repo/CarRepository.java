package repo;

import model.Car;
import model.carStatus;

import java.util.List;

public interface CarRepository extends Repository<Integer, Car>{
    List<Car> findByBrand(String name);
    List<Car> findByModel(String model);
    List<Car> findByColor(String color);
    List<Car> findAllCars();
    List<Car> findBySeats(int seats);
    List<Car> filterdByStatus(carStatus status);
    List<Car> filterByModelAndStatus(String model, carStatus status);
}