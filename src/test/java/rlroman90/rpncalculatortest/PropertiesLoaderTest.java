package rlroman90.rpncalculatortest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import rlroman90.rpncalculator.PropertiesLoader;

import java.math.RoundingMode;

public class PropertiesLoaderTest {
    // Note these match the defaults in the functional code, only to be used for test verification
    public int storingPrecisionDefault = 15;
    public int printingPrecisionDefault = 15;
    public RoundingMode roundingModeDefault = RoundingMode.HALF_UP;

    public String configFileFolder = "TestConfigFiles\\";

    public PropertiesLoader testLoader;

    @Before
    public void setUp() {
        testLoader = new PropertiesLoader();
    }

    @Test
    public void testNoConfigFile() {
        testLoader.loadProperties("testconfigWrong.properties");

        assertEquals(storingPrecisionDefault, testLoader.StoringPrecision);
        assertEquals(printingPrecisionDefault, testLoader.PrintingPrecision);
        assertEquals(roundingModeDefault, testLoader.SetRoundingMode);
    }

    @Test
    public void testStoringPrecisionNotPresent() {
        testLoader.loadProperties(configFileFolder + "testconfig1.properties");

        assertEquals(storingPrecisionDefault, testLoader.StoringPrecision);
    }

    @Test
    public void testStoringPrecisionInvalidFormat() {
        testLoader.loadProperties(configFileFolder + "testconfig2.properties");

        assertEquals(storingPrecisionDefault, testLoader.StoringPrecision);
    }

    @Test
    public void testConfigHasCorrectStoringPrecision() {
        testLoader.loadProperties(configFileFolder + "testconfig3.properties");

        assertNotEquals(storingPrecisionDefault, testLoader.StoringPrecision);
    }

    @Test
    public void testPrintingPrecisionNotPresent() {
        testLoader.loadProperties(configFileFolder + "testconfig2.properties");

        assertEquals(printingPrecisionDefault, testLoader.PrintingPrecision);
    }

    @Test
    public void testPrintingPrecisionInvalidFormat() {
        testLoader.loadProperties(configFileFolder + "testconfig1.properties");

        assertEquals(printingPrecisionDefault, testLoader.PrintingPrecision);
    }

    @Test
    public void testConfigHasCorrectPrintingPrecision() {
        testLoader.loadProperties(configFileFolder + "testconfig3.properties");

        assertNotEquals(printingPrecisionDefault, testLoader.PrintingPrecision);
    }

    @Test
    public void testRoundingModeNotPresent() {
        testLoader.loadProperties(configFileFolder + "testconfig2.properties");

        assertEquals(roundingModeDefault, testLoader.SetRoundingMode);
    }

    @Test
    public void testRoundingModeInvalid() {
        testLoader.loadProperties(configFileFolder + "testconfig3.properties");

        assertEquals(roundingModeDefault, testLoader.SetRoundingMode);
    }

    @Test
    public void testConfigHasCorrectRoundingMode() {
        testLoader.loadProperties(configFileFolder + "testconfig1.properties");

        assertNotEquals(roundingModeDefault, testLoader.SetRoundingMode);
    }
}
