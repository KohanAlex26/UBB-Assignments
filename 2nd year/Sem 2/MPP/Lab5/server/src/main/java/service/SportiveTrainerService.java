package service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import org.xml.sax.SAXException;
import repository.Repository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SportiveTrainerService implements SportiveTrainerServiceInterface{
    ExecutorService executor;
    Repository<Long, SportiveTrainer> repository;
    SportiveTrainerServiceInterface sportiveTrainerService;
    Repository<Long, Sportive> sportiveRepo;
    Repository<Long, Trainer> trainerRepo;

    public SportiveTrainerService(ExecutorService executorService, Repository<Long, SportiveTrainer> repository, Repository<Long, Sportive> sportiveRepo, Repository<Long, Trainer> trainerRepo) {
        this.executor = executorService;
        this.repository = repository;
        this.sportiveRepo = sportiveRepo;
        this.trainerRepo = trainerRepo;
    }

    public void setSportiveTrainerService(SportiveTrainerServiceInterface sportiveTrainerService) {
        this.sportiveTrainerService = sportiveTrainerService;
    }

    @Override
    public CompletableFuture<Boolean> addSportiveTrainer(SportiveTrainer sportiveTrainer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !this.repository.save(sportiveTrainer).isPresent();
            } catch (ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.delete(st.getId()).isPresent();
            } catch (IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }

    @Override
    public CompletableFuture<Boolean> updateSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.update(st).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }

    @Override
    public CompletableFuture<Boolean> associateSportiveTrainer(SportiveTrainer st) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.save(st).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException | SAXException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }

    @Override
    public CompletableFuture<Boolean> dissociateSportiveTrainer(Long id) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return !repository.delete(id).isPresent();
            } catch (ValidatorException | IOException | TransformerException | ParserConfigurationException | SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }

    @Override
    public CompletableFuture<Set<SportiveTrainer>> getAllSportivesTrainers() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }
    @Override
    public Optional<Sportive> getOneSportive(Long id) {
        try {
            return sportiveRepo.findOne(id);
        } catch(SQLException ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public Optional<Trainer> getOneTrainer(Long id) {
        try {
            return trainerRepo.findOne(id);
        } catch(SQLException ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public CompletableFuture<Set<Trainer>> allTrainersOfOneSportive(Sportive s) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Set<SportiveTrainer> st = StreamSupport.stream(repository.findAll().spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
                return st.stream()
                        .map(x -> getOneTrainer(x.getTrainerID()).get())
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }
    @Override
    public CompletableFuture<Set<Sportive>> allSportivesOfOneTrainer(Trainer t) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Set<SportiveTrainer> st = StreamSupport.stream(repository.findAll().spliterator(), false).filter(sp -> sp.getTrainerID().equals(t.getId())).collect(Collectors.toSet());
                return st.stream()
                        .map(x -> getOneSportive(x.getSportiveID()).get())
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }
    @Override
    public CompletableFuture<Set<String>> allTrainingTypesOfOneSportive(Sportive s) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Set<SportiveTrainer> st = StreamSupport.stream(repository.findAll().spliterator(), false).filter(sp -> sp.getSportiveID().equals(s.getId())).collect(Collectors.toSet());
                return st.stream()
                        .map(SportiveTrainer::getTrainingType)
                        .collect(Collectors.toSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }
    @Override
    public CompletableFuture<SportiveTrainer> mostExpensiveTraining() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Set<Integer> costs = StreamSupport.stream(this.repository.findAll().spliterator(), false).map(SportiveTrainer::getCost).collect(Collectors.toSet());
                int maxCost = Collections.max(costs);
                Set<SportiveTrainer> str = StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
                SportiveTrainer st = str.stream().filter(x -> x.getCost() == maxCost).findAny().orElseThrow();
                return st;
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }, this.executor);
    }
}
