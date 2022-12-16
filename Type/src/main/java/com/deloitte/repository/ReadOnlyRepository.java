package com.deloitte.repository;


import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {


    boolean contains(T id);


    TE get(T id);

    Collection<TE> getAll();
}
