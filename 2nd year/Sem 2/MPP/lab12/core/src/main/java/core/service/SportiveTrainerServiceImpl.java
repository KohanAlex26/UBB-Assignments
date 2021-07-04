package core.service;

import core.domain.SportiveTrainer;
import core.domain.Trainer;
import core.exceptions.SportException;
import core.repository.sportive.SportiveRepository;
import core.repository.trainer.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.domain.Sportive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class SportiveTrainerServiceImpl implements SportiveTrainerService {
    public static final Logger logger = LoggerFactory.getLogger(SportiveTrainer.class);

    @Autowired
    private SportiveRepository sportiveRepository;

    @Autowired
    private TrainerRepository trainerRepository;

//    @Autowired
//    private SportiveTrainerRepository sportiveTrainerRepository;

    @Override
    @Transactional
    public void addSportiveTrainer(Long sportiveId, Long trainerId, int cost, String trainingType) {
        logger.trace("addSportiveTrainer - method entered - sportiveId: " + sportiveId + ", trainerId: " + trainerId + ", cost: " + cost + ", date trainingType: " + trainingType);
        Optional<Sportive> sportive = sportiveRepository.findById(sportiveId);
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        if (sportive.isPresent() && trainer.isPresent()) {
            sportive.get().addSportiveTrainer(trainer.get(), cost, trainingType);
        } else throw new SportException("Invalid sportiveTrainer");
        logger.trace("addSportiveTrainer - method finished");

    }

    @Override
    public List<SportiveTrainer> getAllSportivesTrainers() {
        logger.trace("getSportiveTrainersFromRepository - method entered");
        List<SportiveTrainer> sportiveTrainers = new ArrayList<>();
        for (var sportive : sportiveRepository.findAll())
            sportiveTrainers.addAll(sportive.getSportiveTrainer());
//        logger.trace("getSportiveTrainersFromRepository: " + sportiveTrainers);
        return sportiveTrainers;
    }

    @Override
    public List<SportiveTrainer> getSportiveTrainersFromRepository() {
        logger.trace("getSportiveTrainersFromRepository - method entered");
        List<SportiveTrainer> sportiveTrainers = new ArrayList<>();
        for (var sportive : sportiveRepository.findAll())
            sportiveTrainers.addAll(sportive.getSportiveTrainer());
//        logger.trace("getSportiveTrainersFromRepository: " + sportiveTrainers);
        return sportiveTrainers;
    }

//    @Override
//    public List<SportiveTrainer> getSportivesTrainersOnPage(int pageNo, int pageSize) {
//        Pageable page = PageRequest.of(pageNo, pageSize);
//        Page<SportiveTrainer> pagedResult = sportiveTrainerRepository.findAll(page);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<>();
//        }
//    }

}
