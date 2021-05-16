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
import ro.ubb.core.repository.SportiveRepository;
import ro.ubb.core.validators.SportiveValidator;
import ro.ubb.core.validators.ValidatorException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SportiveService implements ISportiveService{
    public static final Logger log = LoggerFactory.getLogger(SportiveService.class);

    @Autowired
    private SportiveValidator validator;

    @Autowired
    private SportiveRepository sportiveRepository;
    


    @Override
    @Transactional
    public void addSportive(Sportive sportive) throws ValidatorException {
        log.trace("addSportive - method entered sportive={}", sportive);
        validator.validate(sportive);
        if (sportiveRepository.existsById(sportive.getId()))
            throw new ValidatorException("Sportive already exists");
        Sportive std = sportiveRepository.save(sportive);
        log.trace("addSportive - method finished sportive={}", std);
    }

    @Override
    @Transactional
    public void deleteSportive(Long id) throws ValidatorException {
        log.trace("deleteSportive - method entered={}",id);
        if (!sportiveRepository.existsById(id))
            throw new ValidatorException("Sportive doesn't exist");
        sportiveRepository.delete(sportiveRepository.getOne(id));
        log.trace("deleteSportive - method finished");
    }

    @Override
    @Transactional
    public void updateSportive(Sportive sportive) throws ValidatorException {
        log.trace("updateSportive - method entered: sportive={}", sportive);
        validator.validate(sportive);
        if (!sportiveRepository.existsById(sportive.getId()))
            throw new ValidatorException("Sportive doesn't exist");
        sportiveRepository.findById(sportive.getId())
                .ifPresent(s -> {
                    s.setFirstName(sportive.getFirstName());
                    s.setLastName(sportive.getLastName());
                    s.setAge(sportive.getAge());
                    s.setTid(sportive.getTid());
                    log.debug("updateSportive - updated: s={}", s);
                });
        log.trace("updateSportive - method finished");
    }

    @Override
    public Sportive getSportiveById(long id) throws ValidatorException {
        log.trace("getSportiveById - method entered={}", id);
        return sportiveRepository.findById(id).get();
    }

    @Override
    public List<Sportive> getAllSportives() {
        log.trace("getAllSportives - method entered");
        return sportiveRepository.findAll();
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

    @Override
    public List<Sportive> sortSportivesAscendingByFirstName() {
        log.trace("sortSportivesAscendingByName - method entered");
        Iterable<Sportive> sportives = sportiveRepository.findAll(Sort.by("firstName").ascending());
        return StreamSupport.stream(sportives.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> getSportivesOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Sportive> pagedResult = sportiveRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
