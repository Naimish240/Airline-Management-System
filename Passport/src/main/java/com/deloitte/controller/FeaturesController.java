package com.deloitte.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.service.FeaturesService;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;


@RestController
public class FeaturesController {
    private FeaturesService featuresService;

    @Autowired
    public FeaturesController(FeaturesService featuresService) {
        this.featuresService = featuresService;
    }

    @RequestMapping("/findallFeatures")
    private List<Feature> findedAllFeatures() {
        return featuresService.findedAllFeatures();
    }
}

