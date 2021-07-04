package service;


import domain.entities.Trainer;
import domain.exceptions.ValidatorException;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface TrainerServiceInterface {

    CompletableFuture<Boolean> addTrainer(Trainer trainer) throws ValidatorException;

    CompletableFuture<Boolean> deleteTrainer(Long id) throws ValidatorException;

    CompletableFuture<Boolean> updateTrainer(Trainer trainer) throws ValidatorException;

    CompletableFuture<Set<Trainer>> getAllTrainers();

    CompletableFuture<Set<Trainer>> filterTrainersByName(String name);
}
