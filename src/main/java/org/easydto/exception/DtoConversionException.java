package org.easydto.exception;

public class DtoConversionException extends DtoException{

    public DtoConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DtoConversionException(String message) {
        super(message);
    }
}
