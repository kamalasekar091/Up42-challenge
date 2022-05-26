package com.up42.challenge.featureslist.service;

import com.up42.challenge.featureslist.exception.FeatureNotFoundException;
import com.up42.challenge.featureslist.model.Feature;
import com.up42.challenge.featureslist.model.response.FeatureResponse;
import com.up42.challenge.featureslist.repository.FeatureRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Service Layer will call Repo layer to get data and convert to response object
 */

@Service
@Log4j2
public class FeatureServices {

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private FeatureRepository repo;

    /**
     * This method is used to find all features present in data files
     *  Data Mapper Class is used to Map Internal object to Response Object
     * @return List of FeatureResponse
     */
    public List<FeatureResponse> findAllFeatures(){
        log.debug("Returning all features");
        return repo.getFeatures().stream().map(dataMapper::convert).collect(Collectors.toList());
    }


    /**
     * This Method is used to find Image for the provided ID
     * @param id ID of the Feature
     * @return QuickLook in Byte Array
     *  Assumption: When a feature is having blank value of Quick Look it is assumed to valid and no exception will be thrown
     */
    public byte[] findQuickLookByFeatureId(UUID id){
        log.debug(format("Returning Image for id={%s}.", id));
        return  getFeatureById(id).getProperties().getQuicklook();
    }

    /**
     * This method is not accessed by
     * @param id ID of the Feature
     * @return Features of the provided IF or throw Feature Not Found Exception
     *  Assumption : having separate method for get feature by Id, since i am expecting to have a separate endpoint for find by Id in future
     */
    public Feature getFeatureById(UUID id){
        log.debug(format("Returning feature by id={%s}.", id));
        return repo.getFeatureById(id).orElseThrow(FeatureNotFoundException::new);
    }

}
