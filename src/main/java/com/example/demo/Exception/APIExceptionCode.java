package com.example.demo.Exception;

public enum APIExceptionCode {
    VERR001("All fields are mandatory"),
    VERR002("Volunteer cannot be underage"),
    AERR001("Slum cannot be null");

    private final String message;

    private APIExceptionCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }



}
