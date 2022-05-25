package com.up42.challenge.featureslist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
    private Properties properties;
}
