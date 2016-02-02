package rlroman90.rpncalculator;

import java.util.ArrayList;
import java.util.Stack;

public class Program {
    public static void main(String[] args) throws Exception {
        //String testString = "5 2";
        //String testString = "5 2 undo";
        //String testString = "1 2 3 5 +";
        //String testString = "1 2 3 5 + + undo undo";
        //String testString = "5 2 -";
        //String testString = "5 2 - undo";
        //String testString = "5 4 *";
        //String testString = "5 4 * undo";
        //String testString = "7 12 2 /";
        //String testString = "7 12 2 / undo";
        //String testString = "2 sqrt";
        //String testString = "2 sqrt undo";
        //String testString = "1 2 3 clear 5";
        //String testString = "1 2 3 clear undo";
        //String testString = "2 sqrt clear 9 sqrt";
        //String testString = "5 2 - 3 - clear";
        //String testString = "5 4 3 2 undo undo * 5 * undo";
        //String testString = "7 12 2 / * 4 /";
        //String testString = "1 2 3 4 5 * clear 3 4 -";
        String testString = "1 2 3 4 5 * * * *";

        RpnCalculator rpnCalc = new RpnCalculator();
        Stack<Double> valueStack = rpnCalc.calcString(testString);

        ArrayList<Double> outputValues = new ArrayList<Double>();
        while(!valueStack.empty())
            outputValues.add(0, valueStack.pop());

        StringBuilder outputString = new StringBuilder();
        for(double value : outputValues)
            outputString.append(value).append(" ");
        if(outputString.length() > 0)
            outputString.deleteCharAt(outputString.length() - 1);
        System.out.println(outputString);
    }
}
