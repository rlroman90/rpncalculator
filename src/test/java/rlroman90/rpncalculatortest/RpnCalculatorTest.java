package rlroman90.rpncalculatortest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import rlroman90.rpncalculator.RpnCalculator;

import java.util.Stack;

public class RpnCalculatorTest {

    RpnCalculator calculator;

    @Before
    public void setUp() {
        calculator = new RpnCalculator();
    }

    @Test(expected=Exception.class)
    public void testUnknownOperator() throws Exception {
        String emptyString = "";

        calculator.calcString(emptyString);
    }

    @Test
    public void  testNumberOnly() throws Exception {
        Double firstEntry = 5.0;

        Stack<Double> result = calculator.calcString(firstEntry.toString());

        assertEquals(firstEntry, result.peek());
    }

    @Test(expected=Exception.class)
    public void testNotEnoughElementsForTwoElementOperator() throws Exception {
        String notEnoughElements = "5 +";

        calculator.calcString(notEnoughElements);
    }

    @Test(expected=Exception.class)
    public void testNotEnoughElementsForSqrt() throws Exception {
        String notEnoughElements = "sqrt";

        calculator.calcString(notEnoughElements);
    }

    @Test(expected=Exception.class)
    public void testNotEnoughElementsForUndo() throws Exception {
        String notEnoughElements = "undo";

        calculator.calcString(notEnoughElements);
    }

    @Test
    public void testAddition() throws Exception {
        String addingNumbers = "5 2 +";
        Double sum = 7.0;

        Stack<Double> result = calculator.calcString(addingNumbers);

        assertEquals(sum, result.peek());
    }

    @Test
    public void testSubtraction() throws Exception {
        String subtractingNumbers = "5 3 -";
        Double difference = 2.0;

        Stack<Double> result = calculator.calcString(subtractingNumbers);

        assertEquals(difference, result.peek());
    }

    @Test
    public void testMultiplication() throws Exception {
        String multiplyingNumbers = "5 4 *";
        Double product = 20.0;

        Stack<Double> result = calculator.calcString(multiplyingNumbers);

        assertEquals(product, result.peek());
    }

    @Test
    public void testDivision() throws Exception {
        String divisionNumbers = "5 5 /";
        Double quotient = 1.0;

        Stack<Double> result = calculator.calcString(divisionNumbers);

        assertEquals(quotient, result.peek());
    }

    @Test
    public void testSquareRoot() throws Exception {
        String sqrtNumbers = "4 sqrt";
        Double squareRoot = 2.0;

        Stack<Double> result = calculator.calcString(sqrtNumbers);

        assertEquals(squareRoot, result.peek());
    }

    @Test
    public void testUndoNumber() throws Exception {
        String undoNumber = "5 2 undo";
        Double firstElement = 5.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(firstElement, result.peek());
    }

    @Test
    public void testUndoAddition() throws Exception {
        String undoNumber = "5 2 + undo";
        Double lastElement = 2.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.peek());
    }

    @Test
    public void testUndoSubtraction() throws Exception {
        String undoNumber = "5 2 - undo";
        Double lastElement = 2.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.peek());
    }

    @Test
    public void testUndoMultiplication() throws Exception {
        String undoNumber = "5 2 * undo";
        Double lastElement = 2.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.peek());
    }

    @Test
    public void testUndoDivision() throws Exception {
        String undoNumber = "5 2 / undo";
        Double lastElement = 2.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.peek());
    }

    @Test
    public void testClear() throws Exception {
        String sqrtNumbers = "4 clear";

        Stack<Double> result = calculator.calcString(sqrtNumbers);

        assertEquals(0, result.size());
    }

    @Test
    public void testUndoClear() throws Exception {
        String undoNumber = "5 clear undo";
        Double firstElement = 5.0;

        Stack<Double> result = calculator.calcString(undoNumber);

        assertEquals(firstElement, result.peek());
    }
}
