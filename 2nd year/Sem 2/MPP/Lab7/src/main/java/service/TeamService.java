package service;

import domain.entities.Team;

import java.util.List;

/**
 * Created by radu.
 */
public interface TeamService {
    List<Team> getAllTeams();

    void saveTeam(Team team);

    void updateTeam(Team team);

    void deleteById(Long id);

    List<Team> filterTeamsByTeamName(String token);
}
