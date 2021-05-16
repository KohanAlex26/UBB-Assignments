package ro.ubb.core.service;

import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.Trainer;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ITrainerService {
    void addTrainer(Trainer trainer) throws ValidationException;

    void deleteTrainer(Long id) throws ValidationException;

    void updateTrainer(Trainer trainer) throws ValidationException;

    Trainer getTrainerById(long id) throws ValidationException;

    List<Trainer> getAllTrainers();

    List<Trainer> filterTrainersByName(String name);

    List<Trainer> sortTrainersAscendingByFirstName();

    List<Trainer> getTrainersOnPage(int pageNo, int pageSize);
}
