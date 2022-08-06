package repository;



import domain.entities.BaseEntity;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * class that implements the Repository interface
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    /**
     * Finds the entity with the specified id
     * @param id must be not null.
     * @return an Optional, the entity if exists, null if not
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Returns all entities
     * @return a Set containing all the entities
     */
    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    /**
     * Saves an entity
     * @param entity must not be null.
     * @return Optional, the entity that was added, null otherwise
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    /**
     * Deletes an entity
     * @param id must not be null.
     * @return Optional if the entity that was removed, null otherwise
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * Updates an entity
     * @param entity must not be null.
     * @return Optional, null if the entity was updated, the entity otherwise
     * @throws ValidatorException if the entity is not valid
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
