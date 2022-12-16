package com.project.hb.airline.domain.service;

import com.project.hb.airline.domain.model.entity.Entity;
import com.project.hb.airline.domain.model.entity.Airline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface AirlineService {

    public void add(Airline airline) throws Exception;

    public void update(Airline airline) throws Exception;

    public void delete(String id) throws Exception;

    public Entity findById(String airlineId) throws Exception;

    public Collection<Airline> findByName(String name) throws Exception;

    public Collection<Airline> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

    public Collection<Airline> getAll();
}
