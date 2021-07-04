package service;

import domain.exceptions.MyException;
import domain.entities.Sportive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class SportiveServiceClient implements SportiveServiceInterface {
    @Autowired
    private SportiveServiceInterface sportiveService;

    @Override
    public void addSportive(Sportive sportive) throws MyException {
        sportiveService.addSportive(sportive);
    }

    @Override
    public void deleteSportive(Long id) throws MyException {
        sportiveService.deleteSportive(id);
    }

    @Override
    public void updateSportive(Sportive sportive) throws MyException {
        sportiveService.updateSportive(sportive);
    }

    @Override
    public Sportive getSportiveById(long id) throws MyException {
        return sportiveService.getSportiveById(id);
    }

    @Override
    public Set<Sportive> getAllSportives() {
        return sportiveService.getAllSportives();
    }

    @Override
    public Set<Sportive> filterSportivesByAge(int age) {
        return sportiveService.filterSportivesByAge(age);
    }

    @Override
    public Set<Sportive> filterSportivesByFirstName(String firstName) { return sportiveService.filterSportivesByFirstName(firstName); }

    @Override
    public Set<Sportive> filterSportivesByTeamId(int teamId) {
        return sportiveService.filterSportivesByTeamId(teamId);
    }

}
