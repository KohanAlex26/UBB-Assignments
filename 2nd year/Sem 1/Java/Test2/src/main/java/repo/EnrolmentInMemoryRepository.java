package repo;

import model.Enrolment;
import model.WinterGame;

import java.util.List;
import java.util.stream.Collectors;

public class EnrolmentInMemoryRepository extends AbstractRepository<Integer, Enrolment> implements EnrolmentRepository {

    @Override
    public List<Enrolment> filterByGame(int id) {
        return getAll().stream().filter(x->x.getWinterGame().getID()==id).collect(Collectors.toList());
    }

    @Override
    public  List<WinterGame> filterGamesByChild(String childName) {
        return getAll().stream().filter(x->x.getPersonName().equals(childName)).map(Enrolment::getWinterGame).collect(Collectors.toList());
    }
}
