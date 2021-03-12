package main.repo;

import main.model.Car;
import main.model.CarReservation;

public class CarReservationRepo extends CarReservationRepository {
    public CarReservationRepo(){
        loadCars();
    }

    public CarReservation add(CarReservation m){
        m.setId(IDGenerator.getNextId());
        super.add(m);
        return m;
    }
    private void loadCars()
    {
        add(new CarReservation("Dan","0744546789","20-10-2019"));
        add(new CarReservation("Ion","0756789456","12-09-2020"));
        add(new CarReservation("Marcel","0756324123","09-06-2018"));
    }
}
