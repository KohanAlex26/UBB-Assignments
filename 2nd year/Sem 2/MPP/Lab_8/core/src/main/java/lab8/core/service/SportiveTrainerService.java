package lab8.core.service;

import lab8.core.model.Sportive;
import lab8.core.model.SportiveTrainer;
import lab8.core.model.Trainer;

import java.util.List;

public interface SportiveTrainerService {
    SportiveTrainer saveSportiveTrainer(SportiveTrainer st);

    void deleteSportiveTrainer(Long id);

    SportiveTrainer updateSportiveTrainer(Long id, SportiveTrainer st);

    List<SportiveTrainer> getAllSportivesTrainers();

    Sportive getOneSportive(Long id);

    Trainer getOneTrainer(Long id);

    List<Trainer> allTrainersOfOneSportive(Sportive s);

    List<Sportive> allSportivesOfOneTrainer(Trainer t);

    List<String> allTrainingTypesOfOneSportive(Sportive s);

    SportiveTrainer mostExpensiveTraining();

}
