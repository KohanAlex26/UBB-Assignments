package core.repository.trainer;

import core.domain.SportiveTrainer;
import core.repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
//import core.domain.SportiveTrainer_;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TrainerRepositoryImpl extends CustomRepositorySupport implements TrainerExtendedRepository {

    @Override
    public List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerIdJPQL(long trainerId) {
        System.out.println("jpql");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct st from SportiveTrainer st left join fetch st.sportive left join fetch st.trainer where st.trainer.id = ?1");
        query.setParameter(1, trainerId);
        //noinspection unchecked
        return (List<SportiveTrainer>) query.getResultList();
    }

    @Override
    @Transactional
    public List<SportiveTrainer> findSportiveTrainerOfTrainerByTrainerIdNative(long trainerId) {
        System.out.println("native");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {st.*},{s.*},{t.*} " +
                "from SportiveTrainer st " +
                "left join sportive s on st.sportiveId=s.id " +
                "left join trainer t on st.trainerid=t.id " + "" +
                "where t.id = " + trainerId)
                .addEntity("st",SportiveTrainer.class)
                .addJoin("s", "st.sportive")
                .addJoin("t", "st.trainer")
                .addEntity("st",SportiveTrainer.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<SportiveTrainer>) query.getResultList();
    }
}
