package sudoku;

public class Sudoku {
    public static void main(String[] args) {
        SudokuTheme.applyTheme();
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setLocationRelativeTo(null);
            login.setTitle("Sudoku");
            login.setVisible(true);
        });
    }
}
