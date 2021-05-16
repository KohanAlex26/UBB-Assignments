package ro.ubb.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.core.model.Team;
import ro.ubb.core.repository.TeamRepository;
import ro.ubb.core.validators.TeamValidator;
import ro.ubb.core.validators.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService implements ITeamService{
    public static final Logger log = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamValidator validator;

    @Autowired
    private TeamRepository teamRepository;



    @Override
    @Transactional
    public void addTeam(Team team) throws ValidatorException {
        log.trace("addTeam - method entered team={}", team);
        validator.validate(team);
        if (teamRepository.existsById(team.getId()))
            throw new ValidatorException("Team already exists");
        Team std = teamRepository.save(team);
        log.trace("addTeam - method finished team={}", std);
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) throws ValidatorException {
        log.trace("deleteTeam - method entered={}",id);
        if (!teamRepository.existsById(id))
            throw new ValidatorException("Team doesn't exist");
        teamRepository.delete(teamRepository.getOne(id));
        log.trace("deleteTeam - method finished");
    }

    @Override
    @Transactional
    public void updateTeam(Team team) throws ValidatorException {
        log.trace("updateTeam - method entered: team={}", team);
        validator.validate(team);
        if (!teamRepository.existsById(team.getId()))
            throw new ValidatorException("Team doesn't exist");
        teamRepository.findById(team.getId())
                .ifPresent(s -> {
                    s.setTeamName(team.getTeamName());
                    log.debug("updateTeam - updated: s={}", s);
                });
        log.trace("updateTeam - method finished");
    }

    @Override
    public Team getTeamById(long id) throws ValidatorException {
        log.trace("getTeamById - method entered={}", id);
        return teamRepository.findById(id).get();
    }

    @Override
    public List<Team> getAllTeams() {
        log.trace("getAllTeams - method entered");
        return teamRepository.findAll();

    }

    @Override
    public List<Team> filterTeamsByTeamName(String teamName) {
        log.trace("filterTeamsByTeamName - method entered teamName={}",teamName);
        return teamRepository.findAll()
                .stream()
                .filter(team -> team.getTeamName().contains(teamName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> sortTeamsAscendingByName() {
        log.trace("sortTeamsAscendingByName - method entered");
        Iterable<Team> teams = teamRepository.findAll(Sort.by("teamName").ascending());
        return StreamSupport.stream(teams.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamsOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Team> pagedResult = teamRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
