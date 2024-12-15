package com.apirest.mitocode.ruben.service.impl;

import com.apirest.mitocode.ruben.exception.NotFoundException;
import com.apirest.mitocode.ruben.repository.GenericRepo;
import com.apirest.mitocode.ruben.service.ICRUD;

import java.util.List;

abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {
    protected abstract GenericRepo<T, ID> getRepo();
    protected abstract Class<T> getEntityClass();
    protected abstract void setId(T t,ID d);


    @Override
    public List<T> getAll() {
        return getRepo().findAll();
    }

    @Override
    public T getById(ID ID) {
        return getRepo().findById(ID).orElseThrow(()->new NotFoundException(getEntityClass().getSimpleName()));
    }

    @Override
    public T create(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID ID) {
        getRepo().findById(ID).orElseThrow(()->new NotFoundException(getEntityClass().getSimpleName()));
        setId(t,ID);
        return getRepo().save(t);
    }

    @Override
    public void delete(ID ID) {
        getRepo().findById(ID).orElseThrow(()->new NotFoundException(getEntityClass().getSimpleName()));
        getRepo().deleteById(ID);
    }
}
