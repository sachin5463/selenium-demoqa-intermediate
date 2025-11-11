package com.demoqa.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("src/main/resources/config.properties"));
            System.out.println("⚙️ Headless flag loaded as: " + props.getProperty("headless"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getTestData(String key) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/test/resources/testdata/testdata.properties"));
            return props.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read testdata.properties");
        }
    }
}