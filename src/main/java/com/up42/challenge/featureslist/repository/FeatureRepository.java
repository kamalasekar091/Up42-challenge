package com.up42.challenge.featureslist.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.challenge.featureslist.model.Feature;
import com.up42.challenge.featureslist.model.FeatureCollection;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This Class is used to,
 *  1) Parse Data from Data File
 *  2) Load the parsed data to List of Feature Object
 *
 */

@Component
@Log4j2
public class FeatureRepository {

    private String dataFileName="source-data.json";

    private List<FeatureCollection> featureCollections;

    public FeatureRepository() {
        loadData();
    }

    /**
     * Call This method in a Constructor to load Data from File
     * If an Error Occur while reading data from file log an error
     */
    private void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        try{
            log.debug("Reading File From Resources");
            featureCollections = Arrays.asList(mapper.readValue(
                                        getClass().getClassLoader().
                                        getResourceAsStream(dataFileName),
                                        FeatureCollection[].class));
        } catch (IOException e) {
            log.error("Exception occurred while Parsing/reading Data from teh input File {}",e.getMessage());
        }
    }

    /**
     * Get all the List of Features
     * @return List of all Features Preset in Data File in a List
     */
    public List<Feature> getFeatures(){
        log.debug("Extracting Features from Collections");
        return featureCollections.stream().
                                    flatMap(x -> Arrays.stream(x.getFeatures())).
                                    collect(Collectors.toList());
    }

    /**
     * Get Features By ID
     * @param id Unique Feature ID
     * @return Requested Features in Optional Interface
     *
     *      Note : This method will be used in Service layer to query by ID and later extract Image
     *             Having this method just return Feature for now, this will enable adding an endpoint to get details only by ID in future
     */
    public Optional<Feature> getFeatureById(UUID id){
        log.debug("Find a Particular ID: "+id.toString());
        return getFeatures().stream().filter(f -> f.getProperties().getId().equals(id)).findAny();
    }

}
