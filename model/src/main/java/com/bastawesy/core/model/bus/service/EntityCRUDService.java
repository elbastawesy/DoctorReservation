package com.bastawesy.core.model.bus.service;

import com.bastawesy.core.model.entity.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EntityCRUDService<T extends BaseEntity, E extends CrudRepository> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    T saveOrUpdate(T entity) throws Exception;

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    List<T> saveOrUpdate(List<T> entities) throws Exception;

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    T findById(Object id) throws Exception;

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll() throws Exception;

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    List<T> findAllById(List<Object> ids) throws Exception;

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void deleteById(Object id) throws Exception;

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity) throws Exception;

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll() throws Exception;

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    void deleteAll(List<T> entities) throws Exception;

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    boolean existsById(Object id) throws Exception;

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count() throws Exception;

    /**
     *
     * @return the autowired crud repository for the calling service
     */
    E getCrudRepository() throws Exception;
}
