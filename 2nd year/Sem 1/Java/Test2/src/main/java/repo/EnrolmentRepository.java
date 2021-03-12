package repo;

import model.WinterGame;
import model.Enrolment;

import java.util.List;

public interface EnrolmentRepository extends Repository<Integer, Enrolment>{
    List<Enrolment> filterByGame(int id);
    List<WinterGame> filterGamesByChild(String childName);
}
