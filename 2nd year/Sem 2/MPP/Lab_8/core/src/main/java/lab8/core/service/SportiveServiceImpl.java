package lab8.core.service;

import lab8.core.model.Sportive;
import lab8.core.repository.SportiveRepository;
import lab8.core.validators.SportiveValidator;
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
public class SportiveServiceImpl implements SportiveService {

    private static final Logger log = LoggerFactory.getLogger(SportiveServiceImpl.class);

    @Autowired
    private SportiveValidator validator;

    @Autowired
    private SportiveRepository sportiveRepository;


    @Override
    public List<Sportive> getAllSportives() {
        //todo: logs
        log.trace("getAllSportives - method entered");
        return sportiveRepository.findAll();
    }

    @Override
    public Sportive saveSportive(Sportive sportive) {
        log.trace("SportiveServiceImpl - saveSportive - method entered: sportive={}", sportive);
        validator.validate(sportive);
        return sportiveRepository.save(sportive);
    }

    @Override
    @Transactional
    public Sportive updateSportive(Long id,Sportive sportive) {
        Optional<Sportive> findSportive = sportiveRepository.findById(sportive.getId());
        if (findSportive.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        try {
            log.trace("SportiveServiceImpl - updateSportive - method entered: sportive={}", sportive);
            validator.validate(sportive);
            Sportive update = sportiveRepository.findById(id).orElse(sportive);
            update.setAge(sportive.getAge());
            update.setFirstName(sportive.getFirstName());
            update.setLastName(sportive.getLastName());
            update.setTid(sportive.getTid());
            return update;
        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public void deleteSportive(Long id) {
        Optional<Sportive> findSportive = sportiveRepository.findById(id);
        if (findSportive.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }
        log.trace("SportiveServiceImpl - deleteSportive - method entered: id={}", id);
        sportiveRepository.deleteById(id);
        log.trace("SportiveServiceImpl - deleteSportive - method finished");
    }

    @Override
    public List<Sportive> filterSportivesByAge(int age) {
        log.trace("filterSportivesByAge - method entered age={}",age);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getAge()<=age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByFirstName(String firstName) {
        log.trace("filterSportivesByFirstName - method entered firstName={}",firstName);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByTeamId(int teamId) {
        log.trace("filterSportivesByTeamId - method entered teamId={}",teamId);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getTid()==teamId)
                .collect(Collectors.toList());
    }
}
