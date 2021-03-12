package repo;

import model.Car;
import model.carStatus;

import java.util.List;
import java.util.stream.Collectors;

public class CarInMemoryRepository extends AbstractRepository<Integer,Car> implements CarRepository {
   public CarInMemoryRepository(){}


    @Override
    public List<Car> findByBrand(String brand) {
        return getAll().stream().filter(x->x.getBrand().toLowerCase().contains(brand.toLowerCase())).collect(Collectors.toList());
    }

   @Override
    public List<Car> findByModel(String model) {
        return getAll().stream().filter(x->x.getModel().toLowerCase().contains(model.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Car> findByColor(String color) {
        return getAll().stream().filter(x->x.getColor().toLowerCase().contains(color.toLowerCase())).collect(Collectors.toList());
    }
    @Override
    public List<Car> findAllCars() {
        return getAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Car> findBySeats(int seats) {
        return getAll().stream().filter(x->x.getSeats()==seats).collect(Collectors.toList());
    }

    @Override
    public List<Car> filterdByStatus(carStatus status) {
        return getAll().stream().filter(x->x.getStatus()==status).collect(Collectors.toList());
    }

    @Override
    public List<Car> filterByModelAndStatus(String model, carStatus status) {
        return getAll().stream().filter(x->x.getModel().toLowerCase().contains(model.toLowerCase()) && x.getStatus()==status).collect(Collectors.toList());
    }
}
