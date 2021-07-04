package core.repository.team;



import core.domain.Team;
import core.repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by radu.
 */
public class TeamRepositoryImpl extends CustomRepositorySupport
        implements TeamExtendedRepository {
//    @Override
//    public List<Team> findAllWithSportivesJpql() {
//        EntityManager entityManager = getEntityManager();
//        Query query = entityManager.createQuery(
//                "select distinct t from Team t " +
//                "left join fetch t.sportives s ");
//        List<Team> teams = query.getResultList();
//
//        return teams;
//
//        //setParameter
//        //... where b.publisher.name = :pname
//        //... where b.publisher.name = :?1
//        //query.setParameter("pname","p1");
//        //query.setParameter(1,"p1");
//    }
}
