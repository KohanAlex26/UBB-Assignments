package lab8.core.service;

import lab8.core.model.Trainer;
import lab8.core.repository.TrainerRepository;
import lab8.core.validators.TrainerValidator;
import lab8.core.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService{
    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerValidator trainerValidator;


    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public List<Trainer> filterTrainersByName(String name) {
        log.trace("filterTrainersByName - method entered name={}",name);
        return trainerRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getFirstName().contains(name) || trainer.getLastName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        log.trace("TrainerServiceImpl - saveTrainer - method entered: trainer={}", trainer);
        trainerValidator.validate(trainer);
        return trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public Trainer updateTrainer(Long id, Trainer trainer) {
        Optional<Trainer> findTrainer = trainerRepository.findById(trainer.getId());
        if (findTrainer.isEmpty())
            throw new ValidatorException("Cannot find id");

        try {
            log.trace("TrainerServiceImpl - updateTrainer - method entered: trainer={}", trainer);

            Trainer update = trainerRepository.findById(id).orElse(trainer);
            update.setAge(trainer.getAge());
            update.setFirstName(trainer.getFirstName());
            update.setLastName(trainer.getLastName());
            return update;
        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public void deleteTrainerById(Long id) {
        Optional<Trainer> findTrainer = trainerRepository.findById(id);
        if (findTrainer.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        log.trace("TrainerServiceImpl - deleteTrainer - method entered: id={}", id);
        trainerRepository.deleteById(id);
        log.trace("TrainerServiceImpl - deleteTrainer - method finished");
    }
}
