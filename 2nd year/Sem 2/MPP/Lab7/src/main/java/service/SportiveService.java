package service;

import domain.entities.Sportive;
import domain.exceptions.MyException;

import java.util.List;

public interface SportiveService {
    void addSportive(Sportive sportive) throws MyException;

    void deleteSportive(Sportive sportive) throws MyException;

    void updateSportive(Sportive sportive) throws MyException;

    List<Sportive> getAllSportives();

    List<Sportive> filterSportivesByAge(int age);

    List<Sportive> filterSportivesByFirstName(String firstName);

    List<Sportive> filterSportivesByTeamId(int teamId);


}
