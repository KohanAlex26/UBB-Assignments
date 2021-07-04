package service;


import domain.entities.Team;
import domain.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class TeamServiceClient implements TeamServiceInterface{

    @Autowired
    private TeamServiceInterface teamService;

    @Override
    public void addTeam(Team team) throws MyException {
        teamService.addTeam(team);
    }

    @Override
    public void updateTeam(Team team) throws MyException {
        teamService.updateTeam(team);
    }

    @Override
    public void deleteTeam(Long id) throws MyException {
        teamService.deleteTeam(id);
    }

    @Override
    public Set<Team> getAllTeams() {
        return teamService.getAllTeams();
    }


    @Override
    public Set<Team> filterTeamsByTeamName(String token) {
        return teamService.filterTeamsByTeamName(token);
    }
}
