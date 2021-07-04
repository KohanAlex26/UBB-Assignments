package service;

import domain.entities.Message;
import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.exceptions.ValidatorException;
import repository.Repository;
import tcp.TcpClient;
import utils.Factory;

import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Services class for materials.
 *
 * @author alex.
 *
 */
public class SportiveService implements SportiveServiceInterface {
    private ExecutorService executorService;
    private TcpClient tcpClient;


public SportiveService(ExecutorService executorService, TcpClient tcpClient) {
    this.executorService = executorService;
    this.tcpClient = tcpClient;
}

    @Override
    public CompletableFuture<Boolean> addSportive(Sportive sportive) throws ValidatorException {
        Message message = new Message("add sportive", Factory.sportiveToString(sportive));
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
    public CompletableFuture<Boolean> deleteSportive(Long id) throws ValidatorException {
        Message message = new Message("delete sportive", Factory.sportiveToStringId(id));
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
    public CompletableFuture<Boolean> updateSportive(Sportive Sportive) throws ValidatorException {
        Message message = new Message("update sportive", Factory.sportiveToString(Sportive));
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

    public CompletableFuture<Set<Sportive>> filterByAge(int age){
        Message message = new Message("filter sportives by age", String.valueOf(age));
        Message response = null;
//        try {
        response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Sportive> sportives = Arrays.stream(tokens).map(Factory::messageToSportive).collect(Collectors.toSet());
        //return executorService.submit(() -> students);
        return CompletableFuture.supplyAsync(
                ()-> sportives,
                executorService
        );
    }


    public CompletableFuture<Set<Sportive>> filterByFirstName(String st){
        Message message = new Message("filter sportives by first name", st);
        Message response = null;
//        try {
        response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Sportive> sportives = Arrays.stream(tokens).map(Factory::messageToSportive).collect(Collectors.toSet());
        //return executorService.submit(() -> students);
        return CompletableFuture.supplyAsync(
                ()-> sportives,
                executorService
        );
    }

    public CompletableFuture<Set<Sportive>> filterByTeamId(int id){
        Message message = new Message("filter sportives by team id", String.valueOf(id));
        Message response = null;
//        try {
        response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Sportive> sportives = Arrays.stream(tokens).map(Factory::messageToSportive).collect(Collectors.toSet());
        //return executorService.submit(() -> students);
        return CompletableFuture.supplyAsync(
                ()-> sportives,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Sportive>> getAllSportives()   {
        Message message = new Message("print all sportives", "");
        Message response = null;
//        try {
            response = tcpClient.sendAndReceive(message);
//        } catch (SocketException e) {
//            throw new RuntimeException();
//        }
        String body = response.getBody();
        String[] tokens = (body.split(System.lineSeparator()));
        Set<Sportive> sportives = Arrays.stream(tokens).map(Factory::messageToSportive).collect(Collectors.toSet());
        //return executorService.submit(() -> students);
        return CompletableFuture.supplyAsync(
                ()-> sportives,
                executorService
        );
    }

//    @Override
//    public CompletableFuture<Sportive> findOneSportive(Long id)   {
//        Message message = new Message("find one sportive", String.valueOf(id));
//        Message response = null;
////        try {
//        response = tcpClient.sendAndReceive(message);
////        } catch (SocketException e) {
////            throw new RuntimeException();
////        }
//        String body = response.getBody();
////        String[] tokens = (body.split(System.lineSeparator()));
//        String[] tokens = body.split(",");
//        Sportive s= new Sportive(tokens[1], tokens[2], Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]));
//        s.setId(Long.parseLong(tokens[0]));
//        //return executorService.submit(() -> students);
//        return CompletableFuture.supplyAsync(
//                ()-> s,
//                executorService
//        );
//    }
}
