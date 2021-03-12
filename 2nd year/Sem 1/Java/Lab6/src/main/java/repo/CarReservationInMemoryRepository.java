package repo;

import model.CarReservation;

import java.util.List;
import java.util.stream.Collectors;

public class CarReservationInMemoryRepository extends AbstractRepository<Integer,CarReservation> implements CarReservationRepository {

    @Override
    public List<CarReservation> filterByOwner(String owner) {
        return getAll().stream().filter(x->x.getOwnerName().toLowerCase().equals(owner.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<CarReservation> filterByOwnerAndDate(String owner, String date) {
        return getAll().stream().filter(x->x.getOwnerName().toLowerCase().equals(owner.toLowerCase()) && x.getDate().equals(date)).collect(Collectors.toList());
    }
}
