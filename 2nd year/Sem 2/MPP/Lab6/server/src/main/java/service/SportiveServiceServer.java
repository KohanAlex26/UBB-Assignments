package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import domain.validators.SportiveValidator;
import repository.RepositoryInterface;
import repository.SportiveRepository;
import repository.SportiveTrainerRepository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportiveServiceServer implements SportiveServiceInterface{
    @Autowired
    Validator<Sportive> validator;

    @Autowired
    RepositoryInterface<Long, Sportive> repository;

    @Autowired
    SportiveServiceInterface sportiveService;


    @Override
    public void addSportive(Sportive sportive) throws MyException {
        this.validator.validate(sportive);
        if(this.repository.save(sportive).isPresent())
            throw new MyException("Sportive already exists");
    }

    @Override
    public void deleteSportive(Long id) throws MyException {
//        this.validator.validate(sportive);
        if(!this.repository.delete(id).isPresent())
            throw new MyException("Sportive does not exist");
    }

    @Override
    public void updateSportive(Sportive sportive) throws MyException {
        this.validator.validate(sportive);
        if(this.repository.update(sportive).isPresent())
            throw new MyException("Sportive does not exist");
    }

    @Override
    public Sportive getSportiveById(long id) throws MyException {
        Optional<Sportive> result = this.repository.findById(id);
        if(result.isPresent())
            return result.get();
        else
            throw new MyException("Sportive does not exist");
    }

    @Override
    public Set<Sportive> getAllSportives() {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Sportive> filterSportivesByAge(int age) {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .filter(sportive -> sportive.getAge()<age)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Sportive> filterSportivesByFirstName(String firstName) {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .filter(sportive -> sportive.getFirstName().contains(firstName))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Sportive> filterSportivesByTeamId(int teamId) {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .filter(sportive -> sportive.getTeamId()==teamId)
                .collect(Collectors.toSet());
    }


}
