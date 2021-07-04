package core.repository.sportive;

import core.domain.Sportive;
import core.domain.SportiveTrainer;

import java.util.List;

/**
 * Created by radu.
 */
public interface SportiveExtendedRepository {

//    List<Sportive> findAllWithTeamsJpql();
//    List<Sportive> findAllWithTrainersSqlQuery();
//
//    List<Sportive> findAllWithTrainersJpql();
//
////    List<Sportive> findAllWithDTrainersJpaCriteria();

    List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveIdJPQL(long sportiveId);

//    List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveIdCriteria(long sportiveId);

    List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveIdNative(long sportiveId);
}
