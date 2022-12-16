package com.deloitte.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.deloitte.entity.Booking;

import jakarta.persistence.Entity;

public interface BookingService {

  
    public void add(Booking booking) throws Exception;


    public void update(Booking booking) throws Exception;

    public void delete(String id) throws Exception;


    public Entity findById(String id) throws Exception;

    public Collection<Booking> findByName(String name) throws Exception;


    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;


    public void produceBookingOrderEvent(Booking booking) throws Exception;

}
