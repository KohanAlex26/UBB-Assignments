package service;

import domain.entities.Team;
import domain.exceptions.ValidatorException;
import org.xml.sax.SAXException;
import repository.Repository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TeamService implements TeamServiceInterface{
    ExecutorService executor;
    Repository<Long, Team> repository;
    TeamServiceInterface teamService;

    public TeamService(ExecutorService executorService, Repository<Long, Team> repository) {
        this.executor = executorService;
        this.repository = repository;
    }

    public void setTeamService(TeamServiceInterface teamService) {
        this.teamService = teamService;
    }

    @Override
    public CompletableFuture<Boolean> addTeam(Team team) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.save(team).isPresent();
            } catch (ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteTeam(Long id) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.delete(id).isPresent();
            } catch (IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> updateTeam(Team team) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.update(team).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Team>> getAllTeams() {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Team>> filterTeamsByName(String name) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .filter(team -> (team.getTeamName().contains(name)))
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }
}
