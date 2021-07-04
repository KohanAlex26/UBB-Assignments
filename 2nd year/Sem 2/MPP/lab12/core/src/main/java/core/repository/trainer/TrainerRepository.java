package core.repository.trainer;

import core.domain.Trainer;
import core.repository.Repository;

public interface TrainerRepository extends Repository<Trainer, Long>, TrainerExtendedRepository {


    //    @Query("select distinct s from Trainer s")
//    @EntityGraph(value = "trainerWithSportives", type = EntityGraph.EntityGraphType.LOAD)
//    List<Trainer> findAllWithSportivesGraph();
//
//    @Query("select distinct s from Trainer s where s.id=?1")
//    @EntityGraph(value = "trainerWithSportives", type = EntityGraph.EntityGraphType.LOAD)
//    Trainer findOneWithSportives(Long trainerId);
}
