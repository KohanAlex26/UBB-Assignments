package service;

import domain.entities.Team;
import domain.exceptions.ValidatorException;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface TeamServiceInterface {

    CompletableFuture<Boolean> addTeam(Team team) throws ValidatorException;

    CompletableFuture<Boolean> deleteTeam(Long id) throws ValidatorException;

    CompletableFuture<Boolean> updateTeam(Team team) throws ValidatorException;

        CompletableFuture<Set<Team>> getAllTeams();

    CompletableFuture<Set<Team>> filterTeamsByName(String name);

}
