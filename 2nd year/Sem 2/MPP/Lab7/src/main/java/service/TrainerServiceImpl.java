package service;

import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.validators.TrainerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TrainerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService{
    public static final Logger log = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerValidator trainerValidator;

    @Override
    public void addTrainer(Trainer trainer) {
        log.trace("addTrainer - method entered trainer={}",trainer);
        trainerValidator.validate(trainer);
//        if (trainerRepository.existsById(trainer.getId()))
//            throw new MyException("Trainer already exists");
        Trainer trainer1 = trainerRepository.save(trainer);
        log.trace("addTrainer - method finished trainer={}", trainer1);
    }

    @Override
    @Transactional
    public void updateTrainer(Trainer trainer) {
        log.trace("updateTrainer - method entered: trainer={}", trainer);
        trainerValidator.validate(trainer);
        if (!trainerRepository.existsById(trainer.getId()))
            throw new MyException("Trainer doesn't exist");
        trainerRepository.findById(trainer.getId())
                .ifPresent(t -> {
                    t.setFirstName(trainer.getFirstName());
                    t.setLastName(trainer.getLastName());
                    t.setAge(trainer.getAge());
                    log.debug("updateTrainer - updated: t={}", t);
                });

        log.trace("updateTrainer - method finished");
    }

    @Override
    public void deleteTrainer(Trainer trainer) {
        log.trace("deleteTrainer - method entered={}",trainer);
        trainerValidator.validate(trainer);
        if (!trainerRepository.existsById(trainer.getId()))
            throw new MyException("Trainer doesn't exist");
        trainerRepository.delete(trainer);
        log.trace("deleteTrainer - method finished");
    }

    @Override
    public List<Trainer> getAllTrainers() {
        log.trace("getAllTrainers --- method entered");
        List<Trainer> trainers = trainerRepository.findAll();
        log.trace("getAllTrainers: trainers={}", trainers);
        return trainers;
    }

    @Override
    public List<Trainer> filterTrainersByName(String name) {
        log.trace("filterTrainersByName - method entered name={}",name);
        return trainerRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getFirstName().contains(name) || trainer.getLastName().contains(name))
                .collect(Collectors.toList());
    }
}
