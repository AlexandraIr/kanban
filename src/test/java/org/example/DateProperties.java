package org.example;

import java.io.*;
import java.util.Properties;

public class DateProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/date.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static void setProperty(String key, String value) {
            String fileName  = "src/test/resources/date.properties";

            Properties props = new Properties();
            OutputStream output = null;
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(fileName);
                props.load(fileInputStream);
                fileInputStream.close();

                output = new FileOutputStream(fileName);

                props.setProperty(key, value);
                props.store(output, null);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
