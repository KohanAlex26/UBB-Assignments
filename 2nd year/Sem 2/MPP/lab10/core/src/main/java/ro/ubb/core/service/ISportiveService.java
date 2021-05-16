package ro.ubb.core.service;

import ro.ubb.core.model.Sportive;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

public interface ISportiveService {
    void addSportive(Sportive sportive) throws ValidationException;

    void deleteSportive(Long id) throws ValidationException;

    void updateSportive(Sportive sportive) throws ValidationException;

    Sportive getSportiveById(long id) throws ValidationException;

    List<Sportive> getAllSportives();

    List<Sportive> filterSportivesByAge(int age);

    List<Sportive> filterSportivesByFirstName(String firstName);

    List<Sportive> filterSportivesByTeamId(int teamId);

    List<Sportive> sortSportivesAscendingByFirstName();

    List<Sportive> getSportivesOnPage(int pageNo, int pageSize);
}
