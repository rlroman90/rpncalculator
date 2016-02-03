 package rlroman90.rpncalculator;

public class Program {
    public static void main(String[] args) {
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
        StackOutput result = rpnCalc.calcString(testString);

        if(!result.ExceptionMessage.isEmpty())
            System.out.println(result.ExceptionMessage);
        System.out.println("Stack: " + result.OutputString);
    }
}
