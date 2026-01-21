package sudoku;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Unit tests for the Save class.
 * Tests game persistence functionality.
 */
@RunWith(JUnit4.class)
public class SaveTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private Save testSave;
    private File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = tempFolder.newFile("testSave.dat");
        testSave = new Save("TestUser", testFile, Difficulty.EASY);
    }

    // Constructor and basic accessor tests

    @Test
    public void testConstructorName() {
        assertEquals("TestUser", testSave.getName());
    }

    @Test
    public void testConstructorSaveFile() {
        assertEquals(testFile, testSave.getSaveFile());
    }

    @Test
    public void testConstructorDifficulty() {
        assertEquals(Difficulty.EASY, testSave.getDifficulty());
    }

    @Test
    public void testConstructorNullDifficulty() {
        // Should not throw
        new Save("User", testFile, null);
    }

    // Setter tests

    @Test
    public void testSetName() {
        testSave.setName("NewUser");
        assertEquals("NewUser", testSave.getName());
    }

    @Test
    public void testSetNameNull() {
        testSave.setName(null);
        assertNull(testSave.getName());
    }

    @Test
    public void testSetNameEmpty() {
        testSave.setName("");
        assertEquals("", testSave.getName());
    }

    @Test
    public void testSetSaveFile() throws Exception {
        File newFile = tempFolder.newFile("newSave.dat");
        testSave.setSaveFile(newFile);
        assertEquals(newFile, testSave.getSaveFile());
    }

    @Test
    public void testSetDifficulty() {
        testSave.setDifficulty(Difficulty.HARD);
        assertEquals(Difficulty.HARD, testSave.getDifficulty());
    }

    @Test
    public void testSetDifficultyAllLevels() {
        for (Difficulty difficulty : Difficulty.values()) {
            testSave.setDifficulty(difficulty);
            assertEquals(difficulty, testSave.getDifficulty());
        }
    }

    // Tests for exists() method

    @Test
    public void testExistsFound() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        saveVector.add("1,1,5,key");
        saveVector.add("2,2,3,init");
        saveVector.add("3,3,7,key");

        assertTrue(Save.exists("2,2,3,init", saveVector));
    }

    @Test
    public void testExistsNotFound() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        saveVector.add("1,1,5,key");
        saveVector.add("2,2,3,init");

        assertFalse(Save.exists("5,5,9,key", saveVector));
    }

    @Test
    public void testExistsEmptyVector() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        assertFalse(Save.exists("1,1,1,key", saveVector));
    }

    @Test
    public void testExistsCaseSensitive() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        saveVector.add("1,1,5,KEY");

        assertFalse(Save.exists("1,1,5,key", saveVector));
    }

    @Test
    public void testExistsExactMatch() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        saveVector.add("1,1,5,key");

        assertFalse(Save.exists("1,1,5,ke", saveVector));
        assertFalse(Save.exists("1,1,5,keys", saveVector));
    }

    // Tests for loadSaves() method

    @Test
    public void testLoadSavesReturnsArrayList() {
        var saves = Save.loadSaves();
        assertNotNull(saves);
        assertTrue(saves instanceof java.util.ArrayList);
    }

    // Save instance tests

    @Test
    public void testSaveProperties() {
        Save save1 = new Save("User", testFile, Difficulty.MEDIUM);
        Save save2 = new Save("User", testFile, Difficulty.MEDIUM);

        assertEquals(save1.getName(), save2.getName());
        assertEquals(save1.getSaveFile(), save2.getSaveFile());
    }

    // Size calculation tests based on difficulty

    @Test
    public void testNonDevilishBoardSize() {
        Save save = new Save("User", testFile, Difficulty.EASY);
        assertNotEquals("Devilish", save.getDifficulty().toString());
    }

    @Test
    public void testDevilishBoardSize() {
        Save save = new Save("User", testFile, Difficulty.DEVILISH);
        assertEquals("Devilish", save.getDifficulty().toString());
    }

    // Edge cases

    @Test
    public void testSpecialCharactersInName() {
        Save save = new Save("User_123-Test", testFile, Difficulty.EASY);
        assertEquals("User_123-Test", save.getName());
    }

    @Test
    public void testUnicodeCharactersInName() {
        Save save = new Save("Usuario\u00e9", testFile, Difficulty.EASY);
        assertEquals("Usuario\u00e9", save.getName());
    }

    @Test
    public void testSaveFilePathPreservation() throws Exception {
        File subdir = tempFolder.newFolder("subdir");
        File nestedFile = new File(subdir, "save.dat");
        Save save = new Save("User", nestedFile, Difficulty.EASY);
        assertTrue(save.getSaveFile().getPath().contains("subdir"));
    }

    // Multiple modifications test

    @Test
    public void testMultiplePropertyChanges() throws Exception {
        testSave.setName("ChangedUser");
        testSave.setDifficulty(Difficulty.HARD);
        File newFile = tempFolder.newFile("changed.dat");
        testSave.setSaveFile(newFile);

        assertEquals("ChangedUser", testSave.getName());
        assertEquals(Difficulty.HARD, testSave.getDifficulty());
        assertEquals(newFile, testSave.getSaveFile());
    }

    // Vector helper tests

    @Test
    public void testExistsLargeVector() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            saveVector.add(i + "," + i + "," + (i % 9 + 1) + ",key");
        }

        // Should find the last element
        assertTrue(Save.exists("999,999,1,key", saveVector));
        // Should not find non-existent element
        assertFalse(Save.exists("1001,1001,1,key", saveVector));
    }

    @Test
    public void testExistsWithEmptyStrings() throws FileNotFoundException {
        Vector<String> saveVector = new Vector<>();
        saveVector.add("");
        saveVector.add("1,1,5,key");

        assertTrue(Save.exists("", saveVector));
        assertTrue(Save.exists("1,1,5,key", saveVector));
    }
}
