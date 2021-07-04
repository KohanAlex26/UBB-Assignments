package service;

import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RepositoryInterface;
import repository.TrainerRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TrainerServiceServer implements TrainerServiceInterface{
    @Autowired
    Validator<Trainer> validator;

    @Autowired
    RepositoryInterface<Long, Trainer> repository;

    @Autowired
    TrainerServiceInterface trainerService;


    @Override
    public void addTrainer(Trainer trainer) throws MyException {
        this.validator.validate(trainer);
        if (this.repository.save(trainer).isPresent())
            throw new MyException("Trainer already exists");
    }

    @Override
    public void deleteTrainer(Trainer trainer) throws MyException {
        this.validator.validate(trainer);
        if (!this.repository.delete(trainer.getId()).isPresent())
            throw new MyException("Trainer does not exist");
    }

    @Override
    public void updateTrainer(Trainer trainer) throws MyException {
        this.validator.validate(trainer);
        if (this.repository.update(trainer).isPresent())
            throw new MyException("Trainer does not exist");
    }

    @Override
    public Set<Trainer> getAllTrainers() {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Trainer> filterTrainersByName(String name) {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .filter(trainer -> (trainer.getFirstName().contains(name) || trainer.getLastName().contains(name)))
                .collect(Collectors.toSet());
    }
}
