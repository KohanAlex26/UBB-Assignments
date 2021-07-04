package service;


import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for trainer.
 *
 * @author oprea.
 *
 */
public class TrainerService {
    private Repository<Long, Trainer> repository;
    private Repository<Long, SportiveTrainer> stRepo;
    public TrainerService(Repository<Long, Trainer> repository,Repository<Long, SportiveTrainer> stRepo){
        this.repository = repository;
        this.stRepo=stRepo;
    }

    /**
     * Adds a trainer to the repository.
     *
     * @param trainer
     *            must be not null.
     * @throws IllegalArgumentException
     *             if the given sportive is null.
     * @throws ValidatorException
     *             if the sportive is not valid.
     */
    public void addTrainer(Trainer trainer) throws ValidatorException{
        repository.save(trainer);
    }

    /**
     * Gets all the trainers from the repository.
     *
     * @return A set containing all the trainers.
     */
    public Set<Trainer> getAll(){
        Iterable<Trainer> trainers = repository.findAll();
        return StreamSupport.stream(trainers.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Delete the given trainer
     *
     * @param id
     *              must be an existing id.
     * @throws ValidatorException
     *             if the trainer id is not present.
     */
    public void deleteTrainer(Long id) throws ValidatorException{
        Optional<Trainer> findTrainer = repository.findOne(id);
        if (findTrainer.isEmpty()) {
            throw new ValidatorException("Cannot find ID");
        }
        repository.delete(id);
        Iterable<SportiveTrainer> sportiveTrainerIterable = stRepo.findAll();
        StreamSupport.stream(sportiveTrainerIterable.spliterator(), false)
                .filter(sportiveTrainer -> {return sportiveTrainer.getTrainerID().equals(id);})
                .forEach(sportiveTrainer -> {stRepo.delete(sportiveTrainer.getId());});
    }

    /**
     * Update the given trainer.
     *
     * @param trainer
     *              must be a valid trainer.
     * @throws ValidatorException
     *             if the trainer is not valid.
     */
    public void updateTrainer(Trainer trainer) throws ValidatorException{
        Optional<Trainer> findTrainer = repository.findOne(trainer.getId());
        if (findTrainer.isEmpty()){
            throw new ValidatorException("Cannot find ID");
        }
        repository.update(trainer);
    }


    /**
     * Returns all trainers whose name contain the given string.
     * @param name
     *              given string
     * @return  A Set containing the above trainers.
     */
    public Set<Trainer> filterTrainersByName(String name) {
        Iterable<Trainer> trainers = repository.findAll();
        return StreamSupport.stream(trainers.spliterator(), false)
                .filter(trainer -> (trainer.getFirstName().contains(name) || trainer.getLastName().contains(name)))
                .collect(Collectors.toSet());
    }
}
