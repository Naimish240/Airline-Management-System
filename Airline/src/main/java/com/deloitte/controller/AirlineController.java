package com.deloitte.controller;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deloitte.service.AirlineService;

import jakarta.persistence.Entity;

import com.deloitte.entity.Airline;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/airlines")
public class AirlineController {

    protected Logger logger = Logger.getLogger(AirlineController.class.getName());

    @Autowired
    protected AirlineService airlineService;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/services")
    public List<String> home() {
        return client.getServices();
    }

    @HystrixCommand(fallbackMethod = "defaultAirlines")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Airline>> findByName(@RequestParam("name") String name) {
        logger.info(
                String.format("airline-service findByName() invoked:{} for {} ", airlineService.getClass().getName(),
                        name));
        name = name.trim().toLowerCase();
        Collection<Airline> airlines;
        try {
            airlines = airlineService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return airlines.size() > 0 ? new ResponseEntity<>(airlines, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Collection<Airline>> findAll() {

        Collection<Airline> airlines;
        try {
            airlines = airlineService.getAll();
        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(airlines, HttpStatus.OK);

    }

    @HystrixCommand(fallbackMethod = "defaultAirline")
    @RequestMapping(value = "/{airline_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("airline_id") String id) {
        logger.info(
                String.format("airline-service findById() invoked:{} for {} ", airlineService.getClass().getName(),
                        id));
        id = id.trim();
        Entity airline;
        try {
            airline = airlineService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return airline != null ? new ResponseEntity<>(airline, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Airline> add(@RequestBody AirlineDto airlineDto) {
        logger.info(String.format("airline-service add() invoked: %s for %s", airlineService.getClass().getName(),
                airlineDto.getName()));

        Airline airline = new Airline(null, null, null, null);
        BeanUtils.copyProperties(airlineDto, airline);
        try {
            airlineService.add(airline);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Airline REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Entity> defaultAirline(String input) {
        logger.warning("Fallback method for airline-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Collection<Airline>> defaultAirlines(String input) {
        logger.warning("Fallback method for user-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
