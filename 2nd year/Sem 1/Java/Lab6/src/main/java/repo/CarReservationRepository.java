package repo;

import model.CarReservation;

import java.util.List;

public interface CarReservationRepository extends Repository<Integer, CarReservation>{
    List<CarReservation> filterByOwner(String ownerName);
    List<CarReservation> filterByOwnerAndDate(String ownerName, String date);
}
