package core.service;

import core.domain.SportiveTrainer;
import core.domain.Team;
import core.domain.Sportive;
import core.domain.validators.*;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface SportiveService {
    void addSportive(String firstName, String lastName, int age, Team team);
    void deleteSportive(Long id);
    void updateSportive(Long id, String firstName, String lastName, int age, Team team);
    List<Sportive> getAllSportives();
    List<Sportive> getSportivesOnPage(int pageNo, int pageSize);
    Sportive getSportiveById(Long id);
//    List<Sportive> getSportivesFromRepository();
    List<Sportive> getSportivesOfTeam(Long teamId);
    List<Sportive> filterSportivesByAge(int age);
    List<Sportive> filterSportivesByFirstName(String firstName);
    List<Sportive> filterSportivesByTeamId(int teamId);
    List<Sportive> sortSportivesAscendingByFirstName();


    List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveId(long sportiveId);



}
