package service;

import domain.entities.Team;
import domain.exceptions.MyException;

import java.util.Set;

public interface TeamServiceInterface {

    void addTeam(Team team) throws MyException;

    void updateTeam(Team team) throws MyException;

    void deleteTeam(Long id) throws MyException;

    Set<Team> getAllTeams();

    Set<Team> filterTeamsByTeamName(String token);
}
