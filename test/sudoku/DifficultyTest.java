package sudoku;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Unit tests for the Difficulty enum.
 */
@RunWith(JUnit4.class)
public class DifficultyTest {

    @Test
    public void testDifficultyTest() {
        assertEquals("Test", Difficulty.TEST.toString());
        assertEquals(34, Difficulty.TEST.getNumberOfCellsToDisplay());
    }

    @Test
    public void testDifficultyEasy() {
        assertEquals("Easy", Difficulty.EASY.toString());
        assertEquals(50, Difficulty.EASY.getNumberOfCellsToDisplay());
    }

    @Test
    public void testDifficultyMedium() {
        assertEquals("Medium", Difficulty.MEDIUM.toString());
        assertEquals(45, Difficulty.MEDIUM.getNumberOfCellsToDisplay());
    }

    @Test
    public void testDifficultyHard() {
        assertEquals("Hard", Difficulty.HARD.toString());
        assertEquals(40, Difficulty.HARD.getNumberOfCellsToDisplay());
    }

    @Test
    public void testDifficultyDevilish() {
        assertEquals("Devilish", Difficulty.DEVILISH.toString());
        assertEquals(130, Difficulty.DEVILISH.getNumberOfCellsToDisplay());
    }

    @Test
    public void testDifficultyCount() {
        assertEquals(5, Difficulty.values().length);
    }

    @Test
    public void testAllDifficultiesHavePositiveCells() {
        for (Difficulty difficulty : Difficulty.values()) {
            assertTrue("Difficulty " + difficulty + " should have positive cells to display",
                difficulty.getNumberOfCellsToDisplay() > 0);
        }
    }

    @Test
    public void testAllDifficultiesHaveNonNullToString() {
        for (Difficulty difficulty : Difficulty.values()) {
            assertNotNull(difficulty.toString());
            assertFalse(difficulty.toString().isEmpty());
        }
    }

    @Test
    public void testStandardDifficultiesWithinBounds() {
        assertTrue(Difficulty.TEST.getNumberOfCellsToDisplay() <= 81);
        assertTrue(Difficulty.EASY.getNumberOfCellsToDisplay() <= 81);
        assertTrue(Difficulty.MEDIUM.getNumberOfCellsToDisplay() <= 81);
        assertTrue(Difficulty.HARD.getNumberOfCellsToDisplay() <= 81);
    }

    @Test
    public void testDevilishWithinBounds() {
        assertTrue(Difficulty.DEVILISH.getNumberOfCellsToDisplay() <= 256);
    }

    @Test
    public void testDifficultyOrdering() {
        assertTrue("HARD should have fewer visible cells than MEDIUM",
            Difficulty.HARD.getNumberOfCellsToDisplay() <
            Difficulty.MEDIUM.getNumberOfCellsToDisplay());
        assertTrue("MEDIUM should have fewer visible cells than EASY",
            Difficulty.MEDIUM.getNumberOfCellsToDisplay() <
            Difficulty.EASY.getNumberOfCellsToDisplay());
    }

    @Test
    public void testValueOf() {
        assertEquals(Difficulty.EASY, Difficulty.valueOf("EASY"));
        assertEquals(Difficulty.MEDIUM, Difficulty.valueOf("MEDIUM"));
        assertEquals(Difficulty.HARD, Difficulty.valueOf("HARD"));
        assertEquals(Difficulty.DEVILISH, Difficulty.valueOf("DEVILISH"));
        assertEquals(Difficulty.TEST, Difficulty.valueOf("TEST"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfInvalid() {
        Difficulty.valueOf("INVALID");
    }
}
