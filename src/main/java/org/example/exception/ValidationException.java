package org.example.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String messahge)
    {
        super(messahge);
    }

    public ValidationException(String field, String reason)
    {
        super(String.format("%s field %d reason Not found",field,reason));
    }
}
