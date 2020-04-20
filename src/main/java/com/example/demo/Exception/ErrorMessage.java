package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ErrorMessage {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorMessage(String message, HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorMessage(String message, HttpStatus status, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
