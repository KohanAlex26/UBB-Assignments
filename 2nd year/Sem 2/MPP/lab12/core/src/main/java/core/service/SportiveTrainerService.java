package core.service;


import core.domain.Sportive;
import core.domain.SportiveTrainer;
import core.domain.Trainer;

import java.util.List;

public interface SportiveTrainerService {
    void addSportiveTrainer(Long sportiveId, Long trainerId, int cost, String trainingType);
    List<SportiveTrainer> getSportiveTrainersFromRepository();
//    List<SportiveTrainer> getSportivesTrainersOnPage(int pageNo, int pageSize);
    List<SportiveTrainer> getAllSportivesTrainers();
}
