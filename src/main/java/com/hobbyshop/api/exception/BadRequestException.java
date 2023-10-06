package com.hobbyshop.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private String requestName;
    private String fieldName;
    private Object fieldValue;

    // TODO
    public BadRequestException(String requestName, String fieldName, Object fieldValue) {
        super(String.format("%s cannot be performed with %s : '%s", requestName, fieldName, fieldValue));
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
