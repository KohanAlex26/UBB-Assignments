package service;

import domain.entities.Sportive;
import domain.exceptions.ValidatorException;
import org.xml.sax.SAXException;
import repository.Repository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportiveService implements SportiveServiceInterface{
    ExecutorService executor;
    Repository<Long, Sportive> repository;
    SportiveServiceInterface sportiveService;

    public SportiveService(ExecutorService executorService, Repository<Long, Sportive> repository) {
        this.executor = executorService;
        this.repository = repository;
    }

    public void setSportiveService(SportiveServiceInterface sportiveService) {
        this.sportiveService = sportiveService;
    }

    @Override
    public CompletableFuture<Boolean> addSportive(Sportive sportive) {
        return CompletableFuture.supplyAsync(() -> {
            try {
//                this.repository.findAll().forEach(System.out::println);
                return !this.repository.save(sportive).isPresent();
            } catch (ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
    @Override
    public CompletableFuture<Boolean> deleteSportive(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.delete(id).isPresent();
            } catch (IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> updateSportive(Sportive sportive) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.update(sportive).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Sportive>> getAllSportives() {
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
    public CompletableFuture<Set<Sportive>> filterByAge(int age) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .filter(sportive -> sportive.getAge()<age)
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Sportive>> filterByFirstName(String st) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .filter(sportive -> sportive.getFirstName().contains(st))
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Sportive>> filterByTeamId(int id) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .filter(sportive -> sportive.getTeamId()==id)
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

//    @Override
//    public CompletableFuture<Sportive> findOneSportive(Long id) {
//        return CompletableFuture.supplyAsync(() -> {
//            try {
////                this.repository.findAll().forEach(System.out::println);
//                Sportive s=repository.findOne(id).get();
//                s.toString();
//                return this.repository.findOne(id).get();
//            } catch (ValidatorException |  SQLException e) {
//                throw new RuntimeException(e.getMessage());
//            }
//        });
//    }

}
