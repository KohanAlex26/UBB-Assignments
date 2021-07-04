package core.service;



import core.domain.Award;
import core.domain.Sportive;
import core.domain.SportiveTrainer;
import core.domain.Trainer;

import java.util.List;

public interface TrainerService {

    void addTrainer(String firstName, String lastName, int age, Award award);
//    void addTrainer(String firstName, String lastName, int age);
    List<Trainer> getTrainersFromRepository();
    void deleteTrainer(Long id);
    Trainer getTrainerById(Long id);
    void updateTrainer(Long id, String firstName, String lastName, int age, Award award);
    List<Trainer> getTrainersOnPage(int pageNo, int pageSize);
    List<Trainer> getAllTrainers();
    List<Trainer> filterTrainersByName(String name);
    List<Trainer> sortTrainersAscendingByFirstName();

    List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerId(long trainerId);
}
