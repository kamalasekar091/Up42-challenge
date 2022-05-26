package com.up42.challenge.featureslist.controller;

import com.up42.challenge.featureslist.model.response.FeatureResponse;
import com.up42.challenge.featureslist.service.FeatureServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * Rest Controller for Feature Service
 *  1) Get All Features by calling getallFeatures
 *  2) Get Image by calling getQuickLook
 *  3) Get Feature By ID was not implemented in controller layer,
 *     since it was not mentioned in task, assuming it may be added as future requirements
 *     Method in service layers are already present for this implementation
 */
@RestController
@RequestMapping("v1/features")
public class FeatureController {

    //Autowire Service component
    @Autowired
    private FeatureServices featureServices;

    /**
     * This Method provides the list of features in the provided Json File
     * @return List of FeatureResponse
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeatureResponse>> getallFeatures(){
        return new ResponseEntity(featureServices.findAllFeatures(), HttpStatus.OK);
    }


    /**
     * This method gets image for the feature provided
     * @param id ID for the Feature
     * @return Return the Image for requested ID
     */
    @GetMapping(value = "/{id}/quicklook",produces = IMAGE_PNG_VALUE)
    public  ResponseEntity<Byte[]> getQuickLook(@PathVariable("id") final UUID id){
        return new ResponseEntity(featureServices.findQuickLookByFeatureId(id),HttpStatus.OK);
    }

}
