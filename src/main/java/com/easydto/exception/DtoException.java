package com.easydto.exception;

public class DtoException extends RuntimeException{

    DtoException(Exception e){
        super(e);
    }

    public DtoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DtoException(String message) {
        super(message);
    }
}
