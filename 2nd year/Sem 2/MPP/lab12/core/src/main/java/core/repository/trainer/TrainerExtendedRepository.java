package core.repository.trainer;


import core.domain.SportiveTrainer;
import core.domain.Trainer;

import java.util.List;

/**
 * Created by radu.
 */
public interface TrainerExtendedRepository {
    List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerIdJPQL(long trainerId);

//    List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerIdCriteria(long trainerId);

    List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerIdNative(long trainerId);
}
