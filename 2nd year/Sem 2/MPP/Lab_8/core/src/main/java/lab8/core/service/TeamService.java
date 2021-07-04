package lab8.core.service;

import lab8.core.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    List<Team> filterTeamsByTeamname(String name);

    Team saveTeam(Team team);

    Team updateTeam(Long id, Team team);

    void deleteTeamById(Long id);
}
