package com.deloitte.service;

import java.util.Collection;

import com.deloitte.repository.BookingRepository;

public abstract class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {

    private BookingRepository<TE, T> _repository;


    public void add(TE entity) throws Exception {
        _repository.add(entity);
    }

    public Collection<TE> getAll() {
        return _repository.getAll();
    }
}
