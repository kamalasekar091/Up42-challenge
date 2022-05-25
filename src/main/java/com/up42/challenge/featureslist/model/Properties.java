package com.up42.challenge.featureslist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private UUID id;

    private Long timestamp;

    private Acquisition acquisition;

    private byte[] quicklook;

}
