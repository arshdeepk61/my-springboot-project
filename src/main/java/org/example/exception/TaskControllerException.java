package org.example.exception;

public class TaskControllerException extends RuntimeException {

    public TaskControllerException(String messahge) {
        super(messahge);
    }

    public TaskControllerException(String rourcename, String ID) {
        super("task Controller Exception");
    }
}
