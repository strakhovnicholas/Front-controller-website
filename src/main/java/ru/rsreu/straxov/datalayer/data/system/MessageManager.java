package ru.rsreu.straxov.datalayer.data.system;

import java.util.ResourceBundle;

public class MessageManager {
    private static final String MESSAGE = "message";
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(MESSAGE);

    // Class retrieves information from the messages.properties file
    private MessageManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
