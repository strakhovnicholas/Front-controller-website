package ru.rsreu.straxov.datalayer.data.system;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String CONFIG = "config";
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(CONFIG);

    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
