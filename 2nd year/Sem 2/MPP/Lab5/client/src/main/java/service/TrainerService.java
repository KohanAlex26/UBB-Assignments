package service;

import domain.entities.Message;
import domain.entities.Sportive;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import tcp.TcpClient;
import utils.Factory;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class TrainerService implements TrainerServiceInterface{
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public TrainerService(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Boolean> addTrainer(Trainer trainer) throws ValidatorException {
        Message message = new Message("add trainer", Factory.trainerToString(trainer));
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
    public CompletableFuture<Boolean> deleteTrainer(Long id) throws ValidatorException {
        Message message = new Message("delete trainer", Factory.sportiveToStringId(id));
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
    public CompletableFuture<Boolean> updateTrainer(Trainer trainer) throws ValidatorException {
        Message message = new Message("update trainer", Factory.trainerToString(trainer));
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
    public CompletableFuture<Set<Trainer>> getAllTrainers() {
        Message message = new Message("print all trainers", "");
        Message response = null;
        response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Trainer> trainers = Arrays.stream(tokens).map(Factory::messageToTrainer).collect(Collectors.toSet());
        return CompletableFuture.supplyAsync(
                ()-> trainers,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Trainer>> filterTrainersByName(String name) {
        Message message = new Message("filter trainers by name", name);
        Message response = null;
        response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Trainer> trainers = Arrays.stream(tokens).map(Factory::messageToTrainer).collect(Collectors.toSet());
        return CompletableFuture.supplyAsync(
                ()-> trainers,
                executorService
        );
    }
}
