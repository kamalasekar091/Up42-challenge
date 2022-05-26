package com.up42.challenge.featureslist.service;

import com.up42.challenge.featureslist.model.Feature;
import com.up42.challenge.featureslist.model.response.FeatureResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class will be used to map the internal data object to the teh response object
 */
@Component
public class DataMapper implements Converter<Feature, FeatureResponse> {

    @Override
    public FeatureResponse convert(Feature source) {
        return FeatureResponse.builder()
                .id(source.getProperties().getId())
                .timestamp(source.getProperties().getTimestamp())
                .beginViewingDate(source.getProperties().getAcquisition().getBeginViewingDate())
                .endViewingDate(source.getProperties().getAcquisition().getEndViewingDate())
                .missionName(source.getProperties().getAcquisition().getMissionName())
                .build();

    }
}
