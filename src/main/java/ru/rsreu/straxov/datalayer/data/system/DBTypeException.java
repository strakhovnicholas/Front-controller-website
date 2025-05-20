package ru.rsreu.straxov.datalayer.data.system;

@SuppressWarnings("serial")
public class DBTypeException extends RuntimeException {
    public DBTypeException() {
        super();
    }

    public DBTypeException(String message) {
        super(message);
    }
}
