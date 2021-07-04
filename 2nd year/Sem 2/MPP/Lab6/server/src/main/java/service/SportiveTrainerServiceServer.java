package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RepositoryInterface;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportiveTrainerServiceServer implements SportiveTrainerServiceInterface{

    @Autowired
    Validator<SportiveTrainer> validator;

    @Autowired
    RepositoryInterface<Long, SportiveTrainer> repository;

    @Autowired
    RepositoryInterface<Long, Sportive> sportiveRepo;

    @Autowired
    RepositoryInterface<Long, Trainer> trainerRepo;



    @Override
    public void addSportiveTrainer(SportiveTrainer st) throws MyException {
        this.validator.validate(st);
        if (this.repository.save(st).isPresent()) {
            throw new MyException("SportiveTrainer already exists!");
        }
    }

    @Override
    public void deleteSportiveTrainer(SportiveTrainer st) throws MyException {
        this.validator.validate(st);
        if (!this.repository.delete(st.getId()).isPresent()) {
            throw new MyException("SportiveTrainer does not exist!");
        }
    }

    @Override
    public void updateSportiveTrainer(SportiveTrainer st) throws MyException {
        this.validator.validate(st);
        if (this.repository.update(st).isPresent()) {
            throw new MyException("SportiveTrainer does not exist!");
        }
    }

    @Override
    public void associateSportiveTrainer(SportiveTrainer st) throws MyException {
        this.validator.validate(st);
        if (this.repository.save(st).isPresent()) {
            throw new MyException("SportiveTrainer already exists!");
        }
    }

    @Override
    public void dissociateSportiveTrainer(Long id) throws MyException {
            if (!this.repository.delete(id).isPresent()) {
                throw new MyException("SportiveTrainer does not exist!");
            }
    }

    @Override
    public Set<SportiveTrainer> getAllSportivesTrainers() {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                            .collect(Collectors.toSet());
    }

    @Override
    public Optional<Sportive> getOneSportive(Long id) {
            return sportiveRepo.findById(id);
    }

    @Override
    public Optional<Trainer> getOneTrainer(Long id) {
        return trainerRepo.findById(id);
    }

    @Override
    public Set<Trainer> allTrainersOfOneSportive(Sportive s) throws MyException {
        Set<SportiveTrainer> st = StreamSupport.stream(repository.getAll().spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
        return st.stream()
                .map(x -> getOneTrainer(x.getTrainerID()).get())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Sportive> allSportivesOfOneTrainer(Trainer t) throws MyException {
        Set<SportiveTrainer> st = StreamSupport.stream(repository.getAll().spliterator(), false).filter(sp -> sp.getTrainerID().equals(t.getId())).collect(Collectors.toSet());
        return st.stream()
                .map(x -> getOneSportive(x.getSportiveID()).get())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> allTrainingTypesOfOneSportive(Sportive s) throws MyException {
        Set<SportiveTrainer> st = StreamSupport.stream(repository.getAll().spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
        return st.stream()
                .map(SportiveTrainer::getTrainingType)
                .collect(Collectors.toSet());
    }

    @Override
    public SportiveTrainer mostExpensiveTraining() throws MyException {
        Set<Integer> costs = StreamSupport.stream(this.repository.getAll().spliterator(), false).map(SportiveTrainer::getCost).collect(Collectors.toSet());
        int maxCost = Collections.max(costs);
        Set<SportiveTrainer> str = StreamSupport.stream(this.repository.getAll().spliterator(), false).collect(Collectors.toSet());
        SportiveTrainer st = str.stream().filter(x -> x.getCost() == maxCost).findAny().orElseThrow();
        return st;

    }
}
