package com.easydto.exception;

public class DtoException extends RuntimeException{
    DtoException(Exception e){
        super(e);
    }
}
