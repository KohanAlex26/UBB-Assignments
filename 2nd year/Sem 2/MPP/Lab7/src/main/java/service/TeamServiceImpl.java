package service;

import domain.entities.Trainer;
import domain.exceptions.MyException;
import domain.validators.TeamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TeamRepository;
import domain.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by radu.
 */
@Service
public class TeamServiceImpl implements TeamService {
    public static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamValidator teamValidator;

    @Override
    public List<Team> getAllTeams() {
        log.trace("getAllTeams --- method entered");

        List<Team> result = teamRepository.findAll();

        log.trace("getAllTeams: result={}", result);

        return result;
    }

    @Override
    public void saveTeam(Team team) {
        log.trace("addTeam - method entererd: team=",team.toString());
        teamValidator.validate(team);
//        if(teamRepository.existsById(team.getId()))
//            throw new MyException("team already exists");
        Team storedTeam = teamRepository.save(team);
        log.trace("addTeam - method finished: team=", team.toString());
    }


    @Override
    @Transactional
    public void updateTeam(Team team) {
        log.trace("updateTeam - method entered: team={}", team);

        teamRepository.findById(team.getId())
                .ifPresent(t -> {
                    t.setTeamName(team.getTeamName());
                    log.debug("updateTeam - updated: t={}", t);
                });

        log.trace("updateTeam - method finished");
    }

    @Override
    public void deleteById(Long id) {
        log.trace("deleteTeam - method enters: id=",id);
        if(!teamRepository.existsById(id)){
            throw new MyException("Team does not exist");
        }
        teamRepository.deleteById(id);
        log.trace("deleteTeam - finished");
    }

    @Override
    public List<Team> filterTeamsByTeamName(String token) {
        log.trace("filterTeamsByTeamName - entered");
        var result= teamRepository.findAll()
                .stream()
                .filter((team) -> team.getTeamName().toLowerCase().contains(token.toLowerCase()))
                .collect(Collectors.toList());
        log.trace("filterTeamsByTeamName - finished");
        return result;
    }
}
