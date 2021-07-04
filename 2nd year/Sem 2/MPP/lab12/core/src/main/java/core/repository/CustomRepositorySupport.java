package core.repository;

import core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by radu.
 */
@Getter
@Setter
public class CustomRepositorySupport {

    @PersistenceContext
    private EntityManager entityManager;

}
