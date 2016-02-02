package rlroman90.rpncalculator;

import java.util.HashMap;
import java.util.Stack;

public class RpnCalculator {
    private static String DecimalRegex = "[0-9]+(\\.[0-9][0-9]?)?";

    private static HashMap<String, String> twoElementOperationsAndInverses;

    private Stack<Double> valueStack;
    private Stack<String> operatorStack;
    private Stack<Double> lastValueStack;
    private Stack<Stack<Double>> lastStateStack;

    public RpnCalculator() {
        twoElementOperationsAndInverses = new HashMap<String, String>();
        twoElementOperationsAndInverses.put("+", "-");
        twoElementOperationsAndInverses.put("-", "+");
        twoElementOperationsAndInverses.put("*", "/");
        twoElementOperationsAndInverses.put("/", "*");

        valueStack = new Stack<Double>();
        operatorStack = new Stack<String>();
        lastValueStack = new Stack<Double>();
        lastStateStack = new Stack<Stack<Double>>();
    }

    //TODO: Fix input with what will come from command prompt
    public Stack<Double> calcString(String inputString) throws Exception {

        String[] operators = inputString.trim().split(" ");

        for (String operator : operators) {
            processOperator(operator);
        }
        return valueStack;
    }

    private void processOperator(String currentOperator) throws Exception {
        if (currentOperator.matches(DecimalRegex))
            processNumber(currentOperator);
        else if (twoElementOperationsAndInverses.keySet().contains(currentOperator))
            processTwoElementOperator(currentOperator);
        else if(currentOperator.equals("sqrt"))
            processSqrt(currentOperator);
        else if (currentOperator.equals("clear"))
            processClear(currentOperator);
        else if (currentOperator.equals("undo"))
            processUndo(currentOperator);
        else
            throw new Exception("Invalid Operator: " + currentOperator);
    }

    private void processNumber(String currentOperator) {
        operatorStack.push(currentOperator);
        valueStack.push(Double.parseDouble(currentOperator));
    }

    private void processTwoElementOperator(String currentOperator) throws Exception {
        if (valueStack.size() >= 2) {
            operatorStack.push(currentOperator);
            double value1 = valueStack.pop();
            double value2 = valueStack.pop();
            double newValue = getTwoElementOperatorCalc(currentOperator, value1, value2);
            valueStack.push(newValue);
            lastValueStack.push(value1);
        } else
            throw new Exception("Not Enough Elements On Stack For Operation: " + currentOperator);
    }

    private void processSqrt(String currentOperator) throws Exception {
        if (valueStack.size() >= 1) {
            operatorStack.push(currentOperator);
            valueStack.push(Math.sqrt(valueStack.pop()));
        } else
            throw new Exception("Not Enough Elements On Stack For Operation: " + currentOperator);
    }

    private void processClear(String currentOperator) {
        operatorStack.push(currentOperator);
        lastStateStack.push((Stack<Double>) valueStack.clone());
        valueStack.clear();
    }

    private void processUndo(String currentOperator) throws Exception {
        if (operatorStack.size() >= 1)
            processUndoOperator();
        else
            throw new Exception("Not Enough Elements On Stack For Operation: " + currentOperator);
    }

    private void processUndoOperator() {
        String lastOperator = operatorStack.pop();
        if (lastOperator.matches("[0-9]+(\\.[0-9][0-9]?)?"))
            processUndoNumber();
        else if(twoElementOperationsAndInverses.keySet().contains(lastOperator)) {
            processUndoTwoElementOperation(lastOperator);
        } else if (lastOperator.equals("sqrt")) {
            processUndoSqrt();
        } else
            processUndoClear();
    }

    private void processUndoNumber() {
        valueStack.pop();
    }

    private void processUndoTwoElementOperation(String lastOperator) {
        double lastResult = valueStack.pop();
        double lastArgument = lastValueStack.pop();
        String inverseOperator = twoElementOperationsAndInverses.get(lastOperator);
        double missingArgument = getTwoElementOperatorCalc(inverseOperator, lastArgument, lastResult);
        valueStack.push(missingArgument);
        valueStack.push(lastArgument);
    }

    private void processUndoSqrt() {
        double lastResult = valueStack.pop();
        valueStack.push(lastResult * lastResult);
    }

    private void processUndoClear() {
        valueStack = lastStateStack.pop();
    }

    private double getTwoElementOperatorCalc(String currentOperator, double value1, double value2) {
        double newValue;
        if (currentOperator.equals("+"))
            newValue = value2 + value1;
        else if (currentOperator.equals("-"))
            newValue = value2 - value1;
        else if (currentOperator.equals("*"))
            newValue = value2 * value1;
        else
            newValue = value2 / value1;
        return newValue;
    }
}
