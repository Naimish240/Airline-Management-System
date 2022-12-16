package com.deloitte.repository;

import java.awt.print.Book;
import java.nio.charset.CodingErrorAction;
import java.util.Collection;

import org.aspectj.apache.bcel.util.Repository;


public interface BookingRepository<Booking, String> extends Repository {


    boolean containsName(String name);


    public Collection<Booking> findByName(String name) throws Exception;



    public Collection<Booking> findByUserId(String id) throws Exception;

    public Collection<Booking> findByHotelId(String id) throws Exception;

    public Collection<Booking> findByRoomId(String id) throws Exception;
}
