package rlroman90.rpncalculator;

import java.io.*;
import java.math.RoundingMode;
import java.util.Properties;

public class PropertiesLoader {
    private static String intRegex = "[0-9]+";
    private static int defaultStoringPrecision = 15;
    private static int defaultPrintingPrecision = 15;
    private static RoundingMode defaultRoundingMode = RoundingMode.HALF_UP;

    public int StoringPrecision;
    public int PrintingPrecision;
    public RoundingMode SetRoundingMode;

    public void loadProperties(String fileName) {
        Properties properties = new Properties();
        try {
            InputStream input =
                    new FileInputStream(new File(ClassLoader.getSystemClassLoader().getResource(fileName).toURI()));
            properties.load(input);
            setApplicationProperties(properties);
        }
        catch(Exception ex) {
            setPropertiesToDefaultValues();
        }
    }

    private void setApplicationProperties(Properties properties) {
        setRoundingPrecisions(properties);
        setRoundingMode(properties);
    }

    private void setRoundingPrecisions(Properties properties) {
        setStoringPrecision(properties);
        setPrintingPrecision(properties);
    }

   private void setStoringPrecision(Properties properties) {
        if(properties.containsKey("StoringPrecision") && properties.getProperty("StoringPrecision").matches(intRegex))
            StoringPrecision = Integer.parseInt(properties.getProperty("StoringPrecision"));
        else
            StoringPrecision = defaultStoringPrecision;
    }

    private void setPrintingPrecision(Properties properties) {
        if(properties.containsKey("PrintingPrecision") && properties.getProperty("PrintingPrecision").matches(intRegex))
            PrintingPrecision = Integer.parseInt(properties.getProperty("PrintingPrecision"));
        else
            PrintingPrecision = defaultPrintingPrecision;
    }

    private void setRoundingMode(Properties properties) {
        if(properties.containsKey("RoundingMode")) {
            for (RoundingMode mode : RoundingMode.values()) {
                if(mode.name().equals(properties.getProperty("RoundingMode")))
                    SetRoundingMode = mode;
            }
        }
        if(SetRoundingMode == null)
            SetRoundingMode = defaultRoundingMode;
    }

    private void setPropertiesToDefaultValues() {
        StoringPrecision = defaultStoringPrecision;
        PrintingPrecision = defaultPrintingPrecision;
        SetRoundingMode = defaultRoundingMode;
    }
}
