package core.repository.team;

import core.domain.Team;
import core.repository.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepository extends Repository<Team, Long>, TeamExtendedRepository {

    @Query("select distinct t from Team t where t.id = :teamId")
    @EntityGraph(value = "teamsWithSportives", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Team> findTeamByIdWithSportives(@Param("teamId") long teamId);

    //    @Query("select distinct t from Team t")
//    @EntityGraph(value = "teamWithSportives", type = EntityGraph.EntityGraphType.LOAD)
//    List<Team> findAllWithSportivesGraph();
//
//    @Query("select distinct s from Team s where s.id=?1")
//    @EntityGraph(value = "awardWithSportives", type = EntityGraph.EntityGraphType.LOAD)
//    Team findOneWithSportives(Long awardId);
}
