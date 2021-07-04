package repository;

import domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


import java.io.Serializable;

@NoRepositoryBean
public interface RepoInterface<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}