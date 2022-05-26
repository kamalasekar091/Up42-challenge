package com.up42.challenge.featureslist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeatureNotFoundException extends RuntimeException {

    public FeatureNotFoundException() {
        super();
    }

}
