package com.springboot.facturacionfrasson.exception;

public class ValidationException extends Exception {
    private String field;
    private String message;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage(){
        return message;
    }
}
