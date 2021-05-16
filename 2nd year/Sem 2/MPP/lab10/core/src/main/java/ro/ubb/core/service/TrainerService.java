package ro.ubb.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.core.model.Trainer;
import ro.ubb.core.repository.TrainerRepository;
import ro.ubb.core.validators.TrainerValidator;
import ro.ubb.core.validators.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrainerService implements ITrainerService{
    public static final Logger log = LoggerFactory.getLogger(TrainerService.class);

    @Autowired
    private TrainerValidator validator;

    @Autowired
    private TrainerRepository trainerRepository;



    @Override
    @Transactional
    public void addTrainer(Trainer trainer) throws ValidatorException {
        log.trace("addTrainer - method entered trainer={}", trainer);
        validator.validate(trainer);
        if (trainerRepository.existsById(trainer.getId()))
            throw new ValidatorException("Trainer already exists");
        Trainer std = trainerRepository.save(trainer);
        log.trace("addTrainer - method finished trainer={}", std);
    }

    @Override
    @Transactional
    public void deleteTrainer(Long id) throws ValidatorException {
        log.trace("deleteTrainer - method entered={}",id);
        if (!trainerRepository.existsById(id))
            throw new ValidatorException("Trainer doesn't exist");
        trainerRepository.delete(trainerRepository.getOne(id));
        log.trace("deleteTrainer - method finished");
    }

    @Override
    @Transactional
    public void updateTrainer(Trainer trainer) throws ValidatorException {
        log.trace("updateTrainer - method entered: trainer={}", trainer);
        validator.validate(trainer);
        if (!trainerRepository.existsById(trainer.getId()))
            throw new ValidatorException("Trainer doesn't exist");
        trainerRepository.findById(trainer.getId())
                .ifPresent(s -> {
                    s.setFirstName(trainer.getFirstName());
                    s.setLastName(trainer.getLastName());
                    s.setAge(trainer.getAge());
                    log.debug("updateTrainer - updated: s={}", s);
                });
        log.trace("updateTrainer - method finished");
    }

    @Override
    public Trainer getTrainerById(long id) throws ValidatorException {
        log.trace("getTrainerById - method entered={}", id);
        return trainerRepository.findById(id).get();
    }

    @Override
    public List<Trainer> getAllTrainers() {
        log.trace("getAllTrainers - method entered");
        return trainerRepository.findAll();

    }

    @Override
    public List<Trainer> filterTrainersByName(String name) {
        log.trace("filterTrainersByName - method entered age={}",name);
        return trainerRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getFirstName().contains(name))
                .collect(Collectors.toList());
    }



    @Override
    public List<Trainer> sortTrainersAscendingByFirstName() {
        log.trace("sortTrainersAscendingByName - method entered");
        Iterable<Trainer> trainers = trainerRepository.findAll(Sort.by("firstName").ascending());
        return StreamSupport.stream(trainers.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Trainer> getTrainersOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Trainer> pagedResult = trainerRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
