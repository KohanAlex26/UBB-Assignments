package lab8.core.service;

import lab8.core.model.Sportive;
import lab8.core.model.SportiveTrainer;
import lab8.core.model.Team;
import lab8.core.model.Trainer;
import lab8.core.repository.SportiveRepository;
import lab8.core.repository.SportiveTrainerRepository;
import lab8.core.repository.TrainerRepository;
import lab8.core.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SportiveTrainerServiceImpl implements SportiveTrainerService{

    private static final Logger log = LoggerFactory.getLogger(SportiveTrainerServiceImpl.class);

    @Autowired
    private SportiveTrainerRepository sportiveTrainerRepository;

    @Autowired
    private SportiveRepository sportiveRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public SportiveTrainer saveSportiveTrainer(SportiveTrainer st) {
        log.trace("SportiveTrainerServiceImpl - saveSportiveTrainer - method entered: st={}", st);
        return sportiveTrainerRepository.save(st);
    }

    @Override
    public void deleteSportiveTrainer(Long id) {
        Optional<SportiveTrainer> findSt = sportiveTrainerRepository.findById(id);
        if (findSt.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        log.trace("SportiveTrainererviceImpl - deleteSportiveTrainer - method entered: id={}", id);
        sportiveTrainerRepository.deleteById(id);
        log.trace("SportiveTrainerServiceImpl - deleteSportiveTrainer - method finished");

    }

    @Override
    @Transactional
    public SportiveTrainer updateSportiveTrainer(Long id, SportiveTrainer st) {
        Optional<SportiveTrainer> findSt = sportiveTrainerRepository.findById(st.getId());
        if (findSt.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        try {
            log.trace("SportiveTrainerServiceImpl - updateSportiveTrainer - method entered: st={}", st);

            SportiveTrainer update = sportiveTrainerRepository.findById(id).orElse(st);
            update.setSportiveID(st.getSportiveID());
            update.setTrainerID(st.getTrainerID());
            update.setTrainingType(st.getTrainingType());
            update.setCost(st.getCost());
            return update;
        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public List<SportiveTrainer> getAllSportivesTrainers() {
        return sportiveTrainerRepository.findAll();
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
    public List<Trainer> allTrainersOfOneSportive(Sportive s) {
        log.trace("allTrainersOfOneSportive - method entered s={}", s);
        List<SportiveTrainer> st = sportiveTrainerRepository.findAll().stream().filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toList());
        return st.stream()
                .map(x -> getOneTrainer(x.getTrainerID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> allSportivesOfOneTrainer(Trainer t) {
        log.trace("allSportivesOfOneTrainer - method entered t={}", t);
        List<SportiveTrainer> st = sportiveTrainerRepository.findAll().stream().filter(sp -> sp.getTrainerID().equals(t.getId())).collect(Collectors.toList());
        return st.stream()
                .map(x -> getOneSportive(x.getSportiveID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> allTrainingTypesOfOneSportive(Sportive s) {
        log.trace("allTrainingTypesOfOneSportive - method entered s={}", s);
        List<SportiveTrainer> st = sportiveTrainerRepository.findAll().stream().filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toList());
        return st.stream()
                .map(SportiveTrainer::getTrainingType)
                .collect(Collectors.toList());
    }

    @Override
    public SportiveTrainer mostExpensiveTraining() {
        log.trace("mostExpensiveTraining - method entered");
        Set<Integer> costs = sportiveTrainerRepository.findAll().stream().map(SportiveTrainer::getCost).collect(Collectors.toSet());
        int maxCost = Collections.max(costs);
        Set<SportiveTrainer> str = new HashSet<>(sportiveTrainerRepository.findAll());
        return str.stream().filter(x -> x.getCost() == maxCost).findAny().orElseThrow();

    }
}
