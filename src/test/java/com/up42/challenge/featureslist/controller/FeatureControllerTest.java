package com.up42.challenge.featureslist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.challenge.featureslist.model.response.FeatureResponse;
import com.up42.challenge.featureslist.service.FeatureServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This Class is used to Test Controller layer
 */

@SpringBootTest
@AutoConfigureMockMvc
public class FeatureControllerTest {

    private final UUID validUUID = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
    private final UUID inValidUUID = UUID.fromString("1111111-1111-1111-1111-111111111111");
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FeatureServices featureServices;

    /**
     * Method used to valid get All Features in Controller layer
     *      1) Mock Rest Call
     *      2) Validate the numbers of objects returned
     *      3) Call Service layer directly and validate the content of mock call and service layer call. Content should match
     * @throws Exception
     */
    @DisplayName("Test Get Features")
    @Test
    void testGetFeatures() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/features").accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        FeatureResponse[] features = objectMapper.readValue(result.getResponse().getContentAsString(), FeatureResponse[].class);

        // If any of the below method fail, then the issue is with the controller layer

        assertEquals(14, features.length, "Length should Match");

        assertArrayEquals(features,featureServices.findAllFeatures().toArray()," Content should match");

    }

    /**
     * Method is used to validate Get Quick Look Method in controller class with valid ID
     * @throws Exception
     */
    @DisplayName("Test Get Quick Look")
    @Test
    void testGetQuickLook() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/features/" + validUUID.toString() + "/quicklook").accept("image/png"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        byte[] actual = result.getResponse().getContentAsByteArray();

        byte[] expected = featureServices.findQuickLookByFeatureId(validUUID);

        assertNotNull(actual);

        assertEquals(actual.length, expected.length);

        assertArrayEquals(actual,expected);

    }

    /**
     * Method USed to Validate the Feature not found scenario
     * @throws Exception
     */
    @DisplayName("Test Get Quick Look Not Found")
    @Test
    void testGetQuickLookNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/features/" + inValidUUID.toString() + "/quicklook").accept("image/png"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
