package repo;

import model.MarathonRace;
import model.Registration;

import java.util.List;

public interface RegistrationRepository extends Repository<Integer, Registration>{
        List<Registration> filterByRace(int id);
        List<Registration> filterByAge(int age1,int age2);
}
