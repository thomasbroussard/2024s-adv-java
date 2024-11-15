package fr.epita.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Configuration {


    public static final String CONF_PROPERTIES_FALL_BACK = "conf.properties";
    private final Properties properties;

    private static Configuration instance;

    public static Configuration getInstance(){
        if (instance == null){
            try {
                instance = new Configuration();
            } catch (Exception e){
                throw new IllegalStateException("the program was not able to load the configuration");
            }
        }
        return instance;
    }

    private Configuration() throws IOException {
        this.properties = new Properties();
        String confLocation = System.getProperty("conf.location");
        if (confLocation == null
                || confLocation.isEmpty()
                || !Files.exists(Path.of(confLocation))) {

            confLocation = CONF_PROPERTIES_FALL_BACK;
        }
        try(FileReader reader = new FileReader(confLocation)){
            properties.load(reader);
        }

    }
    public String getValue(String confKey){
       return properties.getProperty(confKey);
    }


}
