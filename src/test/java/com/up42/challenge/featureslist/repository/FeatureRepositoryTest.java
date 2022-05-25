package com.up42.challenge.featureslist.repository;

import com.up42.challenge.featureslist.model.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Clas to Validate FeatureRepository Component
 */
@SpringBootTest
public class FeatureRepositoryTest {

    @Autowired
    private FeatureRepository featureRepository;

    private final UUID validUUID = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea");

    private final UUID inValidUUID = UUID.fromString("1111111-1111-1111-1111-111111111111");

    /**
     * Test Method to check Get All Features
     *  Use Assert Equals to validate the number of features returned
     *  USe Assert True/False to validate the if the valid/invalid id is present in feature stream
     */
    @DisplayName("Check GetAll Features")
    @Test
    void testGetAllFeatures(){
        List<Feature> features = featureRepository.getFeatures();
        assertEquals(features.size(),14,"Validate if there are 14 elements in a array");
        assertTrue(features.stream().filter(f -> f.getProperties().getId().equals(validUUID)).findAny().isPresent() , "Check if valid ID is present in the Feature Stream");
        assertFalse(features.stream().filter(f -> f.getProperties().getId().equals(inValidUUID)).findAny().isPresent(), "check if Invalid ID is not present in Feature Stream");
    }

    /**
     * Test Method to Validate Get a Particular Feature By Id
     *  USe Assert True/False to Validate the valid/Invalid ID
     */
    @DisplayName("Test GetById Features")
    @Test
    void testGetIdFeature(){

        assertTrue(featureRepository.getFeatureById(validUUID).isPresent(),"Check if Valid Id return Object");
        assertFalse(featureRepository.getFeatureById(inValidUUID).isPresent(), "Check if Invalid Id does not return Object");

    }

}
