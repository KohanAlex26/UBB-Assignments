package core.service;


import core.domain.Award;
import core.domain.SportiveTrainer;
import core.domain.Trainer;
import core.exceptions.SportException;
import core.repository.trainer.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Math.max;

@Service
public class TrainerServiceImpl implements TrainerService {
    public static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Value("${method}")
    private String method;

    @Override
    public void addTrainer(String firstName, String lastName, int age, Award award) {
        logger.trace("addTrainer - method entered - firstName: " + firstName + ", lastName: " + lastName+ ", age: " + age);
        long id = 0;
        for (Trainer trainer : this.trainerRepository.findAll())
            id = max(id, trainer.getId() + 1);
        Trainer trainerToBeAdded = new Trainer(id, firstName, lastName, age);
        if (award != null)
            trainerToBeAdded.setAward(award);
        trainerRepository.save(trainerToBeAdded);
        logger.trace("addTrainer - method finished");

    }
//    @Override
//    public void addTrainer(String firstName, String lastName, int age) {
//        logger.trace("addTrainer - method entered - firstName: " + firstName + ", lastName: " + lastName+ ", age: " + age);
//        long id = 0;
//        for (Trainer trainer : this.trainerRepository.findAll())
//            id = max(id, trainer.getId() + 1);
//        Trainer trainerToBeAdded = new Trainer(id, firstName, lastName, age);
//        trainerRepository.save(trainerToBeAdded);
//        logger.trace("addTrainer - method finished");
//
//    }

    @Override
    public Trainer getTrainerById(Long id) {
        logger.trace("getTrainerById - method entered={}", id);
        return trainerRepository.findById(id).get();
    }

    @Override
    public List<Trainer> getAllTrainers() {
        logger.trace("getAllTrainers - method entered");
        return trainerRepository.findAll();

    }

    @Override
    public List<Trainer> getTrainersFromRepository() {
        logger.trace("getTrainersFromRepository - method entered");
        List<Trainer> trainers = trainerRepository.findAll();
        logger.trace("getTrainersFromRepository: " + trainers);
        return trainers;

    }


    @Override
    public void deleteTrainer(Long id) {
        logger.trace("deleteTrainer - method entered - id: " + id);

        trainerRepository.findById(id)
                .ifPresentOrElse((trainer) -> trainerRepository.deleteById(trainer.getId()),
                        () -> {
                            throw new SportException("Trainer does not exist");
                        });
        logger.trace("deleteTrainer - method finished");

    }

//    @Override
//    @Transactional
//    public void updateTrainer(Long id, String firstName, String lastName, int age, Award award) {
//        logger.trace("updateTrainer - method entered - id: " + id + ", firstName: " + firstName + ", lastName: " + lastName+ ", age: " + age);
//
//        trainerRepository.findById(id)
//                .ifPresentOrElse(
//                        (trainer) -> {
//                            trainer.setFirstName(firstName);
//                            trainer.setLastName(lastName);
//                            trainer.setAge(age);
//                            trainer.setAward(award);
//                        },
//                        () -> {
//                            throw new SportException("Trainer does not exist");
//                        }
//                );
//
//        logger.trace("updateTrainer - method finished");
//    }

    @Override
    @Transactional
    public void updateTrainer(Long id, String firstName, String lastName, int age, Award award) {
        logger.trace("updateTrainer - method entered - id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", age: " + age +
                ", award: " + award);

        trainerRepository.findById(id)
                .ifPresent((trainer) -> {
                            trainer.setFirstName(firstName);
                            trainer.setLastName(lastName);
                            trainer.setAge(age);
                            trainer.setAward(award);
                        }
                );
        logger.trace("updateTrainer - method finished");
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

    @Override
    public List<Trainer> filterTrainersByName(String name) {
        logger.trace("filterTrainersByName - method entered age={}",name);
        return trainerRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getFirstName().contains(name))
                .collect(Collectors.toList());
    }



    @Override
    public List<Trainer> sortTrainersAscendingByFirstName() {
        logger.trace("sortTrainersAscendingByName - method entered");
        Iterable<Trainer> trainers = trainerRepository.findAll(Sort.by("firstName").ascending());
        return StreamSupport.stream(trainers.spliterator(),false)
                .collect(Collectors.toList());
    }


    @Override
    public List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerId(long trainerId) {
        System.out.println(method);
        switch (method) {
            case "jpql": {
                return trainerRepository.findSportiveTrainerOfTrainerByTrainerIdJPQL(trainerId);
            }
//            case "criteria": {
//                return trainerRepository.findSportiveTrainerOfTrainerByTrainerIdCriteria(trainerId);
//            }
            case "native": {
                return trainerRepository.findSportiveTrainerOfTrainerByTrainerIdNative(trainerId);
            }
            default: throw new SportException("invalid setting");
        }
    }


}
