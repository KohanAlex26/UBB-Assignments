package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Sportive;
import ro.ubb.catalog.core.repository.SportiveRepository;
import ro.ubb.catalog.core.validators.SportiveValidator;

import java.util.List;
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
        log.trace("getAllSportives - method entered");
        return sportiveRepository.findAll();
    }

    @Override
    public Sportive saveSportive(Sportive sportive) {
        log.trace("SportiveServiceImpl - saveSportive - method entered: sportive={}", sportive);
        validator.validate(sportive);
        return sportiveRepository.save(sportive);
    }
}
