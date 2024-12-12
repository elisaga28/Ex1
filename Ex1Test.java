import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {

    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2);
        assertEquals(11, v);
        String s10 = "1011bA";
        v = Ex1.number2Int(s10);
        s2 = Ex1.int2Number(v, 2);
        int v2 = Ex1.number2Int(s2);
        assertEquals(v, v2);
        assertTrue(Ex1.equals(s10, s2));
    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for (String s : good) {
            assertTrue(Ex1.isNumber(s), "Expected valid number: " + s);
        }
        String[] notGood = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for (String s : notGood) {
            assertFalse(Ex1.isNumber(s), "Expected invalid number: " + s);
        }
    }

    @Test
    void testNumber2Int() {
        assertEquals(11, Ex1.number2Int("1011b2")); // Binary to decimal
        assertEquals(255, Ex1.number2Int("FFb16")); // Hexadecimal to decimal
        assertEquals(-1, Ex1.number2Int("123b")); // Invalid format
        assertEquals(-1, Ex1.number2Int("Gb16")); // Invalid number for base
        assertEquals(-1, Ex1.number2Int("123b17")); // Base out of range
        assertEquals(-1, Ex1.number2Int("")); // Empty string
        assertEquals(-1, Ex1.number2Int(null)); // Null input
    }

    @Test
    void testIsNumber() {
        assertTrue(Ex1.isNumber("1011b2")); // Valid binary number
        assertTrue(Ex1.isNumber("FFb16")); // Valid hexadecimal number
        assertFalse(Ex1.isNumber("123b")); // Missing base
        assertFalse(Ex1.isNumber("123b1")); // Base less than 2
        assertFalse(Ex1.isNumber("123b17")); // Base greater than 16
        assertFalse(Ex1.isNumber("")); // Empty string
        assertFalse(Ex1.isNumber(null)); // Null input
        assertFalse(Ex1.isNumber("GbG")); // Invalid characters
    }

    @Test
    void testInt2Number() {
        assertEquals("1011", Ex1.int2Number(11, 2)); // Decimal to binary
        assertEquals("FF", Ex1.int2Number(255, 16)); // Decimal to hexadecimal
        assertEquals("12345", Ex1.int2Number(12345, 10)); // Decimal to decimal
        assertEquals("", Ex1.int2Number(-1, 10)); // Negative number
        assertEquals("", Ex1.int2Number(255, 17)); // Base out of range
        assertEquals("", Ex1.int2Number(255, 1)); // Base less than 2
        assertEquals("0", Ex1.int2Number(0, 10)); // Zero case
    }

    @Test
    void testEquals() {
        assertTrue(Ex1.equals("1011b2", "11b10")); // Same value
        assertTrue(Ex1.equals("FFb16", "255b10")); // Same value
        assertFalse(Ex1.equals("1011b2", "12b10")); // Different values
        assertFalse(Ex1.equals("1011b2", null)); // One invalid
        assertFalse(Ex1.equals("123b10", "123b17")); // One invalid
    }


    @Test
    void testMaxIndex() {
        // Array with valid numbers only
        String[] validArray = {"15b10", "F1b16", "100b2"};
        assertEquals(1, Ex1.maxIndex(validArray)); // 241 is the largest value

        // Array with mixed valid and invalid numbers
        String[] mixedArray = {"15b10", "invalid", "F1b16", null};
        assertEquals(2, Ex1.maxIndex(mixedArray)); // 241 is the largest value

        // Array with invalid numbers only
        String[] invalidArray = {"invalid", null, ""};
        assertEquals(-1, Ex1.maxIndex(invalidArray)); // No valid numbers, return -1

        // Array with duplicate maximum values
        String[] duplicateArray = {"100b10", "64b16", "100b10"};
        assertEquals(0, Ex1.maxIndex(duplicateArray)); // Return first occurrence of max value

        // Empty array
        String[] emptyArray = {};
        assertEquals(-1, Ex1.maxIndex(emptyArray)); // No elements, return -1
    }
}

