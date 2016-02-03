package rlroman90.rpncalculatortest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import rlroman90.rpncalculator.DoubleRounding;

import java.math.RoundingMode;

public class DoubleRoundingTest {
    public DoubleRounding doubleRounding;

    @Before
    public void setUp() {
        int storing = 5;
        int printing = 3;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        doubleRounding = new DoubleRounding(storing, printing, roundingMode);
    }

    @Test
    public void testRoundForStoringNoRounding() {
        double rawValue = 5.123453789;
        double output = 5.12345;

        double result = doubleRounding.roundForStoring(rawValue);

        assertEquals(output, result, 0.0000001);
    }

    @Test
    public void testRoundForStoringRounding() {
        double rawValue = 5.123456789;
        double output = 5.12346;

        double result = doubleRounding.roundForStoring(rawValue);

        assertEquals(output, result, 0.0000001);
    }

    @Test
    public void testRoundForPrinting() {
        double rawValue = 5.123456789;
        double output = 5.123;

        double result = doubleRounding.roundForPrinting(rawValue);

        assertEquals(output, result, 0.0000001);
    }

    @Test
    public void testRoundingTruncatingZeroes() {
        double rawValue = 5.1000000000000;
        double output = 5.1;

        double result = doubleRounding.roundForStoring(rawValue);

        assertEquals(output, result, 0.0000001);
    }
}
