package com.deloitte.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.hb.type.domain.model.entity.Entity;
import com.project.hb.type.domain.model.entity.Type;
import com.project.hb.type.domain.service.TypeService;
import com.project.hb.type.domain.valueobject.TypeVO;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/types")
public class TypeController {

    protected Logger logger = Logger.getLogger(TypeController.class.getName());

    protected TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @HystrixCommand(fallbackMethod = "defaultTypes")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Type>> findByName(@RequestParam("name") String name) {
        logger.info(
                String.format("type-service findByName() invoked:%s for %s ", typeService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Type> types;
        try {
            types = typeService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return types.size() > 0 ? new ResponseEntity<>(types, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultType")
    public ResponseEntity<Entity> findById(@PathVariable("id") String id) {
        logger.info(String.format("type-service findById() invoked:%s for %s ", typeService.getClass().getName(), id));
        id = id.trim();
        Entity type;
        try {
            type = typeService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return type != null ? new ResponseEntity<>(type, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Type> add(@RequestBody TypeVO typeVO) {
        logger.info(String.format("type-service add() invoked: %s for %s", typeService.getClass().getName(),
                typeVO.getName()));
        System.out.println(typeVO);
        Type type = new Type(null, null, null, null, null);
        BeanUtils.copyProperties(typeVO, type);
        try {
            typeService.add(type);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Type REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Entity> defaultType(String input) {
        logger.warning("Fallback method for type-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Collection<Type>> defaultTypes(String input) {
        logger.warning("Fallback method for type-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
