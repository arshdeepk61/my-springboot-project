package org.example.exception;

import javax.swing.*;

public class ResourceNotFoundException extends RuntimeException{

   public ResourceNotFoundException(String messahge)
    {
        super(messahge);
    }

    public ResourceNotFoundException(String rourcename, String ID)
    {
        super("ab");
    }
}
