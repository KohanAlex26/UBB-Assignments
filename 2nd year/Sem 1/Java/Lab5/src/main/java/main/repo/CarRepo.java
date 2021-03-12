package main.repo;

import main.model.Car;

public class CarRepo extends CarRepository {
    public CarRepo(){
        loadCars();
    }

    public Car add(Car m){
        m.setId(IDGenerator.getNextId());
        super.add(m);
        return m;
    }
    private void loadCars()
    {
        add(new Car("Tesla","X",6));
        add(new Car("BMW","X6",4));
        add(new Car("Audi","A6",4));
        add(new Car("Mercedes","GT",2));
    }
}
