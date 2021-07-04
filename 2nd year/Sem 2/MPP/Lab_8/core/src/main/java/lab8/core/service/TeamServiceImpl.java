package lab8.core.service;

import lab8.core.model.Team;
import lab8.core.repository.TeamRepository;
import lab8.core.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService{
    private static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> filterTeamsByTeamname(String name) {
        log.trace("filterTeamsByTeamName - method entered teamName={}",name);
        return teamRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getTeamName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Team saveTeam(Team team) {
        log.trace("TeamServiceImpl - saveTeam - method entered: team={}", team);
        return teamRepository.save(team);
    }

    @Override
    @Transactional
    public Team updateTeam(Long id, Team team) {
        Optional<Team> findTeam = teamRepository.findById(team.getId());
        if (findTeam.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        try {
            log.trace("TeamServiceImpl - updateTeam - method entered: team={}", team);

            Team update = teamRepository.findById(id).orElse(team);
            update.setTeamName(team.getTeamName());
            return update;
        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public void deleteTeamById(Long id) {
        Optional<Team> findTeam = teamRepository.findById(id);
        if (findTeam.isEmpty()) {
            throw new ValidatorException("Cannot find id");
        }

        log.trace("TeamServiceImpl - deleteTeam - method entered: id={}", id);
        teamRepository.deleteById(id);
        log.trace("TeamServiceImpl - deleteTeam - method finished");

    }
}
