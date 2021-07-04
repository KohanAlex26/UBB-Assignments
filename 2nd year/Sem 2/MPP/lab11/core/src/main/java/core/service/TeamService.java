package core.service;



import core.domain.Team;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface TeamService {
    void addTeam(String teamName) throws ValidationException;
    void deleteTeam(Long id) throws ValidationException;
    void updateTeam(Long id, String teamName) throws ValidationException;
    Team getTeamById(long id) throws ValidationException;
    List<Team> getAllTeams();
    List<Team> getTeamsFromRepository();
//    List<Team> getTeamsByName(String name);

    List<Team> getTeamsOnPage(int pageNo, int pageSize);
    List<Team> sortTeamsAscendingByName();
    List<Team> filterTeamsByTeamName(String name);
}
