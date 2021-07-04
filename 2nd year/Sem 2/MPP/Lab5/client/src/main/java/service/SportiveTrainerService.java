package service;

import domain.entities.Message;
import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import tcp.TcpClient;
import utils.Factory;

import java.net.SocketException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class SportiveTrainerService implements SportiveTrainerServiceInterface{
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public SportiveTrainerService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }
    /**
     * Adds a sportive to the repository.
     *
     * @param st
     *            must be not null.
     * @throws IllegalArgumentException
     *             if the given sportive is null.
     * @throws ValidatorException
     *             if the sportive is not valid.
     */
    @Override
    public CompletableFuture<Boolean> addSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        Message message = new Message("add sportiveTrainer", Factory.sportiveTrainerToString(st));
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
    public CompletableFuture<Boolean> deleteSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        Message message = new Message("delete sportiveTrainer", Factory.sportiveTrainerToString(st));
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
    public CompletableFuture<Boolean> updateSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        Message message = new Message("update sportiveTrainer", Factory.sportiveTrainerToString(st));
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
    public CompletableFuture<Boolean> dissociateSportiveTrainer(Long id) throws ValidatorException {
        Message message = new Message("dissociate sportive-trainer", String.valueOf(id));
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
    public CompletableFuture<Boolean> associateSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        Message message = new Message("associate sportive-trainer", Factory.sportiveTrainerToString(st));
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
    public CompletableFuture<Set<SportiveTrainer>> getAllSportivesTrainers() {
        Message message = new Message("all sportives-trainers", "");
        Message response = null;
//        try {
            response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = body.split(System.lineSeparator());
        Set<SportiveTrainer> sts = Arrays.stream(tokens).map(Factory::messageToSportiveTrainer).collect(Collectors.toSet());
        //return executorService.submit(() -> assignments);
        return CompletableFuture.supplyAsync(
                ()->sts,
                executorService
        );
    }

    @Override
    public Optional<Sportive> getOneSportive(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> getOneTrainer(Long id) {
        return Optional.empty();
    }

    @Override
    public CompletableFuture<Set<Trainer>> allTrainersOfOneSportive(Sportive s) {
        Message message = new Message("trainers of one sportive", Factory.sportiveToString(s));
        Message response = null;
//        try {
        System.out.println(message.getBody());
            response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = body.split(System.lineSeparator());
        Set<Trainer> trainers = Arrays.stream(tokens).map(Factory::messageToTrainer).collect(Collectors.toSet());
        //return executorService.submit(() -> assignments);
        return CompletableFuture.supplyAsync(
                ()->trainers,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Sportive>> allSportivesOfOneTrainer(Trainer t) {
        Message message = new Message("sportives of one trainer", Factory.trainerToString(t));
        Message response = null;
//        try {
            response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = body.split(System.lineSeparator());
        Set<Sportive> sportives = Arrays.stream(tokens).map(Factory::messageToSportive).collect(Collectors.toSet());
        //return executorService.submit(() -> assignments);
        return CompletableFuture.supplyAsync(
                ()->sportives,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<String>> allTrainingTypesOfOneSportive(Sportive s) {
        Message message = new Message("training types of one sportive", Factory.sportiveToString(s));
        Message response = null;
//        try {
            response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = body.split(System.lineSeparator());
        Set<String> sts = Arrays.stream(tokens).collect(Collectors.toSet());
        //return executorService.submit(() -> assignments);
        return CompletableFuture.supplyAsync(
                ()->sts,
                executorService
        );
    }

    @Override
    public CompletableFuture<SportiveTrainer> mostExpensiveTraining() {
        Message message = new Message("most expensive training", "");
//        Message response = null;
//        try {
           Message response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        System.out.println(body);
        String[] tokens = body.split(",");
//        String[] tokens1= tokens.split()
        SportiveTrainer st= new SportiveTrainer(Long.parseLong(tokens[0]),Long.parseLong(tokens[1]),Long.parseLong(tokens[2]),tokens[3],Integer.parseInt(tokens[4]));

        //return executorService.submit(() -> assignments);
        return CompletableFuture.supplyAsync(
                ()->st,
                executorService
        );
    }

}
