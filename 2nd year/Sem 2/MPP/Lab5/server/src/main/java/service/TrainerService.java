package service;

import domain.entities.Trainer;
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

public class TrainerService implements TrainerServiceInterface{
    ExecutorService executor;
    Repository<Long, Trainer> repository;
    TrainerServiceInterface trainerService;

    public TrainerService(ExecutorService executorService, Repository<Long, Trainer> repository) {
        this.executor = executorService;
        this.repository = repository;
    }

    public void setTrainerService(TrainerServiceInterface trainerService) {
        this.trainerService = trainerService;
    }

    @Override
    public CompletableFuture<Boolean> addTrainer(Trainer trainer) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.save(trainer).isPresent();
            } catch (ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteTrainer(Long id) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.delete(id).isPresent();
            } catch (IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> updateTrainer(Trainer trainer) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.update(trainer).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Trainer>> getAllTrainers() {
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
    public CompletableFuture<Set<Trainer>> filterTrainersByName(String name) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .filter(trainer -> (trainer.getFirstName().contains(name) || trainer.getLastName().contains(name)))
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }
}
