package com.example.demo.Exception;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final APIExceptionCode code;

    public APIException(APIExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public APIExceptionCode getCode() {
        return code;
    }



}
