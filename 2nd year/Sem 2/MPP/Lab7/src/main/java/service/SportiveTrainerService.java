package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.MyException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SportiveTrainerService {
    void addSportiveTrainer(SportiveTrainer st) throws MyException;

    void deleteSportiveTrainer(SportiveTrainer st) throws MyException;

    void updateSportiveTrainer(SportiveTrainer st) throws MyException;

    List<SportiveTrainer> getAllSportivesTrainers();

    Sportive getOneSportive(Long id);

    Trainer getOneTrainer(Long id);

    List<Trainer> allTrainersOfOneSportive(Sportive s) throws MyException;

    List<Sportive> allSportivesOfOneTrainer(Trainer t) throws MyException;

    List<String> allTrainingTypesOfOneSportive(Sportive s) throws MyException;

    SportiveTrainer mostExpensiveTraining() throws MyException;

}
