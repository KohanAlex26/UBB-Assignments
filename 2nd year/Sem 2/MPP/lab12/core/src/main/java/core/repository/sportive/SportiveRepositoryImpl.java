package core.repository.sportive;


import core.domain.Sportive;
import core.domain.SportiveTrainer;
import core.repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by radu.
 */
public class SportiveRepositoryImpl extends CustomRepositorySupport implements SportiveExtendedRepository {
//    private static final Logger log = LoggerFactory.getLogger(SportiveRepositoryImpl.class);

    @Override
    public List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveIdJPQL(long sportiveId) {
        System.out.println("jpql");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct st from SportiveTrainer st left join fetch st.sportive left join fetch st.trainer where st.sportive.id = ?1");
        query.setParameter(1, sportiveId);
        //noinspection unchecked
        return (List<SportiveTrainer>) query.getResultList();
    }


    @Override
    @Transactional
    public List<SportiveTrainer> findSportiveTrainerOfSportiveBySportiveIdNative(long sportiveId) {
        System.out.println("native");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {st.*},{t.*},{s.*} " +
                "from SportiveTrainer st " +
                "left join trainer t on st.trainerid=t.id " +
                "left join sportive s on st.sportiveid=s.id " + "" +
                "where s.id = " + sportiveId)
                .addEntity("st",SportiveTrainer.class)
                .addJoin("t", "st.trainer")
                .addJoin("s", "st.sportive")
                .addEntity("st",SportiveTrainer.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<SportiveTrainer>) query.getResultList();
    }

//@Override
//public List<Sportive> findAllWithTeamsJpql() {
//    EntityManager entityManager = getEntityManager();
//    Query query = entityManager.createQuery(
//            "select distinct s from Sportive s " +
//                    "left join fetch s.team t ");
//    List<Sportive> sportives = query.getResultList();
//
//    return sportives;
//
//    //setParameter
//    //... where b.publisher.name = :pname
//    //... where b.publisher.name = :?1
//    //query.setParameter("pname","p1");
//    //query.setParameter(1,"p1");
//}
//    @Override
//    @Transactional
//    public List<Sportive> findAllWithTrainersSqlQuery() {
//        log.trace("findAllWithTrainersSqlQuery: method entered");
//
//        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
//        Session session = hibernateEntityManager.getSession();
//
//        Query query = session.createSQLQuery("select distinct {s.*}, {sd.*}, {d.*}" +
//                " from sportive s" +
//                " left join sportive_trainer sd on sd.sportive_id = s.id" +
//                " left join trainer d on d.id = sd.trainer_id")
//                .addEntity("s", Sportive.class)
//                .addJoin("sd", "s.sportiveTrainers")
//                .addJoin("d", "sd.trainer")
//                .addEntity("s", Sportive.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        List<Sportive> sportives = query.list();
//
//        log.trace("findAllWithTrainersSqlQuery: sportives={}", sportives);
//        return sportives;
//    }
//
//    @Override
//    @Transactional
//    public List<Sportive> findAllWithTrainersJpql() {
//        log.trace("findAllWithTrainersJpql: method entered");
//
//        javax.persistence.Query query = getEntityManager().createQuery("select distinct s from Sportive s" +
//                " left join fetch s.sportiveTrainers sd" +
//                " left join fetch sd.trainer d");
//
//        List<Sportive> sportives = query.getResultList();
//
//        log.trace("findAllWithTrainersJpql: sportives={}", sportives);
//        return sportives;
//    }



//    @Override
//    @Transactional
//    public List<Sportive> findAllWithTrainersJpaCriteria() {
//        log.trace("findAllWithTrainersJpaCriteria: method entered");
//
//        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Sportive> query = criteriaBuilder.createQuery(Sportive.class);
//
//        query.distinct(Boolean.TRUE);
//
//        Root<Sportive> from = query.from(Sportive.class);
//
//        Fetch<Sportive, SportiveTrainer> sportiveTrainerFetch = from.fetch(Sportive_.sportiveTrainers, JoinType.LEFT);
//        sportiveTrainerFetch.fetch(SportiveTrainer_.trainer, JoinType.LEFT);
//
//        List<Sportive> sportives = getEntityManager().createQuery(query).getResultList();
//
//        log.trace("findAllWithTrainersJpaCriteria: sportives={}", sportives);
//
//        return sportives;
//    }
}
