package rlroman90.rpncalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleRounding implements Rounding {

    private int storingPrecision;
    private int printingPrecision;
    private RoundingMode roundingMode;

    public DoubleRounding(int desiredStoringPrecision, int desiredPrintingPrecision, RoundingMode desiredRoundingMode) {
        storingPrecision = desiredStoringPrecision;
        printingPrecision = desiredPrintingPrecision;
        roundingMode = desiredRoundingMode;
    }

    public double roundForStoring(double rawValue) {
        return roundDouble(rawValue, storingPrecision);
    }

    public double roundForPrinting(double rawValue) {
        return roundDouble(rawValue, printingPrecision);
    }

    private double roundDouble(double rawValue, int precision) {
        BigDecimal rawDecimal = new BigDecimal(rawValue);
        BigDecimal roundedDecimal = rawDecimal.setScale(precision, roundingMode);
        return roundedDecimal.doubleValue();
    }
}
