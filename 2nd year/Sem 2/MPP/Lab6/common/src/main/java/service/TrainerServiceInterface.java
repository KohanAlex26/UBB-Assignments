package service;

import domain.entities.Trainer;
import domain.exceptions.MyException;

import java.util.Set;

public interface TrainerServiceInterface {
    void addTrainer(Trainer trainer) throws MyException;

    void deleteTrainer(Trainer trainer) throws MyException;

    void updateTrainer(Trainer trainer) throws MyException;

    Set<Trainer> getAllTrainers();

    Set<Trainer> filterTrainersByName(String name);
}
