package com.up42.challenge.featureslist.service;


import com.up42.challenge.featureslist.exception.FeatureNotFoundException;
import com.up42.challenge.featureslist.model.Acquisition;
import com.up42.challenge.featureslist.model.Feature;
import com.up42.challenge.featureslist.model.Properties;
import com.up42.challenge.featureslist.repository.FeatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *  Test Class to test Service Layer.
 *      Mocking is used in this layer - Assumption is that the Service layer would be eventually an endpoint or DB
 *                                      To enable continual testing the service layer is mocked
 */
@SpringBootTest
public class FeatureServiceTest {

    @MockBean
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureServices featureServices;

    private List<Feature> features= new ArrayList<>();

    private Feature singleFeature;

    private final UUID validUUID = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea");

    private final UUID inValidUUID = UUID.fromString("1111111-1111-1111-1111-111111111111");

    /**
     * Setup Mock Objects which will be used for testing in below classes.
     */

    @BeforeEach
    void setup(){

        Acquisition acquisition = Acquisition.builder().beginViewingDate(1554831167697L)
                .endViewingDate(1554831202043L)
                .missionName("Test Mission").build();

        Properties properties = Properties.builder()
                .id(validUUID)
                .timestamp(1554831167697L)
                .acquisition(acquisition)
                .quicklook("quickLook".getBytes(StandardCharsets.UTF_8)).build();
        singleFeature=Feature.builder()
                .properties(properties).build();
        features.add(singleFeature);

    }

    /**
     * Method to Validate get all features
     */
    @DisplayName("Test Get All Features")
    @Test
    void testGetAllFeatures(){
        when(featureRepository.getFeatures()).thenReturn(features);

        assertEquals(featureServices.findAllFeatures().size(),1,"Size should be 1");

        assertEquals(featureServices.findAllFeatures().get(0).getId(),validUUID);

    }

    /**
     * Method to test Get Quick Look
     */
    @DisplayName("Test Get Image")
    @Test
    void testGetImage(){
        when(featureRepository.getFeatureById(validUUID)).thenReturn(Optional.ofNullable(singleFeature));

        byte[] actual = featureServices.findQuickLookByFeatureId(validUUID);

        assertNotNull(actual, "Return value should not be null");

        assertArrayEquals(actual,"quickLook".getBytes(StandardCharsets.UTF_8),"actual result should match with mock results");

    }


    /**
     * Method to validate Get Feature By ID, using valid ID
     */
    @DisplayName("Test Get Feature By Valid Id")
    @Test
    void testGetByIdValidCase(){
        when(featureRepository.getFeatureById(validUUID)).thenReturn(Optional.ofNullable(singleFeature));

        assertEquals("Test Mission",featureServices.getFeatureById(validUUID).getProperties().getAcquisition().getMissionName());

    }

    /**
     * Method to validate Get Feature By ID using Invalid Case
     */
    @DisplayName("Test Get Feature By InValid Id")
    @Test
    void testGetByIdInValidCase(){
        when(featureRepository.getFeatureById(validUUID)).thenThrow(FeatureNotFoundException.class);

        assertThrows(FeatureNotFoundException.class,()-> featureServices.getFeatureById(inValidUUID));
    }
}
