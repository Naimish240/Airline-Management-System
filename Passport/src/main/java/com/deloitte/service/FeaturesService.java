package com.deloitte.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.repository.FeaturesRepository;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;



@Service
public class FeaturesService {
    private final FeaturesRepository featuresRepository;
    @Autowired
    public FeaturesService(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    public List<Feature> findedAllFeatures() {
        return featuresRepository.findAll();
    }
}
