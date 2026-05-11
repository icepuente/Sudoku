package sudoku;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the Tools class.
 * Tests utility methods including board generation and manipulation.
 */
@RunWith(JUnit4.class)
public class ToolsTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    // Test getDocumentsPath()

    @Test
    public void testGetDocumentsPathNotNull() {
        String path = Tools.getDocumentsPath();
        assertNotNull(path);
    }

    @Test
    public void testGetDocumentsPathNotEmpty() {
        String path = Tools.getDocumentsPath();
        assertFalse(path.isEmpty());
    }

    @Test
    public void testGetDocumentsPathExists() {
        String path = Tools.getDocumentsPath();
        File documentsDir = new File(path);
        assertTrue("Documents path should exist", documentsDir.exists());
    }

    // Tests for private helper methods using reflection

    @Test
    public void testRandomWithinBounds() throws Exception {
        Method randomMethod = Tools.class.getDeclaredMethod("random", int.class, int.class);
        randomMethod.setAccessible(true);

        for (int i = 0; i < 100; i++) {
            int result = (int) randomMethod.invoke(null, 1, 9);
            assertTrue("Random value " + result + " should be between 1 and 9",
                result >= 1 && result <= 9);
        }
    }

    @Test
    public void testRandomSameValue() throws Exception {
        Method randomMethod = Tools.class.getDeclaredMethod("random", int.class, int.class);
        randomMethod.setAccessible(true);

        int result = (int) randomMethod.invoke(null, 5, 5);
        assertEquals("Random with same low and high should return that value", 5, result);
    }

    @Test
    public void testRandomDistribution() throws Exception {
        Method randomMethod = Tools.class.getDeclaredMethod("random", int.class, int.class);
        randomMethod.setAccessible(true);

        boolean[] seen = new boolean[10];
        for (int i = 0; i < 1000; i++) {
            int result = (int) randomMethod.invoke(null, 1, 9);
            seen[result] = true;
        }

        for (int i = 1; i <= 9; i++) {
            assertTrue("Value " + i + " should appear in random distribution", seen[i]);
        }
    }

    @Test
    public void testClone2DArray() throws Exception {
        Method cloneMethod = Tools.class.getDeclaredMethod("clone2DArray", int[][].class);
        cloneMethod.setAccessible(true);

        int[][] original = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] cloned = (int[][]) cloneMethod.invoke(null, (Object) original);

        // Verify contents are equal
        assertArrayEquals(original[0], cloned[0]);
        assertArrayEquals(original[1], cloned[1]);
        assertArrayEquals(original[2], cloned[2]);

        // Verify it's an independent copy
        cloned[0][0] = 999;
        assertEquals("Modifying clone should not affect original", 1, original[0][0]);
    }

    @Test
    public void testClone2DArrayEmpty() throws Exception {
        Method cloneMethod = Tools.class.getDeclaredMethod("clone2DArray", int[][].class);
        cloneMethod.setAccessible(true);

        int[][] original = {};
        int[][] cloned = (int[][]) cloneMethod.invoke(null, (Object) original);

        assertEquals(0, cloned.length);
    }

    @Test
    public void testTranspose() throws Exception {
        Method transposeMethod = Tools.class.getDeclaredMethod("transpose", int[][].class);
        transposeMethod.setAccessible(true);

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        transposeMethod.invoke(null, (Object) matrix);

        int[][] expected = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
        };

        assertArrayEquals(expected[0], matrix[0]);
        assertArrayEquals(expected[1], matrix[1]);
        assertArrayEquals(expected[2], matrix[2]);
    }

    @Test
    public void testTransposeTwice() throws Exception {
        Method transposeMethod = Tools.class.getDeclaredMethod("transpose", int[][].class);
        transposeMethod.setAccessible(true);

        int[][] original = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        transposeMethod.invoke(null, (Object) matrix);
        transposeMethod.invoke(null, (Object) matrix);

        assertArrayEquals(original[0], matrix[0]);
        assertArrayEquals(original[1], matrix[1]);
        assertArrayEquals(original[2], matrix[2]);
    }

    @Test
    public void testSwapRows() throws Exception {
        Method swapRowsMethod = Tools.class.getDeclaredMethod("swapRows", int[][].class, int.class, int.class);
        swapRowsMethod.setAccessible(true);

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        swapRowsMethod.invoke(null, matrix, 1, 3); // 1-indexed

        assertArrayEquals(new int[]{7, 8, 9}, matrix[0]);
        assertArrayEquals(new int[]{4, 5, 6}, matrix[1]);
        assertArrayEquals(new int[]{1, 2, 3}, matrix[2]);
    }

    @Test
    public void testSwapRowsSameRow() throws Exception {
        Method swapRowsMethod = Tools.class.getDeclaredMethod("swapRows", int[][].class, int.class, int.class);
        swapRowsMethod.setAccessible(true);

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        swapRowsMethod.invoke(null, matrix, 2, 2); // 1-indexed

        assertArrayEquals(new int[]{1, 2, 3}, matrix[0]);
        assertArrayEquals(new int[]{4, 5, 6}, matrix[1]);
        assertArrayEquals(new int[]{7, 8, 9}, matrix[2]);
    }

    @Test
    public void testContainsFound() throws Exception {
        Method containsMethod = Tools.class.getDeclaredMethod("contains", Integer[].class, ArrayList.class);
        containsMethod.setAccessible(true);

        ArrayList<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2});
        list.add(new Integer[]{3, 4});
        list.add(new Integer[]{5, 6});

        Integer[] toFind = {3, 4};
        boolean result = (boolean) containsMethod.invoke(null, toFind, list);

        assertTrue("Should find existing coordinate pair", result);
    }

    @Test
    public void testContainsNotFound() throws Exception {
        Method containsMethod = Tools.class.getDeclaredMethod("contains", Integer[].class, ArrayList.class);
        containsMethod.setAccessible(true);

        ArrayList<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2});
        list.add(new Integer[]{3, 4});

        Integer[] toFind = {5, 6};
        boolean result = (boolean) containsMethod.invoke(null, toFind, list);

        assertFalse("Should not find non-existing coordinate pair", result);
    }

    @Test
    public void testContainsEmptyList() throws Exception {
        Method containsMethod = Tools.class.getDeclaredMethod("contains", Integer[].class, ArrayList.class);
        containsMethod.setAccessible(true);

        ArrayList<Integer[]> list = new ArrayList<>();
        Integer[] toFind = {1, 2};
        boolean result = (boolean) containsMethod.invoke(null, toFind, list);

        assertFalse("Should return false for empty list", result);
    }

    // Tests for predefined boards

    @Test
    public void testBoard1Valid() throws Exception {
        Field board1Field = Tools.class.getDeclaredField("BOARD1");
        board1Field.setAccessible(true);
        int[][] board = (int[][]) board1Field.get(null);

        assertTrue("BOARD1 should be a valid Sudoku solution", isValidSudoku9x9(board));
    }

    @Test
    public void testBoard2Valid() throws Exception {
        Field board2Field = Tools.class.getDeclaredField("BOARD2");
        board2Field.setAccessible(true);
        int[][] board = (int[][]) board2Field.get(null);

        assertTrue("BOARD2 should be a valid Sudoku solution", isValidSudoku9x9(board));
    }

    @Test
    public void testBoard3Valid() throws Exception {
        Field board3Field = Tools.class.getDeclaredField("BOARD3");
        board3Field.setAccessible(true);
        int[][] board = (int[][]) board3Field.get(null);

        assertTrue("BOARD3 should be a valid Sudoku solution", isValidSudoku9x9(board));
    }

    @Test
    public void testBoard4Valid() throws Exception {
        Field board4Field = Tools.class.getDeclaredField("BOARD4");
        board4Field.setAccessible(true);
        int[][] board = (int[][]) board4Field.get(null);

        assertTrue("BOARD4 should be a valid Sudoku solution", isValidSudoku16x16(board));
    }

    // Helper method to validate 9x9 Sudoku solution
    private boolean isValidSudoku9x9(int[][] board) {
        // Check dimensions
        if (board.length != 9) return false;
        for (int[] row : board) {
            if (row.length != 9) return false;
        }

        // Check rows
        for (int i = 0; i < 9; i++) {
            if (!hasAllNumbers(board[i], 1, 9)) return false;
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            int[] col = new int[9];
            for (int i = 0; i < 9; i++) {
                col[i] = board[i][j];
            }
            if (!hasAllNumbers(col, 1, 9)) return false;
        }

        // Check 3x3 boxes
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                int[] box = new int[9];
                int idx = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        box[idx++] = board[boxRow * 3 + i][boxCol * 3 + j];
                    }
                }
                if (!hasAllNumbers(box, 1, 9)) return false;
            }
        }

        return true;
    }

    // Helper method to validate 16x16 Sudoku solution
    private boolean isValidSudoku16x16(int[][] board) {
        // Check dimensions
        if (board.length != 16) return false;
        for (int[] row : board) {
            if (row.length != 16) return false;
        }

        // Check rows
        for (int i = 0; i < 16; i++) {
            if (!hasAllNumbers(board[i], 1, 16)) return false;
        }

        // Check columns
        for (int j = 0; j < 16; j++) {
            int[] col = new int[16];
            for (int i = 0; i < 16; i++) {
                col[i] = board[i][j];
            }
            if (!hasAllNumbers(col, 1, 16)) return false;
        }

        // Check 4x4 boxes
        for (int boxRow = 0; boxRow < 4; boxRow++) {
            for (int boxCol = 0; boxCol < 4; boxCol++) {
                int[] box = new int[16];
                int idx = 0;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        box[idx++] = board[boxRow * 4 + i][boxCol * 4 + j];
                    }
                }
                if (!hasAllNumbers(box, 1, 16)) return false;
            }
        }

        return true;
    }

    // Helper to check if array contains all numbers from min to max
    private boolean hasAllNumbers(int[] arr, int min, int max) {
        boolean[] found = new boolean[max - min + 1];
        for (int num : arr) {
            if (num < min || num > max) return false;
            found[num - min] = true;
        }
        for (boolean b : found) {
            if (!b) return false;
        }
        return true;
    }
}
