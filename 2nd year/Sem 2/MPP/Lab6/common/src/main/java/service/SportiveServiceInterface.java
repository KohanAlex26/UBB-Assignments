package service;

import domain.entities.Sportive;
import domain.exceptions.MyException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SportiveServiceInterface {

    void addSportive(Sportive sportive) throws MyException;

    void deleteSportive(Long id) throws MyException;

    void updateSportive(Sportive Sportive) throws MyException;

    Sportive getSportiveById(long id) throws MyException;

    Set<Sportive> getAllSportives();

    Set<Sportive> filterSportivesByAge(int age);

    Set<Sportive> filterSportivesByFirstName(String firstName);

    Set<Sportive> filterSportivesByTeamId(int teamId);

}
