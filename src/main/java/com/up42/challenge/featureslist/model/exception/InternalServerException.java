package com.up42.challenge.featureslist.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Expection will be thrown when we have any issue while reading data from file
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{
    public InternalServerException(final String message) {
        super(message);
    }
}
