package eu.yayi.commons.exceptions;

public class BusinessException extends Exception {

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }
}