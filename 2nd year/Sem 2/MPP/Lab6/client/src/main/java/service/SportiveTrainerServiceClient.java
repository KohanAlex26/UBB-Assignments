package service;


import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class SportiveTrainerServiceClient implements SportiveTrainerServiceInterface{
    @Autowired
    private SportiveTrainerServiceInterface sportiveTrainerService;


    @Override
    public void addSportiveTrainer(SportiveTrainer st) throws MyException {
        sportiveTrainerService.addSportiveTrainer(st);
    }

    @Override
    public void deleteSportiveTrainer(SportiveTrainer st) throws MyException {
        sportiveTrainerService.deleteSportiveTrainer(st);
    }

    @Override
    public void updateSportiveTrainer(SportiveTrainer st) throws MyException {
        sportiveTrainerService.updateSportiveTrainer(st);
    }

    @Override
    public void associateSportiveTrainer(SportiveTrainer st) throws MyException {
        sportiveTrainerService.associateSportiveTrainer(st);
    }

    @Override
    public void dissociateSportiveTrainer(Long id) throws MyException {
        sportiveTrainerService.dissociateSportiveTrainer(id);
    }

    @Override
    public Set<SportiveTrainer> getAllSportivesTrainers() {
        return sportiveTrainerService.getAllSportivesTrainers();
    }

    @Override
    public Optional<Sportive> getOneSportive(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> getOneTrainer(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Trainer> allTrainersOfOneSportive(Sportive s) throws MyException {
        return sportiveTrainerService.allTrainersOfOneSportive(s);
    }

    @Override
    public Set<Sportive> allSportivesOfOneTrainer(Trainer t) throws MyException {
        return sportiveTrainerService.allSportivesOfOneTrainer(t);
    }

    @Override
    public Set<String> allTrainingTypesOfOneSportive(Sportive s) throws MyException {
        return sportiveTrainerService.allTrainingTypesOfOneSportive(s);
    }

    @Override
    public SportiveTrainer mostExpensiveTraining() throws MyException {
        return sportiveTrainerService.mostExpensiveTraining();
    }
}
