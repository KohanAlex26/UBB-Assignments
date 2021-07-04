package core.service;


import core.domain.Sportive;
import core.domain.Team;
import core.exceptions.SportException;
import core.exceptions.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Math.max;

@Service
public class TeamServiceImpl implements TeamService {
    public static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamRepository teamsRepository;

    @Override
    public void addTeam(String teamName) {
        logger.trace("add team - method entered - name: " + teamName);
        long id = 0;
        for (Team team : this.teamsRepository.findAll())
            id = max(id, team.getId() + 1);
        Team teamToBeAdded = new Team(id, teamName);
        teamsRepository.save(teamToBeAdded);
        logger.trace("add team - method finished");
    }

    @Override
    public List<Team> getTeamsFromRepository() {
        return teamsRepository.findAll();
    }

    @Override
    public List<Team> getAllTeams() {
        logger.trace("getAllTeams - method entered");
        return teamsRepository.findAll();

    }

    @Override
    public Team getTeamById(long id) throws ValidatorException {
        logger.trace("getTeamById - method entered={}", id);
        return teamsRepository.findById(id).get();
    }

    @Override
    public List<Team> sortTeamsAscendingByName() {
        Iterable<Team> teams = teamsRepository.findAll(Sort.by("teamName").ascending());
        return StreamSupport.stream(teams.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeam(Long id) {
        logger.trace("deleteTeam - method entered - id: " + id);

        teamsRepository.findById(id)
                .ifPresentOrElse((team) -> teamsRepository.deleteById(team.getId()),
                        () -> {
                            throw new SportException("Team does not exist");
                        }
                );

//        teamsRepository.deleteById(id).orElseThrow(() -> new PetShopException("Team does not exist"));
        logger.trace("deleteTeam - method finished");
    }

    @Override
    @Transactional
    public void updateTeam(Long id, String teamName) {
        logger.trace("updateTeam - method entered - id: " + id + ", name: " + teamName);

        teamsRepository.findById(id)
                .ifPresentOrElse((team) -> {
                            team.setTeamName(teamName);
                        },
                        () -> {
                            throw new SportException("Team does not exist");
                        }
                );
        logger.trace("updateTeam - method finished");
    }

    @Override
    public List<Team> getTeamsOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Team> pagedResult = teamsRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Team> filterTeamsByTeamName(String teamName) {
        return teamsRepository.findAll()
                .stream()
                .filter(team -> team.getTeamName().contains(teamName))
                .collect(Collectors.toList());
    }
}
