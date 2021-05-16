package ro.ubb.core.service;

import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.SportiveTrainer;
import ro.ubb.core.model.Trainer;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ISportiveTrainerService {

    SportiveTrainer getSportiveTrainerById(long id) throws ValidationException;

    Sportive getOneSportive(Long id);

    Trainer getOneTrainer(Long id);

    List<SportiveTrainer> getAllSportivesTrainers();

    List<SportiveTrainer> sortSportivesTrainersAscendingByCost();

    List<SportiveTrainer> getSportivesTrainersOnPage(int pageNo, int pageSize);

    List<Trainer> allTrainersOfOneSportive(Long id);
}
