package com.deloitte.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;

@Repository
public interface FeaturesRepository extends CrudRepository<Feature, Long> {
    List<Feature> findAll();
}