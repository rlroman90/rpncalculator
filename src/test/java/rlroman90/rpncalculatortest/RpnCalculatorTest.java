package rlroman90.rpncalculatortest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import rlroman90.rpncalculator.Rounding;
import rlroman90.rpncalculator.RpnCalculator;
import rlroman90.rpncalculator.StackOutput;

public class RpnCalculatorTest {

    RpnCalculator calculator;
    Rounding testRounding;

    @Before
    public void setUp() {
        testRounding = Mockito.mock(Rounding.class);
        calculator = new RpnCalculator(testRounding);
    }

    @Test
    public void testEmptyInput() {
        String emptyString = "";
        String exception = "Input String was empty.";

        StackOutput result = calculator.calcString(emptyString);

        assertEquals(exception, result.ExceptionMessage);
    }

    @Test
    public void testInvalidOperator() {
        String invalidString = "5 2 q";
        String exception = "operator q (position: 4): invalid operator";

        StackOutput result = calculator.calcString(invalidString);

        assertEquals(exception, result.ExceptionMessage);
    }

    @Test
    public void  testNumberOnly() {
        String firstEntry = "5.0";
        String output = "5";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);

        StackOutput result = calculator.calcString(firstEntry);

        assertEquals(output, result.OutputString);
    }

    @Test
    public void testNotEnoughElementsForTwoElementOperator() {
        String notEnoughElements = "5 2 + +";
        String exception = "operator + (position: 6): insufficient parameters";

        StackOutput result = calculator.calcString(notEnoughElements);

        assertEquals(exception, result.ExceptionMessage);
    }

    @Test
    public void testNotEnoughElementsForSqrt() {
        String notEnoughElements = "sqrt";
        String exception = "operator sqrt (position: 0): insufficient parameters";

        StackOutput result = calculator.calcString(notEnoughElements);

        assertEquals(exception, result.ExceptionMessage);
    }

    @Test
    public void testNotEnoughElementsForUndo() {
        String notEnoughElements = "undo";
        String exception = "operator undo (position: 0): insufficient parameters";

        StackOutput result = calculator.calcString(notEnoughElements);

        assertEquals(exception, result.ExceptionMessage);
    }

    @Test
    public void testAddition() {
        String addingNumbers = "5 2 +";
        String sum = "7";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);
        Mockito.when(testRounding.roundForStoring(7.0)).thenReturn(7.0);

        StackOutput result = calculator.calcString(addingNumbers);

        assertEquals(sum, result.OutputString);
    }

    @Test
    public void testSubtraction() {
        String subtractingNumbers = "5 3 -";
        String difference = "2";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(3.0)).thenReturn(3.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);

        StackOutput result = calculator.calcString(subtractingNumbers);

        assertEquals(difference, result.OutputString);
    }

    @Test
    public void testMultiplication() {
        String multiplyingNumbers = "5 4 *";
        String product = "20";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(4.0)).thenReturn(4.0);
        Mockito.when(testRounding.roundForStoring(20.0)).thenReturn(20.0);

        StackOutput result = calculator.calcString(multiplyingNumbers);

        assertEquals(product, result.OutputString);
    }

    @Test
    public void testDivision() {
        String divisionNumbers = "5 5 /";
        String quotient = "1";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(1.0)).thenReturn(1.0);

        StackOutput result = calculator.calcString(divisionNumbers);

        assertEquals(quotient, result.OutputString);
    }

    @Test
    public void testSquareRoot() {
        String sqrtNumbers = "4 sqrt";
        String squareRoot = "2";
        Mockito.when(testRounding.roundForStoring(4.0)).thenReturn(4.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);

        StackOutput result = calculator.calcString(sqrtNumbers);

        assertEquals(squareRoot, result.OutputString);
    }

    @Test
    public void testUndoNumber() {
        String undoNumber = "5 2 undo";
        String firstElement = "5";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(firstElement, result.OutputString);
    }

    @Test
    public void testUndoAddition() {
        String undoNumber = "5 2 + undo";
        String lastElement = "5 2";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);
        Mockito.when(testRounding.roundForStoring(7.0)).thenReturn(7.0);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.OutputString);
    }

    @Test
    public void testUndoSubtraction() {
        String undoNumber = "5 2 - undo";
        String lastElement = "5 2";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);
        Mockito.when(testRounding.roundForStoring(3.0)).thenReturn(3.0);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.OutputString);
    }

    @Test
    public void testUndoMultiplication() {
        String undoNumber = "5 2 * undo";
        String lastElement = "5 2";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);
        Mockito.when(testRounding.roundForStoring(10.0)).thenReturn(10.0);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.OutputString);
    }

    @Test
    public void testUndoDivision() {
        String undoNumber = "5 2 / undo";
        String lastElement = "5 2";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);
        Mockito.when(testRounding.roundForStoring(2.0)).thenReturn(2.0);
        Mockito.when(testRounding.roundForStoring(2.5)).thenReturn(2.5);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(lastElement, result.OutputString);
    }

    @Test
    public void testClear() {
        String sqrtNumbers = "4 clear";

        StackOutput result = calculator.calcString(sqrtNumbers);

        assertTrue(result.OutputString.isEmpty());
    }

    @Test
    public void testUndoClear() {
        String undoNumber = "5 clear undo";
        String firstElement = "5";
        Mockito.when(testRounding.roundForStoring(5.0)).thenReturn(5.0);

        StackOutput result = calculator.calcString(undoNumber);

        assertEquals(firstElement, result.OutputString);
    }
}
