package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface SportiveTrainerServiceInterface {
    void addSportiveTrainer(SportiveTrainer st) throws MyException;
    void deleteSportiveTrainer(SportiveTrainer st) throws MyException;
    void updateSportiveTrainer(SportiveTrainer st) throws MyException;
    void associateSportiveTrainer(SportiveTrainer st) throws MyException;
    void dissociateSportiveTrainer(Long id) throws MyException;
    Set<SportiveTrainer> getAllSportivesTrainers();
    Optional<Sportive> getOneSportive(Long id);
    Optional<Trainer> getOneTrainer(Long id);
    Set<Trainer> allTrainersOfOneSportive(Sportive s) throws MyException;
    Set<Sportive> allSportivesOfOneTrainer(Trainer t) throws MyException;
    Set<String> allTrainingTypesOfOneSportive(Sportive s) throws MyException;
    SportiveTrainer mostExpensiveTraining() throws MyException;
}
