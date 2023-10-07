package com.hobbyshop.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a request cannot be performed due to one or more invalid parameters.
 *  
 * Will send an HTTP 400 BAD REQUEST status.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

     /** Name of the request that caused this exception. */
    private String requestName;

    /** Name of the field in request. */
    private String fieldName;

    /** Value of the field in request. */
    private Object fieldValue;

    /**
     * Constructs a new BadRequestException with the provided details.
     * 
     * @param requestName Name of the request that caused this exception.
     * @param fieldName Name of the field that caused this exception.
     * @param fieldValue Value of the field that caused this exception.
     */
    public BadRequestException(String requestName, String fieldName, Object fieldValue) {
        super(String.format("%s cannot be performed with %s : '%s'", requestName, fieldName, fieldValue));
        this.requestName = requestName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
    
}
