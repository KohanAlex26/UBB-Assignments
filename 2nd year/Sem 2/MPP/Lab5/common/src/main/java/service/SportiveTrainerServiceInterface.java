package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SportiveTrainerServiceInterface {
    CompletableFuture<Boolean> addSportiveTrainer(SportiveTrainer st) throws ValidatorException;
    CompletableFuture<Boolean> deleteSportiveTrainer(SportiveTrainer st) throws ValidatorException;
    CompletableFuture<Boolean> updateSportiveTrainer(SportiveTrainer st) throws ValidatorException;
    CompletableFuture<Boolean> associateSportiveTrainer(SportiveTrainer st) throws ValidatorException;
    CompletableFuture<Boolean> dissociateSportiveTrainer(Long id) throws ValidatorException;
    CompletableFuture<Set<SportiveTrainer>> getAllSportivesTrainers();
    Optional<Sportive> getOneSportive(Long id);
    Optional<Trainer> getOneTrainer(Long id);
    CompletableFuture<Set<Trainer>> allTrainersOfOneSportive(Sportive s);
    CompletableFuture<Set<Sportive>> allSportivesOfOneTrainer(Trainer t);
    CompletableFuture<Set<String>> allTrainingTypesOfOneSportive(Sportive s);
    CompletableFuture<SportiveTrainer> mostExpensiveTraining();
}
