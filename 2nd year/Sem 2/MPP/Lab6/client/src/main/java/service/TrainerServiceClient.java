package service;

import domain.entities.Trainer;
import domain.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class TrainerServiceClient implements TrainerServiceInterface{
    @Autowired
    private TrainerServiceInterface trainerService;

    @Override
    public void addTrainer(Trainer trainer) throws MyException {
        trainerService.addTrainer(trainer);
    }

    @Override
    public void deleteTrainer(Trainer trainer) throws MyException {
        trainerService.deleteTrainer(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) throws MyException {
        trainerService.updateTrainer(trainer);
    }

    @Override
    public Set<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @Override
    public Set<Trainer> filterTrainersByName(String name) {
        return trainerService.filterTrainersByName(name);
    }
}
