package repo;

import model.MarathonRace;
import model.Registration;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationInMemoryRepository extends AbstractRepository<Integer, Registration> implements RegistrationRepository {


    @Override
    public List<Registration> filterByRace(int id) {
        return getAll().stream().filter(x->x.getRace().getID()==id).collect(Collectors.toList());
    }

    @Override
    public List<Registration> filterByAge(int age1,int age2) {
        return getAll().stream().filter(x->x.getAge()>=age1 && x.getAge()<=age2).collect(Collectors.toList());
    }
}
