package core.repository.sportive;

import core.domain.Sportive;
import core.repository.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SportiveRepository extends Repository<Sportive, Long>, SportiveExtendedRepository{

    @Query("select distinct s from Sportive s")
    @EntityGraph(value = "sportivesWithTeams", type = EntityGraph.EntityGraphType.LOAD)
    List<Sportive> findSportivesWithTeams();

//    @Query("select distinct s from Sportive s")
//    @EntityGraph(value = "sportiveWithTrainers", type = EntityGraph.EntityGraphType.LOAD)
//    List<Sportive> findAllWithTrainersGraph();
//
//    @Query("select distinct s from Sportive s where s.id=?1")
//    @EntityGraph(value = "sportiveWithTrainers", type = EntityGraph.EntityGraphType.LOAD)
//    Sportive findOneWithTrainers(Long sportiveId);
}
