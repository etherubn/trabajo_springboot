package com.apirest.mitocode.ruben.service;

import java.util.List;

public interface ICRUD<T,ID> {
    List<T> getAll();
    T getById(ID id);
    T create(T t);
    T update(T t,ID id);
    void delete(ID id);
}
