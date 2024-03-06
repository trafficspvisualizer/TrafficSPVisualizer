package edu.kit.ifv.trafficspvisualizer.model.data;

/**
 * An Exception that gets thrown, when a {@link DataObject} gets accessed with an invalid key.
 */
public class InvalidDataKeyException extends Exception {

    /**
     * Constructs a new exception with no message.
     */
    public InvalidDataKeyException() {
        super();
    }

    /**
     * Constructs a new exception with the given message.
     *
     * @param message the message of the exception
     */
    public InvalidDataKeyException(String message) {
        super(message);
    }
}
