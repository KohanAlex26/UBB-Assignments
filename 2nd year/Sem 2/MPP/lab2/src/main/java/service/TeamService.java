package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.exceptions.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Services class for teams.
 *
 * @author Sebi.
 *
 */
public class TeamService {
    private Repository<Long, Team> repo;

    public TeamService(Repository<Long, Team> repo) {
        this.repo = repo;
    }

    /**
     * Adds a team to the repository;
     *
     * @param team
     *          must be not null
     * @throws ValidatorException
     *          if the team is not valid
     * @throws IllegalArgumentException
     *          if the team is null
     */
    public void addTeam(Team team) throws ValidatorException {
        repo.save(team);
    }

    /**
     * Get all teams from the repository
     *
     *
     * @return
     *        A set containing all teams
     */
    public Set<Team> getAll(){
        Iterable<Team> teamIterable = repo.findAll();
        return StreamSupport.stream(teamIterable.spliterator(), false).collect(Collectors.toSet());
    }


    /**
     * Deletes the team with the given id.
     *
     * @param id
     *          must be positive
     *
     * @throws ValidatorException
     *          if the given id is not associated with any existing team, ValidatorException is throwed
     * */
    public void deleteTeam(Long id) throws ValidatorException{
        Optional<Team> findTeam = repo.findOne(id);
        if (findTeam.isEmpty()) {
            throw new ValidatorException("Cannot find ID");
        }
        repo.delete(id);
    }

    /**
     * Updates a team.
     *
     * @param team
     *          must be a valid team
     *
     * @throws ValidatorException
     *          if the team is not valid
     */
    public void updateTeam(Team team) throws ValidatorException{
        Optional<Team> findTeam = repo.findOne(team.getId());
        if (findTeam.isEmpty()){
            throw new ValidatorException("Cannot find ID");
        }
        repo.update(team);
    }


    /**
     * Returns all teams that contain in their team name the given token
     *
     * @param token
     *          A substring of the team names
     * @return
     *          All teams containing the token as substring of their team names
     */
    public Set<Team> filterByTeamName(String token){
        Iterable<Team> teamSet = repo.findAll();
        return StreamSupport.stream(teamSet.spliterator(), false)
                .filter(team -> team.getTeamName().toLowerCase().contains(token))
                .collect(Collectors.toSet());
    }
}
