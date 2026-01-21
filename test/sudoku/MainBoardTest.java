package sudoku;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Unit tests for the MainBoard class.
 * Focuses on static utility methods that don't require GUI initialization.
 */
@RunWith(JUnit4.class)
public class MainBoardTest {

    // Tests for isInteger(String s)

    @Test
    public void testIsIntegerStringWithSingleDigits() {
        assertTrue(MainBoard.isInteger("0"));
        assertTrue(MainBoard.isInteger("1"));
        assertTrue(MainBoard.isInteger("5"));
        assertTrue(MainBoard.isInteger("9"));
    }

    @Test
    public void testIsIntegerStringWithMultiDigitNumbers() {
        assertTrue(MainBoard.isInteger("10"));
        assertTrue(MainBoard.isInteger("42"));
        assertTrue(MainBoard.isInteger("123"));
        assertTrue(MainBoard.isInteger("9999"));
    }

    @Test
    public void testIsIntegerStringWithNegativeNumbers() {
        assertTrue(MainBoard.isInteger("-1"));
        assertTrue(MainBoard.isInteger("-42"));
        assertTrue(MainBoard.isInteger("-999"));
    }

    @Test
    public void testIsIntegerStringWithNonNumeric() {
        assertFalse(MainBoard.isInteger("a"));
        assertFalse(MainBoard.isInteger("abc"));
        assertFalse(MainBoard.isInteger("hello"));
        assertFalse(MainBoard.isInteger("!@#"));
    }

    @Test
    public void testIsIntegerStringWithEmptyString() {
        assertFalse(MainBoard.isInteger(""));
    }

    @Test
    public void testIsIntegerStringWithDecimalNumbers() {
        assertFalse(MainBoard.isInteger("1.5"));
        assertFalse(MainBoard.isInteger("3.14"));
        assertFalse(MainBoard.isInteger("0.1"));
    }

    @Test
    public void testIsIntegerStringWithMixedAlphanumeric() {
        assertFalse(MainBoard.isInteger("12a"));
        assertFalse(MainBoard.isInteger("a12"));
        assertFalse(MainBoard.isInteger("1a2"));
    }

    @Test
    public void testIsIntegerStringWithSpaces() {
        assertFalse(MainBoard.isInteger(" 1"));
        assertFalse(MainBoard.isInteger("1 "));
        assertFalse(MainBoard.isInteger(" 1 "));
        assertFalse(MainBoard.isInteger("1 2"));
    }

    @Test
    public void testIsIntegerStringAllSingleDigits() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String digit : digits) {
            assertTrue("Digit " + digit + " should be an integer", MainBoard.isInteger(digit));
        }
    }

    // Tests for isInteger(char c)

    @Test
    public void testIsIntegerCharWithDigits() {
        assertTrue(MainBoard.isInteger('0'));
        assertTrue(MainBoard.isInteger('1'));
        assertTrue(MainBoard.isInteger('5'));
        assertTrue(MainBoard.isInteger('9'));
    }

    @Test
    public void testIsIntegerCharWithLetters() {
        assertFalse(MainBoard.isInteger('a'));
        assertFalse(MainBoard.isInteger('z'));
        assertFalse(MainBoard.isInteger('A'));
        assertFalse(MainBoard.isInteger('Z'));
    }

    @Test
    public void testIsIntegerCharWithSpecialChars() {
        assertFalse(MainBoard.isInteger('!'));
        assertFalse(MainBoard.isInteger('@'));
        assertFalse(MainBoard.isInteger('#'));
        assertFalse(MainBoard.isInteger('.'));
        assertFalse(MainBoard.isInteger('-'));
        assertFalse(MainBoard.isInteger(' '));
    }

    @Test
    public void testIsIntegerCharAllDigits() {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char digit : digits) {
            assertTrue("Char " + digit + " should be an integer", MainBoard.isInteger(digit));
        }
    }

    @Test
    public void testIsIntegerCharDevilishLetters() {
        // Letters used for Devilish mode (q, w, e, r, t, y, u) should not be integers
        assertFalse(MainBoard.isInteger('q'));
        assertFalse(MainBoard.isInteger('w'));
        assertFalse(MainBoard.isInteger('e'));
        assertFalse(MainBoard.isInteger('r'));
        assertFalse(MainBoard.isInteger('t'));
        assertFalse(MainBoard.isInteger('y'));
        assertFalse(MainBoard.isInteger('u'));
    }

    // Edge cases

    @Test
    public void testIsIntegerStringMaxValue() {
        assertTrue(MainBoard.isInteger(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void testIsIntegerStringMinValue() {
        assertTrue(MainBoard.isInteger(String.valueOf(Integer.MIN_VALUE)));
    }

    @Test
    public void testIsIntegerStringOverflow() {
        // Value larger than Integer.MAX_VALUE
        assertFalse(MainBoard.isInteger("99999999999999999999"));
    }

    @Test
    public void testIsIntegerStringLeadingZeros() {
        assertTrue(MainBoard.isInteger("007"));
        assertTrue(MainBoard.isInteger("00"));
        assertTrue(MainBoard.isInteger("01"));
    }

    // Sudoku-specific value tests

    @Test
    public void testValidSudokuValues9x9() {
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String value : values) {
            assertTrue("Value " + value + " should be valid for 9x9 Sudoku",
                MainBoard.isInteger(value));
        }
    }

    @Test
    public void testValidSudokuValues16x16() {
        String[] values = {"10", "11", "12", "13", "14", "15", "16"};
        for (String value : values) {
            assertTrue("Value " + value + " should be valid for 16x16 Sudoku",
                MainBoard.isInteger(value));
        }
    }
}
