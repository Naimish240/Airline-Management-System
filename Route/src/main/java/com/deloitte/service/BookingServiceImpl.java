package com.deloitte.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.entity.Booking;
import com.deloitte.repository.BookingRepository;

@Service("bookingService")
public class BookingServiceImpl extends BaseService<Booking, String>
        implements BookingService {

    private static final Logger LOG = LoggerFactory.getLogger(BookingServiceImpl.class);
    private BookingRepository<Booking, String> bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository<Booking, String> bookingRepository) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Booking booking) throws Exception {
        if (bookingRepository.containsName(booking.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", booking.getName()));
        }

        if (booking.getName() == null || "".equals(booking.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(booking);
        produceBookingOrderEvent(booking);
    }


    @Override
    public Collection<Booking> findByName(String name) throws Exception {
        return bookingRepository.findByName(name);
    }


    @Override
    public void update(Booking booking) throws Exception {
        bookingRepository.update(booking);
    }


    @Override
    public void delete(String id) throws Exception {
        bookingRepository.remove(id);
    }


    @Override
    public Entity findById(String id) throws Exception {
        return bookingRepository.get(id);
    }


    @Override
    public void produceBookingOrderEvent(Booking booking) throws Exception {
        final BookingOrder.Builder boBuilder = BookingOrder.newBuilder();
        boBuilder.setId(booking.getId());
        boBuilder.setName(booking.getName());
        boBuilder.setHotelId(booking.getHotelId());
        boBuilder.setTableId(booking.getRoomId());
        boBuilder.setUserId(booking.getUserId());
        boBuilder.setDate(booking.getDate().toString());
        boBuilder.setTime(booking.getTime().toString());
        BookingOrder bo = boBuilder.build();
        final Message<BookingOrder> message = MessageBuilder.withPayload(bo).build();
        bookingMessageChannels.bookingOrderOutput().send(message);
        LOG.info("sending bookingOrder: {}", booking);
    }

    @Override
    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void add(Booking booking) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Booking booking) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void produceBookingOrderEvent(Booking booking) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
