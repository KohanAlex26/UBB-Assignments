package main.service;

import main.model.CarReservation;
import main.repo.CarReservationRepository;

import java.util.ArrayList;
import java.util.List;

public class CarReservationService {
    private CarReservationRepository mRepo;

    public CarReservationService(CarReservationRepository mRepo) {
        this.mRepo = mRepo;
    }

    public CarReservation adauga(CarReservation m){
        return mRepo.add(m);
    }
    public List<CarReservation> getAll(){
//       try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<CarReservation> lm=new ArrayList<>();
        for(CarReservation m:mRepo.findall()){
            lm.add(m);
        }
        return lm;
    }
}
