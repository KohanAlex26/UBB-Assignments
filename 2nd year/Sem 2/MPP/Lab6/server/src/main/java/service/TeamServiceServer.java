package service;

import domain.entities.Team;
import domain.exceptions.MyException;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RepositoryInterface;
import repository.TeamRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TeamServiceServer implements  TeamServiceInterface{

    @Autowired
    Validator<Team> validator;

    @Autowired
    RepositoryInterface<Long, Team> repository;

//    @Autowired
//    TeamServiceInterface teamService;



    @Override
    public void addTeam(Team team) throws MyException {
        this.validator.validate(team);
        if (this.repository.save(team).isPresent())
            throw new MyException("Team already exists");
    }

    @Override
    public void updateTeam(Team team) throws MyException {
        this.validator.validate(team);
        if (this.repository.update(team).isPresent())
            throw new MyException("Trainer does not exist");
    }

    @Override
    public void deleteTeam(Long id) throws MyException {
        if (!this.repository.delete(id).isPresent())
            throw new MyException("Team does not exist");
    }

    @Override
    public Set<Team> getAllTeams() {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Team> filterTeamsByTeamName(String token) {
        return StreamSupport.stream(this.repository.getAll().spliterator(), false)
                .filter((team) -> team.getTeamName().toLowerCase().contains(token.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
