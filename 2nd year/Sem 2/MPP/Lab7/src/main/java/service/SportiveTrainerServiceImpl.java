package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.validators.SportiveTrainerValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SportiveRepository;
import repository.SportiveTrainerRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TrainerRepository;

@Service
public class SportiveTrainerServiceImpl implements SportiveTrainerService{
    public static final Logger log = LoggerFactory.getLogger(SportiveTrainerServiceImpl.class);

    @Autowired
    private SportiveTrainerValidator validator;

    @Autowired
    private SportiveTrainerRepository stRepository;

    @Autowired
    private SportiveRepository sportiveRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    @Transactional
    public void addSportiveTrainer(SportiveTrainer st) throws MyException {
        log.trace("addSportiveTrainer - method entered sportiveTrainer={}", st);
        validator.validate(st);
//        if(stRepository.existsById(st.getId())){
//            throw new MyException("SportiveTrainer already exists");
//        }
        SportiveTrainer asg = stRepository.save(st);
        log.trace("addSportiveTrainer - method finished sportiveTrainer={}", asg);
    }

    @Override
    @Transactional
    public void deleteSportiveTrainer(SportiveTrainer st) throws MyException {
        log.trace("deleteSportiveTrainer - method entered sportiveTrainer{}", st);
        validator.validate(st);
        if(!stRepository.existsById(st.getId())){
            throw new MyException("SportiveTrainer does'nt exist");
        }
        stRepository.delete(st);
        log.trace("deleteSportiveTrainer - method finished");
    }

    @Override
    @Transactional
    public void updateSportiveTrainer(SportiveTrainer st) throws MyException {
        log.trace("updateSportiveTrainer - method entered sportive={}",st);
        validator.validate(st);
        if (!stRepository.existsById(st.getId()))
            throw new MyException("SportiveTrainer doesn't exist");
        stRepository.findById(st.getId())
                .ifPresent(assig -> {
                    assig.setSportiveID(st.getSportiveID());
                    assig.setTrainerID(st.getTrainerID());
                    assig.setTrainingType(st.getTrainingType());
                    assig.setCost(st.getCost());
                    log.debug("updateSportiveTrainer - updated: assig={}",assig);
                });
        log.trace("updateSportiveTrainer - method finished", st);
    }

    @Override
    public List<SportiveTrainer> getAllSportivesTrainers() {
        log.trace("getAllSportivesTrainers - method entered");
        return stRepository.findAll();
    }

    @Override
    public Sportive getOneSportive(Long id) {
        return sportiveRepository.findById(id).get();
    }

    @Override
    public Trainer getOneTrainer(Long id) {
        return trainerRepository.findById(id).get();
    }

    @Override
    public List<Trainer> allTrainersOfOneSportive(Sportive s) throws MyException {
        log.trace("allTrainersOfOneSportive - method entered s={}", s);
        List<SportiveTrainer> st = stRepository.findAll().stream().filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toList());
        return st.stream()
                .map(x -> getOneTrainer(x.getTrainerID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> allSportivesOfOneTrainer(Trainer t) throws MyException {
        log.trace("allSportivesOfOneTrainer - method entered t={}", t);
        List<SportiveTrainer> st = stRepository.findAll().stream().filter(sp -> sp.getTrainerID().equals(t.getId())).collect(Collectors.toList());
        return st.stream()
                .map(x -> getOneSportive(x.getSportiveID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> allTrainingTypesOfOneSportive(Sportive s) throws MyException {
        log.trace("allTrainingTypesOfOneSportive - method entered s={}", s);
        List<SportiveTrainer> st = stRepository.findAll().stream().filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toList());
        return st.stream()
                .map(SportiveTrainer::getTrainingType)
                .collect(Collectors.toList());
    }

    @Override
    public SportiveTrainer mostExpensiveTraining() throws MyException {
        log.trace("mostExpensiveTraining - method entered");
        Set<Integer> costs = stRepository.findAll().stream().map(SportiveTrainer::getCost).collect(Collectors.toSet());
        int maxCost = Collections.max(costs);
        Set<SportiveTrainer> str = new HashSet<>(stRepository.findAll());
        return str.stream().filter(x -> x.getCost() == maxCost).findAny().orElseThrow();

    }
}
