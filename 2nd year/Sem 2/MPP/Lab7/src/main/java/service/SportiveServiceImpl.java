package service;

import domain.entities.Sportive;
import domain.exceptions.MyException;
import domain.validators.SportiveValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SportiveRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportiveServiceImpl implements  SportiveService{
    public static final Logger log = LoggerFactory.getLogger(SportiveServiceImpl.class);

    @Autowired
    private SportiveValidator validator;

    @Autowired
    private SportiveRepository sportiveRepository;

    @Override
    @Transactional
    public void addSportive(Sportive sportive) throws MyException {
        log.trace("addSportive - method entered sportive={}",sportive);
        validator.validate(sportive);
//        if (sportiveRepository.existsById(sportive.getId()))
//            throw new MyException("Sportive already exists");
        Sportive asg = sportiveRepository.save(sportive);
        log.trace("addSportive - method finished sportive={}",asg);
    }

    @Override
    @Transactional
    public void deleteSportive(Sportive sportive) throws MyException {
        log.trace("deleteSportive - method entered sportive={}",sportive);
        validator.validate(sportive);
        if (!sportiveRepository.existsById(sportive.getId()))
            throw new MyException("Sportive doesn't exist");
        sportiveRepository.delete(sportive);
        log.trace("deleteSportive - method entered finished");
    }

    @Override
    @Transactional
    public void updateSportive(Sportive sportive) throws MyException {
        log.trace("updateSportive - method entered sportive={}",sportive);
        validator.validate(sportive);
        if (!sportiveRepository.existsById(sportive.getId()))
            throw new MyException("Sportive doesn't exist");
        sportiveRepository.findById(sportive.getId())
                .ifPresent(assig -> {
                    assig.setAge(sportive.getAge());
                    assig.setFirstName(sportive.getFirstName());
                    assig.setLastName(sportive.getLastName());
                    assig.setTid(sportive.getTid());
                    log.debug("updateSportive - updated: assig={}",assig);
                });
        log.trace("updateSportive - method finished",sportive);
    }

    @Override
    public List<Sportive> getAllSportives() {
        log.trace("getAllSportives - method entered");
        return sportiveRepository.findAll();
    }

    @Override
    public List<Sportive> filterSportivesByAge(int  age) {
        log.trace("filterSportivesByAge - method entered g={}",age);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getAge()<age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByFirstName(String firstName) {
        log.trace("filterSportivesByFirstName - method entered",firstName);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByTeamId(int teamId) {
        log.trace("filterSportivesByTeamId - method entered",teamId);
        return sportiveRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getTid()==teamId)
                .collect(Collectors.toList());
    }
}
