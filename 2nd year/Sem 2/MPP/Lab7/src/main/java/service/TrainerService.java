package service;

import domain.entities.Trainer;

import java.util.List;

public interface TrainerService {
    void addTrainer(Trainer trainer);

    void updateTrainer(Trainer trainer);

    void deleteTrainer(Trainer trainer);

    List<Trainer> getAllTrainers();

    List<Trainer> filterTrainersByName(String name);
}
