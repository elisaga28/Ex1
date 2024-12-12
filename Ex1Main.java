import java.util.Scanner;

/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 */

public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner object to take input from the user
        String num1 = "", num2 = "", quit = "quit"; // Initialize num1, num2, and quit strings

        // Loop that continues asking for input until the user types "quit"
        while (!num1.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");

            // Prompt the user to input the first number (num1)
            System.out.print("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (num1.equals(quit)) break; // Exit the loop if the user types "quit"

            // Check if num1 is a valid number using the isNumber method from the Ex1 class
            boolean isValidNum1 = Ex1.isNumber(num1);
            System.out.println("num1= " + num1 + " is number: " + isValidNum1 + " , value: " + (isValidNum1 ? Ex1.number2Int(num1) : "N/A"));

            // Prompt the user to input the second number (num2)
            System.out.print("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next();
            if (num2.equals(quit)) break; // Exit the loop if the user types "quit"

            // Check if num2 is a valid number using the isNumber method from the Ex1 class
            boolean isValidNum2 = Ex1.isNumber(num2);
            System.out.println("num2= " + num2 + " is number: " + isValidNum2 + " , value: " + (isValidNum2 ? Ex1.number2Int(num2) : "N/A"));

            // Prompt the user to input the base for the output (between 2 and 16)
            System.out.print("Enter a base for output: (a number [2,16]): ");
            int base;
            try {
                base = sc.nextInt(); // Read the base as an integer
                if (base < 2 || base > 16) {
                    System.out.println("ERR: Base must be between 2 and 16!"); // If the base is out of range, show an error
                    continue; // Restart the loop and ask again for valid input
                }
            } catch (Exception e) {
                System.out.println("ERR: Invalid base!"); // Handle invalid base input
                sc.nextLine(); // Clear the buffer
                continue; // Restart the loop
            }

            // Convert num1 and num2 to integers using the number2Int method from Ex1 class
            int value1 = Ex1.number2Int(num1);
            int value2 = Ex1.number2Int(num2);

            // Calculate the sum and product of the two numbers and convert them to the desired base
            String sum = Ex1.int2Number(value1 + value2, base);
            String product = Ex1.int2Number(value1 * value2, base);

            // Display the sum and product of the numbers in the specified base
            System.out.println(num1 + " + " + num2 + " = " + sum);
            System.out.println(num1 + " * " + num2 + " = " + product);

            // Create an array containing num1, num2, sum, and product for finding the max number
            String[] numbers = {num1, num2, sum, product};
            int maxIdx = Ex1.maxIndex(numbers); // Find the index of the maximum number in the array
            System.out.println("Max number over " + String.join(",", numbers) + " is: " + numbers[maxIdx]); // Display the maximum number
        }
        System.out.println("quiting now..."); // Print a message when the program ends
        sc.close(); // Close the scanner
    }
}
