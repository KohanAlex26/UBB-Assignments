package core.service;

import core.domain.Team;
import core.exceptions.SportException;
import core.repository.SportiveRepository;
import core.domain.Sportive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SportiveServiceImpl implements SportiveService {
    public static final Logger logger = LoggerFactory.getLogger(SportiveServiceImpl.class);

    @Autowired
    private SportiveRepository sportivesRepository;

    @Override
    public void addSportive(String firstName, String lastName, int age, Team team) {
        long id = 0;
        for (Sportive sportive : this.sportivesRepository.findAll())
            id = max(id, sportive.getId() + 1);
        Sportive sportive = new Sportive(id, firstName, lastName,age);
        sportive.setTeam(team);
        sportivesRepository.save(sportive);
    }

    @Override
    public Sportive getSportiveById(Long id) {
        logger.trace("getSportiveById - method entered={}", id);
        return sportivesRepository.findById(id).get();
    }

    @Override
    public List<Sportive> getSportivesFromRepository() {
        return sportivesRepository.findAll();
    }

    @Override
    public List<Sportive> getSportivesOfTeam(Long teamId) {
        return sportivesRepository.findAll().stream().filter(toy -> toy.getTeam().getId().equals(teamId)).collect(Collectors.toList());
    }

//    @Override
//    public List<Sportive> getSportivesByFirstName(String firstName) {
//        return sportivesRepository.findAll().stream().filter(sportive -> sportive.getFirstName().equals(firstName)).collect(Collectors.toList());
//    }
    @Override
    public List<Sportive> getAllSportives() {
        logger.trace("getAllSportives - method entered");
        return sportivesRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteSportive(Long id) {
        sportivesRepository.findById(id)
                .ifPresentOrElse((sportive) -> {
                            sportive.getTeam().getSportives().remove(sportive);
                            sportivesRepository.deleteById(sportive.getId());
                        },
                        () -> {
                            throw new SportException("Sportive does not exist");
                        }
                );
    }

    @Override
    @Transactional
    public void updateSportive(Long id, String firstName, String lastName, int age, Team team) {
        sportivesRepository.findById(id).ifPresentOrElse((sportive -> {
            sportive.setFirstName(firstName);
            sportive.setLastName(lastName);
            sportive.setAge(age);
            sportive.setTeam(team);
        }), ()->{
            throw new SportException("Nonexistent sportive");
        });
    }

    @Override
    public List<Sportive> getSportivesOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Sportive> pagedResult = sportivesRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
    @Override
    public List<Sportive> filterSportivesByAge(int age) {
        logger.trace("filterSportivesByAge - method entered age={}",age);
        return sportivesRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getAge()<=age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByFirstName(String firstName) {
        logger.trace("filterSportivesByFirstName - method entered firstName={}",firstName);
        return sportivesRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> filterSportivesByTeamId(int teamId) {
        logger.trace("filterSportivesByTeamId - method entered teamId={}",teamId);
        return sportivesRepository.findAll()
                .stream()
                .filter(sportive -> sportive.getTeam().getId()==teamId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sportive> sortSportivesAscendingByFirstName() {
        logger.trace("sortSportivesAscendingByName - method entered");
        Iterable<Sportive> sportives = sportivesRepository.findAll(Sort.by("firstName").ascending());
        return StreamSupport.stream(sportives.spliterator(),false)
                .collect(Collectors.toList());
    }

}
