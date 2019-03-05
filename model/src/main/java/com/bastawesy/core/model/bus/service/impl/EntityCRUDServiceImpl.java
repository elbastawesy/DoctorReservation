package com.bastawesy.core.model.bus.service.impl;

import com.bastawesy.core.model.bus.service.EntityCRUDService;
import com.bastawesy.core.model.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class EntityCRUDServiceImpl<T extends BaseEntity, E extends CrudRepository> implements EntityCRUDService<T, E> {

    @Autowired
    private E repository;


    @Override
    public T saveOrUpdate(T entity) throws Exception {
        return (T) repository.save(entity);
    }

    @Override
    public List<T> saveOrUpdate(List<T> entities) throws Exception {
        return (List<T>) repository.saveAll(entities);
    }

    @Override
    public T findById(Object id) throws Exception {
        return (T) repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() throws Exception {
        return (List<T>) repository.findAll();
    }

    @Override
    public List<T> findAllById(List<Object> ids) throws Exception {
        return (List<T>) repository.findAllById(ids);
    }

    @Override
    public void deleteById(Object id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) throws Exception {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Override
    public void deleteAll(List<T> entities) throws Exception {
        repository.deleteAll(entities);
    }

    @Override
    public boolean existsById(Object id) throws Exception {
        return repository.existsById(id);
    }

    @Override
    public long count() throws Exception {
        return repository.count();
    }

    @Override
    public E getCrudRepository() throws Exception {
        if(repository == null)
            throw  new NullPointerException("Null repository");
        return repository;
    }
}
