// AUthor: Jhanavi Dave

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        // implement scanner to take string input from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string in a single line for addition:");
        // when next line (enter) is pressed, complete the input
        String userInput = scanner.nextLine();

        // execute the user given string to add the numbers - condition stated in add function
        try {
            System.out.println("The sum is: " + add(userInput));
        } catch (IllegalArgumentException e) {
            // catch wrongly formatted string
            System.out.println(e.getMessage());
        }
        // close scanner to avoid taking more inputs
        scanner.close();
    }

    public static int add(String numString) {

        // initialize sum variable, and created array list of not allowed numbers, i.e. negative numbers
        int sum = 0;
        ArrayList notAllowed = new ArrayList();

        // if given string is empty, the total sum is 0, return 0
        if (numString.isEmpty()) {
            return 0;
        }

        // if string starts with '//'
        if (numString.startsWith("//")) {
            // check if string has '\n' character
            int newlineIndex = numString.indexOf("\n");
            if (newlineIndex == -1) {
                // throw wrong format error if not found
                throw new IllegalArgumentException("Invalid input format: Missing newline character after custom delimiter");
            }
            // and has one or two delimiter(s) following, till it reaches '\n'
            String delimiterPart = numString.substring(2, numString.indexOf("\n"));
            // replace all '\\[' and '\\]' by '|' to create a regex possible of splitting
            String regex = delimiterPart.replaceAll("\\[", "|").replaceAll("\\]", "|");
            // replace all '[]'' by '|' to create a regex possible of splitting, in case of multiple delimiters
            if (delimiterPart.contains("[")) {
                regex = delimiterPart.substring(1, delimiterPart.length() - 1).replace("][", "|");
            }
            // locate index of '\n' to see if input starts at new line
            numString = numString.substring(numString.indexOf("\n") + 1);
        }

        // split string by delimiters and characters in between, and store integers into new array numStr
        int[] numStr = Arrays.stream(numString.split("[,;\n*% ]+")).mapToInt(Integer::parseInt).toArray();

        // for each number in numStr, check if it is greater or less than 0 and 1000, since they become exceptions
        for (int i = 0; i < numStr.length; i++) {
            // value of calculation should be within 0 and 1000, latter inclusive
            if (numStr[i] > 0 && numStr[i] <= 1000) {
                // add number to the total sum of all the numbers taken so far, using index i
                sum = sum + numStr[i];
            } // if number is less than 0, add to notAllowed array to be thrown as exception
            else if (numStr[i] < 0) {
                notAllowed.add(numStr[i]);
            }
        }

        // print the total sum of the integers from numStr
        System.out.println(sum);
        // if negative numbers exist, print list
        if (notAllowed.isEmpty() == false) {
            throw new IllegalArgumentException("negative numbers not allowed < " + notAllowed + " >");
            // System.out.println("negative numbers not allowed < " + notAllowed + " >");
        }

        // return the total sum of integers from numStr
        return sum;
    }
}
