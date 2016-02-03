 package rlroman90.rpncalculator;

 import java.math.RoundingMode;
 import java.util.Scanner;

 public class Program {
    public static void main(String[] args) {
        int storingPrecision = 15;
        int roundingPrecision = 10;
        RoundingMode roundingMode = RoundingMode.HALF_UP;

        DoubleRounding doubleRounding = new DoubleRounding(storingPrecision, roundingPrecision, roundingMode);
        RpnCalculator rpnCalc = new RpnCalculator(doubleRounding);
        String inputGreeting = "Please Enter Input Below:";
        StringBuilder calcInput = new StringBuilder();

        printStartupMessage(inputGreeting);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("exit")) {
            calcInput.append(userInput.trim());
            StackOutput result = rpnCalc.calcString(calcInput.toString());

            printOutputMessages(result);
            calcInput.setLength(0);

            System.out.println(inputGreeting);
            userInput = scanner.nextLine();
        }
    }

     private static void printOutputMessages(StackOutput result) {
         if (!result.ExceptionMessage.isEmpty())
             System.out.println(result.ExceptionMessage);
         System.out.println("Stack: " + result.OutputString);
     }

     private static void printStartupMessage(String inputGreeting) {
         System.out.println("Welcome to the RPN Calculator");
         System.out.println("Please Enter a Line with Numbers or allowed operations each separated by a space.");
         System.out.println("Allowed Operations Include: +, -, /, *, sqrt, clear, undo.");
         System.out.println("The input should be follow the recommended format below.");
         System.out.println("Example: 5 2 3 - + undo");
         System.out.println("Enter 'exit' when finished.");
         System.out.println(inputGreeting);
     }
 }
