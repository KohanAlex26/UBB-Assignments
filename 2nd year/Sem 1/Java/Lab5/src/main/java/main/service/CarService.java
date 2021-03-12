package main.service;

import main.model.Car;
import main.repo.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private CarRepository mRepo;

    public CarService(CarRepository mRepo) {
        this.mRepo = mRepo;
    }

    public Car adauga(Car m){
        return mRepo.add(m);
    }
    public List<Car> getAll(){
//       try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<Car> lm=new ArrayList<>();
        for(Car m:mRepo.findall()){
            lm.add(m);
        }
        return lm;
    }
}
