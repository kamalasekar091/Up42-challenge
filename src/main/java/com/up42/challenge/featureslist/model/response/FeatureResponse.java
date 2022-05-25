package com.up42.challenge.featureslist.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * This is the Response Object for the users should match below pattern
 *      {
 *              "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
 *              "timestamp": 1554831167697,
 *              "endViewingDate": 1554831202043,
 *              "beginViewingDate": 1554831167697,
 *              "missionName": "Sentinel-1B"
 *      }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureResponse {

    private UUID id;

    private Long timestamp;

    private Long endViewingDate;

    private Long beginViewingDate;

    private String missionName;
}
