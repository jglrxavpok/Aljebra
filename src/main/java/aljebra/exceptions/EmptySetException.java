package aljebra.exceptions;

public class EmptySetException extends RuntimeException {

    public EmptySetException() {
        super();
    }

    public EmptySetException(String message) {
        super(message);
    }
}
