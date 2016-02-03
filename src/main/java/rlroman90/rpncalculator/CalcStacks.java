package rlroman90.rpncalculator;

import java.util.Stack;

public class CalcStacks {
    public Stack<Double> ValueStack;
    public Stack<String> OperatorStack;
    public Stack<Double> LastValueStack;
    public Stack<Stack<Double>> LastStateStack;

    public CalcStacks() {
        ValueStack = new Stack<Double>();
        OperatorStack = new Stack<String>();
        LastValueStack = new Stack<Double>();
        LastStateStack = new Stack<Stack<Double>>();
    }
}
