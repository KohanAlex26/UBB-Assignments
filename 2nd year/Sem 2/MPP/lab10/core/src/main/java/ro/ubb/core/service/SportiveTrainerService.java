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
import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.SportiveTrainer;
import ro.ubb.core.model.Trainer;
import ro.ubb.core.repository.SportiveRepository;
import ro.ubb.core.repository.SportiveTrainerRepository;
import ro.ubb.core.repository.TrainerRepository;
import ro.ubb.core.validators.SportiveTrainerValidator;
import ro.ubb.core.validators.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SportiveTrainerService implements ISportiveTrainerService{
    public static final Logger log = LoggerFactory.getLogger(SportiveTrainerService.class);

    @Autowired
    private SportiveTrainerRepository sportiveTrainerRepository;

    @Autowired
    private SportiveRepository sportiveRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public SportiveTrainer getSportiveTrainerById(long id) throws ValidatorException {
        log.trace("getSportiveTrainerById - method entered={}", id);
        return sportiveTrainerRepository.findById(id).get();
    }

    @Override
    public List<SportiveTrainer> getAllSportivesTrainers() {
        log.trace("getAllSportiveTrainers - method entered");
        return sportiveTrainerRepository.findAll();
    }

    @Override
    public List<SportiveTrainer> sortSportivesTrainersAscendingByCost() {
        log.trace("sortSportiveTrainersAscendingByCost - method entered");
        Iterable<SportiveTrainer> sportives = sportiveTrainerRepository.findAll(Sort.by("cost").ascending());
        return StreamSupport.stream(sportives.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<SportiveTrainer> getSportivesTrainersOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<SportiveTrainer> pagedResult = sportiveTrainerRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
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
    public List<Trainer> allTrainersOfOneSportive(Long id) {
        log.trace("filterTrainersByOneSportive - method entered id={}",id);
        return sportiveTrainerRepository.findAll()
                .stream()
                .filter(sp -> sp.getSportiveID()==id)
                .map(x -> getOneTrainer(x.getTrainerID()))
                .collect(Collectors.toList());
    }
}
