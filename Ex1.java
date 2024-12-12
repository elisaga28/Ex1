/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     *
     */
    public static int number2Int(String s) {
        if (s == null || !isNumber(s)) return -1;

        String[] parts = s.split("b");
        if (parts.length != 2) return -1;

        String numberPart = parts[0];
        int base;
        try {
            base = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return -1; // Invalid base
        }

        if (base < 2 || base > 16) return -1;

        try {
            return Integer.parseInt(numberPart, base);
        } catch (NumberFormatException e) {
            return -1; // Invalid number for the base
        }
    }

    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     *
     * @param s a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String s) {
        if (s == null || !s.contains("b") || s.startsWith("b") || s.endsWith("b")) return false;

        String[] parts = s.split("b");
        if (parts.length != 2) return false;

        String numberPart = parts[0];
        String basePart = parts[1];

        // Validate the base part
        if (basePart.equals("A") || basePart.equals("G") || basePart.matches("[2-9]|1[0-6]")) {
            try {
                int base;
                if (basePart.equals("A")) {
                    base = 10;
                } else if (basePart.equals("G")) {
                    base = 16;
                } else {
                    base = Integer.parseInt(basePart);
                }
                // Check if the number part is valid for the given base
                for (char c : numberPart.toCharArray()) {
                    int value = Character.digit(c, base);
                    if (value == -1) return false; // Invalid character for the base
                }
                return true;
            } catch (NumberFormatException e) {
                return false; // Invalid base
            }
        }
        return false;
    }



    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     *
     * @param num  the natural number (include 0).
     * @param base the basis [2,16]
     *
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    public static String int2Number(int num, int base) {
        String ans= "";
        if(num < 0 || base < 2 || base > 16) { //if input valid
            return ans;
        }
        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int remainder = num % base ;
            ans = int2Digit(remainder) + ans ;
            num /= base;
        }
        return ans;
    }
    /**
     * Helper function to convert an integer digit to its corresponding character in the given base.
     *
     * @param digit the digit to convert (0 <= digit <= 15).
     * @return a character representation of the digit.
     */
    private static char int2Digit(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit); // For digits 0-9
        } else {
            return (char) ('A' + digit - 10); // For digits 10-15
        }
    }
    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     *
     * @param num  the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */





    /**
     * Checks if the two numbers have the same value.
     *
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        return number2Int(n1) == number2Int(n2);
    }


    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
        // If the array is null or empty, return -1 as there are no valid numbers
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int maxIndex = -1; // Holds the index of the maximum value
        int maxValue = Integer.MIN_VALUE; // Holds the maximum value found, initialized to the smallest possible integer

        // Iterate through the array to find the maximum value
        for (int i = 0; i < arr.length; i++) {
            // Convert the current string to an integer using number2Int
            int value = number2Int(arr[i]);

            // Only consider valid numbers (i.e., value != -1)
            if (value != -1 && value > maxValue) {
                maxValue = value; // Update maxValue with the current valid value
                maxIndex = i; // Update maxIndex with the current index of the valid value
            }
        }

        // If no valid numbers were found, maxIndex will remain -1
        return maxIndex; // Return the index of the maximum value or -1 if no valid values were found
    }
}