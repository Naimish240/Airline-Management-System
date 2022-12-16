package com.project.hb.airline.domain.service;

import com.project.hb.airline.domain.model.entity.Entity;
import com.project.hb.airline.domain.model.entity.Airline;

import com.project.hb.airline.domain.repository.AirlineRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("airlineService")
public class AirlineServiceImpl extends BaseService<Airline, String>
        implements AirlineService {

    private AirlineRepository<Airline, String> airlineRepository;

    @Autowired
    public AirlineServiceImpl(AirlineRepository<Airline, String> airlineRepository) {
        super(airlineRepository);
        this.airlineRepository = airlineRepository;
    }

    @Override
    public void add(Airline airline) throws Exception {
        if (airlineRepository.containsName(airline.getName())) {
            throw new Exception(String.format("There is already a airline with the name - %s", airline.getName()));
        }

        if (airline.getName() == null || "".equals(airline.getName())) {
            throw new Exception("Airline name cannot be null or empty string.");
        }
        super.add(airline);
    }

    @Override
    public Collection<Airline> findByName(String name) throws Exception {
        return airlineRepository.findByName(name);
    }

    @Override
    public void update(Airline airline) throws Exception {
        airlineRepository.update(airline);
    }

    @Override
    public void delete(String id) throws Exception {
        airlineRepository.remove(id);
    }

    @Override
    public Entity findById(String id) throws Exception {
        return airlineRepository.get(id);
    }

    @Override
    public Collection<Airline> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public Collection<Airline> getAll() {
        return airlineRepository.getAll();
    }
}
