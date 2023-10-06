package com.hobbyshop.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a resource is not found in database.
 * 
 * Will send an HTTP 404 NOT FOUND response status.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /** Name of the resource that was not found. */
    private String resourceName;

    /** Name of the field in query. */
    private String fieldName;

    /** Value of the field in query. */
    private Object fieldValue;

    /**
     * Constructs a new ResourceNotFoundException with the provided details.
     *
     * @param resourceName Name of the resource that was not found.
     * @param fieldName Name of the field in query.
     * @param fieldValue Value of the field in query.
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
