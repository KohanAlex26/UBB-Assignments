package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import repository.Repository;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for SportiveTrainer.
 *
 * @author dzen.
 *
 */
public class SportiveTrainerService {
    private Repository<Long, SportiveTrainer> repo;
    private Repository<Long, Sportive> repoSportive;
    private Repository<Long, Trainer> repoTrainer;

    /**
     * Constructs with given arguments
     *
     * @param repo         the repository for SportiveTrainer
     * @param repoSportive the repository for Sportive
     * @param repoTrainer  the repository for Trainer
     */
    public SportiveTrainerService(Repository<Long, SportiveTrainer> repo, Repository<Long, Sportive> repoSportive, Repository<Long, Trainer> repoTrainer) {
        this.repo = repo;
        this.repoSportive = repoSportive;
        this.repoTrainer = repoTrainer;
    }

    /**
     * Associate a sportive with a trainer
     *
     * @param s            a sportive
     * @param t            a trainer
     * @param trainingType a training type
     * @param cost         a cost
     */
    public void associateSportiveTrainer(Long id, Sportive s, Trainer t, String trainingType, int cost) throws ValidatorException {
        SportiveTrainer st = new SportiveTrainer(id, s.getId(), t.getId(), trainingType, cost);
        repo.save(st);
    }

    /**
     * Gets all the relations sporive-trainer from the repository.
     *
     * @return A set containing all the entities sportive-trainer
     */
    public Set<SportiveTrainer> getAll(){
        Iterable<SportiveTrainer> st = repo.findAll();
        return StreamSupport.stream(st.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Gets all the associations sporive-trainer from the repository.
     *
     * @return A set of strings containing all the entities sportive-trainer
     */
    public Set<String> getAssociations(){
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<SportiveTrainer> st = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toSet());
        return st.stream()
                 .map(x -> "ID: " + x.getId() + "-> " + getOneSportive(x.getSportiveID()).get().toString() + "\n \t + \n" + getOneTrainer(x.getTrainerID()).get().toString() + "; type: " + x.getTrainingType() + "; cost: " + x.getCost() + "\n ---").collect(Collectors.toSet());
    }

    /**
     * Dissociate a sportive and a trainer
     *
     * @param id The id of the supposed association to be deleted
     *
     *  @throws ValidatorException if the sportive is not valid.
     */
    public void dissociationSportiveTrainer(Long id) throws ValidatorException {
        repo.delete(id);
    }

    /**
     * Gets one sportive based on a specific id given
     *
     * @param id The id of the supposed sporive to be found
     *
     * @return A sportive.
     */
    public Optional<Sportive> getOneSportive(Long id) {
        return repoSportive.findOne(id);
    }

    /**
     * Gets one trainer based on a specific id given
     *
     * @param id The id of the supposed trainer to be found
     *
     * @return A trainer.
     */
    public Optional<Trainer> getOneTrainer(Long id) {
        return repoTrainer.findOne(id);
    }

    /**
     * Gets all the trainers of a specific sportive
     *
     * @param s A sportive for which all the trainers are searched.
     *
     * @return A set containing all the trainers of a sportive s.
     */
    public Set<Trainer> allTrainersOfOneSportive(Sportive s) {
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<SportiveTrainer> st = StreamSupport.stream(all.spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
        return st.stream().map(x -> getOneTrainer(x.getTrainerID()).get()).collect(Collectors.toSet());
    }

    /**
     * Gets all the sportives of a specific trainer
     *
     * @param t A trainer for which all the sportives are searched.
     *
     * @return A set containing all the sportives of a trainer t.
     */
    public Set<Sportive> allSportivesOfOneTrainer(Trainer t) {
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<SportiveTrainer> st = StreamSupport.stream(all.spliterator(), false).filter(sp -> sp.getTrainerID().equals(t.getId())).collect(Collectors.toSet());
        return st.stream().map(x -> getOneSportive(x.getSportiveID()).get()).collect(Collectors.toSet());
    }

    /**
     * Gets all the training types of a specific sportive
     *
     * @param s A sportive for which all the training types are searched
     *
     * @return A set containing all the training types of a sportive s.
     */
    public Set<String> allTrainingTypesOfOneSportive(Sportive s) {
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<SportiveTrainer> st = StreamSupport.stream(all.spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
        return st.stream().map(SportiveTrainer::getTrainingType).collect(Collectors.toSet());
    }

    /**
     * Gets the maximum cost of a training
     *
     * @return the maximum cost of a training
     */
    public int maxCost(){
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<Integer> costs = StreamSupport.stream(all.spliterator(), false).map(SportiveTrainer::getCost).collect(Collectors.toSet());
        return Collections.max(costs);
    }

    /**
     * Gets the most expensive training (as an association between a sportive and a trainer)
     *
     * @return A string built based on the association of a sportive and a trainer
     */
    public String mostExpensiveTraining() throws NoSuchElementException {
        Iterable<SportiveTrainer> all = repo.findAll();
        Set<SportiveTrainer> str = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toSet());
        SportiveTrainer st = str.stream().filter(x -> x.getCost() == this.maxCost()).findAny().orElseThrow();
        return "ID: " + st.getId() + "-> " + getOneSportive(st.getSportiveID()).get() + "\n \t + "
                + getOneTrainer(st.getTrainerID()).get() + "; type: " + st.getTrainingType() + "; cost: " + st.getCost();
    }
}
