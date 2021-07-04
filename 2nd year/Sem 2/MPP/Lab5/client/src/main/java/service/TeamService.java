package service;

import domain.entities.Message;
import domain.entities.Team;
import domain.exceptions.ValidatorException;
import tcp.TcpClient;
import utils.Factory;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class TeamService implements TeamServiceInterface{
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public TeamService(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Boolean> addTeam(Team team) throws ValidatorException {
        Message message = new Message("add team", Factory.teamToString(team));
        Message response = tcpClient.sendAndReceive(message);
        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new ValidatorException(body);
        //return executorService.submit(() -> true);
        return CompletableFuture.supplyAsync(
                ()->true,
                executorService
        );
    }

    @Override
    public CompletableFuture<Boolean> deleteTeam(Long id) throws ValidatorException {
        Message message = new Message("delete team", Factory.teamToStringId(id));
        Message response = tcpClient.sendAndReceive(message);
        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new ValidatorException(body);
        //return executorService.submit(() -> true);
        return CompletableFuture.supplyAsync(
                ()->true,
                executorService
        );
    }

    @Override
    public CompletableFuture<Boolean> updateTeam(Team team) throws ValidatorException {
        Message message = new Message("update team", Factory.teamToString(team));
        Message response = tcpClient.sendAndReceive(message);
        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new ValidatorException(body);
        //return executorService.submit(() -> true);
        return CompletableFuture.supplyAsync(
                ()->true,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Team>> getAllTeams() {
        Message message = new Message("print all teams", "");
        Message response = null;
        response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Team> teams = Arrays.stream(tokens).map(Factory::messageToTeam).collect(Collectors.toSet());
        return CompletableFuture.supplyAsync(
                ()-> teams,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Team>> filterTeamsByName(String name) {
        Message message = new Message("filter teams by name", name);
        Message response = null;
        response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Team> teams = Arrays.stream(tokens).map(Factory::messageToTeam).collect(Collectors.toSet());
        return CompletableFuture.supplyAsync(
                ()-> teams,
                executorService
        );
    }
}
