package service;

import domain.entities.Sportive;
import domain.exceptions.ValidatorException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SportiveServiceInterface {

    CompletableFuture<Boolean> addSportive(Sportive sportive) throws ValidatorException;

    CompletableFuture<Boolean> deleteSportive(Long id) throws ValidatorException;

    CompletableFuture<Boolean> updateSportive(Sportive Sportive) throws ValidatorException;

    CompletableFuture<Set<Sportive>> filterByAge(int age);

    CompletableFuture<Set<Sportive>> filterByFirstName(String st);

    CompletableFuture<Set<Sportive>> filterByTeamId(int id);

    CompletableFuture<Set<Sportive>> getAllSportives();

//    CompletableFuture<Sportive> findOneSportive(Long id);

}
