package rlroman90.rpncalculator;

import java.util.ArrayList;
import java.util.Stack;

public class RpnCalculator {
    private static String DecimalRegex = "[0-9]+(\\.[0-9][0-9]?)?";

    private Operators availableOperators;
    private CalcStacks stacks;

    public RpnCalculator() {
        stacks = new CalcStacks();
        availableOperators = new Operators();
    }

    public StackOutput calcString(String inputString) {

        StackOutput result = new StackOutput();
        String[] operators = inputString.trim().split(" ");
        int currentOperator = 0;
        try {
            verifyNotEmptyString(inputString);
            while (currentOperator < operators.length) {
                processOperator(operators[currentOperator]);
                currentOperator++;
            }
        } catch (Exception ex) {
            int position = getOperatorPositionInString(operators, currentOperator);
            result.ExceptionMessage = String.format(ex.getMessage(), position);
            result.OutputString = convertValueStackToString();
        }
        result.OutputString = convertValueStackToString();
        return result;
    }

    private void verifyNotEmptyString(String inputOperators) throws Exception {
        if(inputOperators.equals(""))
            throw new Exception("Input String was empty.");
    }

    private void processOperator(String operator) throws Exception {
        if (isValidOperator(operator))
            determineOperator(operator);
        else
            throw new Exception("operator " + operator + " (position: %d): invalid operator");
    }

    private int getOperatorPositionInString(String[] operators, int operatorNumber) {
        int stringPosition = 0;
        for(int i = 0; i < operatorNumber; i++)
            stringPosition += operators[i].length() + 1;
        return stringPosition;
    }

    private String convertValueStackToString() {
        ArrayList<Double> outputValues = getValueList();
        return buildOutputString(outputValues);
    }

    private ArrayList<Double> getValueList() {
        ArrayList<Double> outputValues = new ArrayList<Double>();
        Stack<Double> valueStackCopy = (Stack<Double>) stacks.ValueStack.clone();
        while(!valueStackCopy.empty())
            outputValues.add(0, valueStackCopy.pop());
        return outputValues;
    }

    private String buildOutputString(ArrayList<Double> outputValues) {
        StringBuilder outputString = new StringBuilder();
        for(double value : outputValues)
            outputString.append(value).append(" ");
        if(outputString.length() > 0)
            outputString.deleteCharAt(outputString.length() - 1);
        return outputString.toString();
    }

    private boolean isValidOperator(String operator) {
        boolean result;
        if(availableOperators.ValidOperators.contains(operator) || operator.matches(DecimalRegex))
            result = true;
        else
            result = false;
        return result;
    }

    private void determineOperator(String currentOperator) throws Exception {
        if (currentOperator.matches(DecimalRegex))
            processNumber(currentOperator);
        else if (availableOperators.TwoElementOperationsAndInverses.keySet().contains(currentOperator))
            processTwoElementOperator(currentOperator);
        else if (currentOperator.equals("sqrt"))
            processSqrt(currentOperator);
        else if (currentOperator.equals("clear"))
            processClear(currentOperator);
        else if (currentOperator.equals("undo"))
            processUndo(currentOperator);
    }

    private void processNumber(String currentOperator) {
        stacks.OperatorStack.push(currentOperator);
        stacks.ValueStack.push(Double.parseDouble(currentOperator));
    }

    private void processTwoElementOperator(String currentOperator) throws Exception {
        if (stacks.ValueStack.size() >= 2) {
            stacks.OperatorStack.push(currentOperator);
            double value1 = stacks.ValueStack.pop();
            double value2 = stacks.ValueStack.pop();
            double newValue = getTwoElementOperatorCalc(currentOperator, value1, value2);
            stacks.ValueStack.push(newValue);
            stacks.LastValueStack.push(value1);
        } else
            throw new Exception("operator " + currentOperator + " (position: %d): insufficient parameters");
    }

    private void processSqrt(String currentOperator) throws Exception {
        if (stacks.ValueStack.size() >= 1) {
            stacks.OperatorStack.push(currentOperator);
            stacks.ValueStack.push(Math.sqrt(stacks.ValueStack.pop()));
        } else
            throw new Exception("operator " + currentOperator + " (position: %d): insufficient parameters");
    }

    private void processClear(String currentOperator) {
        stacks.OperatorStack.push(currentOperator);
        stacks.LastStateStack.push((Stack<Double>) stacks.ValueStack.clone());
        stacks.ValueStack.clear();
    }

    private void processUndo(String currentOperator) throws Exception {
        if (stacks.OperatorStack.size() >= 1)
            processUndoOperator();
        else {
            throw new Exception("operator " + currentOperator + " (position: %d): insufficient parameters");
        }
    }

    private void processUndoOperator() {
        String lastOperator = stacks.OperatorStack.pop();
        if (lastOperator.matches("[0-9]+(\\.[0-9][0-9]?)?"))
            processUndoNumber();
        else if(availableOperators.TwoElementOperationsAndInverses.keySet().contains(lastOperator))
            processUndoTwoElementOperation(lastOperator);
        else if (lastOperator.equals("sqrt"))
            processUndoSqrt();
        else
            processUndoClear();
    }

    private void processUndoNumber() {
        stacks.ValueStack.pop();
    }

    private void processUndoTwoElementOperation(String lastOperator) {
        double lastResult = stacks.ValueStack.pop();
        double lastArgument = stacks.LastValueStack.pop();
        String inverseOperator = availableOperators.TwoElementOperationsAndInverses.get(lastOperator);
        double missingArgument = getTwoElementOperatorCalc(inverseOperator, lastArgument, lastResult);
        stacks.ValueStack.push(missingArgument);
        stacks.ValueStack.push(lastArgument);
    }

    private void processUndoSqrt() {
        double lastResult = stacks.ValueStack.pop();
        stacks.ValueStack.push(lastResult * lastResult);
    }

    private void processUndoClear() {
        stacks.ValueStack = stacks.LastStateStack.pop();
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
