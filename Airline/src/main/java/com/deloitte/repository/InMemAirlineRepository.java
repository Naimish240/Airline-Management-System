package com.deloitte.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.project.hb.airline.domain.model.entity.Airline;
import org.springframework.stereotype.Repository;

@Repository("airlineRepository")
public class InMemAirlineRepository implements AirlineRepository<Airline, String> {

    private Map<String, Airline> entities;

    public InMemAirlineRepository() {
        entities = new HashMap();
        // public Airline(String name, String id, String location, List<Room> rooms) {
        Airline airline = new Airline("Le Meurice", "1", "Paris", null);
        entities.put("1", airline);
        airline = new Airline("L'Ambroisie", "2", "Helsinki", null);
        entities.put("2", airline);
        airline = new Airline("Arpège", "3", "Praha", null);
        entities.put("3", airline);
        airline = new Airline("Alain Ducasse au Plaza Athénée", "4", "Zurich", null);
        entities.put("4", airline);
        airline = new Airline("Pavillon LeDoyen", "5", "Berlin", null);
        entities.put("5", airline);
        airline = new Airline("Pierre Gagnaire", "6", "Frankfurt", null);
        entities.put("6", airline);
        airline = new Airline("Guy Savoy", "7", "NewYork", null);
        entities.put("7", airline);
        airline = new Airline("Pré Catelan", "8", "Chicago", null);
        entities.put("8", airline);
        airline = new Airline("L'Astrance", "9", "HoChiMinh", null);
        entities.put("9", airline);
        airline = new Airline("Le Bristol", "10", "California", null);
        entities.put("10", airline);
    }

    @Override
    public Collection<Airline> findAll() {
        return entities.values();
    }

    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            // Exception Handler
        }
        return false;
    }

    @Override
    public void add(Airline entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    @Override
    public void update(Airline entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public Airline get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Airline> getAll() {
        return entities.values();
    }

    @Override
    public Collection<Airline> findByName(String name) throws Exception {
        Collection<Airline> airlines = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                airlines.add(v);
            }
        });
        return airlines;
    }

    @Override
    public Collection<Airline> findByLocation(String location) throws Exception {
        Collection<Airline> airlines = new ArrayList<>();
        entities.forEach((k, v) -> {
            if (v.getLocation().trim().equals(location.trim())) {
                airlines.add(v);

            }

        });
        return airlines;
    }
}
