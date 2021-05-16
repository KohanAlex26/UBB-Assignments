package ro.ubb.core.service;

import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.Team;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ITeamService {
    void addTeam(Team team) throws ValidationException;

    void deleteTeam(Long id) throws ValidationException;

    void updateTeam(Team team) throws ValidationException;

    Team getTeamById(long id) throws ValidationException;

    List<Team> getAllTeams();
    
    List<Team> filterTeamsByTeamName(String name);

    List<Team> sortTeamsAscendingByName();

    List<Team> getTeamsOnPage(int pageNo, int pageSize);
}
