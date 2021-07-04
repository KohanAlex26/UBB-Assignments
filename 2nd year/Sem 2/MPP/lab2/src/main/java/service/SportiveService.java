package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Services class for materials.
 *
 * @author alex.
 *
 */
public class SportiveService {
    private Repository<Long, Sportive> repo;
    private Repository<Long, SportiveTrainer> stRepo;

    public SportiveService(Repository<Long, Sportive> repo,Repository<Long, SportiveTrainer> stRepo){
        this.repo = repo;
        this.stRepo=stRepo;
    }
    /**
     * Adds a sportive to the repository.
     *
     * @param sportive
     *            must be not null.
     * @throws IllegalArgumentException
     *             if the given sportive is null.
     * @throws ValidatorException
     *             if the sportive is not valid.
     */
    public void addSportive(Sportive sportive) throws ValidatorException{
        repo.save(sportive);
    }

    /**
     * Gets all the sportives from the repository.
     *
     *
     * @return A set containing all the sportives.
     */
    public Set<Sportive> getAll(){
        Iterable<Sportive> sportives = repo.findAll();
        return StreamSupport.stream(sportives.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Delete the given sportive
     *
     * @param id
     *              must be an existing id.
     * @throws ValidatorException
     *             if the sportive id is not present.
     */
    public void deleteSportive(Long id) throws ValidatorException{
        Optional<Sportive> findSportive = repo.findOne(id);
        if (findSportive.isEmpty()) {
            throw new ValidatorException("Cannot find ID");
        }
        repo.delete(id);
        Iterable<SportiveTrainer> sportiveTrainerIterable = stRepo.findAll();
        StreamSupport.stream(sportiveTrainerIterable.spliterator(), false)
                .filter(sportiveTrainer -> {return sportiveTrainer.getSportiveID() == id;})
                .forEach(sportiveTrainer -> {stRepo.delete(sportiveTrainer.getId());});
    }

    /**
     * Update the given sportive.
     *
     * @param sportive
     *              must be a valid sportive.
     * @throws ValidatorException
     *             if the sportive is not valid.
     */
    public void updateSportive(Sportive sportive) throws ValidatorException{
        Optional<Sportive> findSportive = repo.findOne(sportive.getId());
        if (findSportive.isEmpty()){
            throw new ValidatorException("Cannot find ID");
        }
        repo.update(sportive);
    }

    /**
     * Gets all the sportives from the repository with less than a given age.
     *
     * @param age must be a number greater than 0
     *
     * @return A set containing a part of the sportives.
     */
    public Set<Sportive> filterByAge(int age){
        Iterable<Sportive> sportives = repo.findAll();
        return StreamSupport.stream(sportives.spliterator(), false).filter(sportive -> sportive.getAge()<age).collect(Collectors.toSet());
    }

    /**
     * Gets all the sportives from the repository with a given string inside the first name.
     *
     * @param st given string
     *
     * @return A set containing a part of the sportives.
     */
    public Set<Sportive> filterByFirstName(String st){
        Iterable<Sportive> materials = repo.findAll();
        return StreamSupport.stream(materials.spliterator(), false).filter(sportive -> sportive.getFirstName().contains(st)).collect(Collectors.toSet());
    }
    /**
     * Gets all the sportives from the repository with a given team id.
     *
     * @param id given team id
     *
     * @return A set containing a part of the sportives.
     */
    public Set<Sportive> filterByTeamId(int id){
        Iterable<Sportive> materials = repo.findAll();
        return StreamSupport.stream(materials.spliterator(), false).filter(sportive -> sportive.getTeamId()==id).collect(Collectors.toSet());
    }
}
