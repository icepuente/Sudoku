package sudoku;

/**
 * Created by Alec on 11/12/2014.
 */
public enum Difficulty {
    TEST("Test", 34), EASY("Easy", 50), MEDIUM("Medium", 45), HARD("Hard", 40), DEVILISH("Devilish", 130);

    private String difficulty;
    private int displayedCells;

    Difficulty(String difficulty, int displayedCells) {
        this.difficulty = difficulty;
        this.displayedCells = displayedCells;
    }

    @Override
    public String toString() {
        return difficulty;
    }

    public int getNumberOfCellsToDisplay() {
        return displayedCells;
    }

}
