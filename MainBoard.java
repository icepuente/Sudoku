package sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainBoard extends JPanel {
    private static final long serialVersionUID = 0;
    private JTextField f[][] = new JTextField[16][16];
    private JPanel p[][] = new JPanel[4][4];
    private int coordinates[] = new int[2];
    private Font Bold = SudokuTheme.CELL_FONT;
    private static String user;
    private static Difficulty difficulty;
    private int size;

    private int[][] doodleAmount = new int[16][16];
    private JTextField[][] doodles = new JTextField[16][16];
    private Font doodleFont = SudokuTheme.CELL_DOODLE_FONT;
    private Color doodleColor = SudokuTheme.DOODLE_COLOR;
    private int doodleLimit;
    private boolean doodleSelected;
    private GameView view;
    private int score;

    public JTextField[][] getTextField() {
        return f;
    }

    public JTextField getFCoordinates() {
        return f[coordinates[0]][coordinates[1]];
    }

    public void doodleSelected(boolean selected) {
        doodleSelected = selected;
    }

    public boolean getdoodleSelected() {
        return doodleSelected;
    }

    public int getDoodleAmount(JTextField f2) {
        if ("".equals(f2.getText())) {
            doodleAmount[coordinates[0]][coordinates[1]] = 0;
            return 0;
        } else
            return doodleAmount[coordinates[0]][coordinates[1]];
    }

    public KeyListener NumberListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {
            String input = ke.getKeyChar() + "";

            String strings = getFCoordinates().getName();
            String stringsArr[] = strings.split(",");
            int count = strings.split(",", -1).length - 1;
            int doodle_num = 3;
            if (difficulty.toString().equals("Devilish"))
                doodle_num = 2;
            if (count >= 2 && stringsArr[2].equals("init")) {
            } else if (getdoodleSelected() == true && getDoodleAmount(getFCoordinates()) < doodle_num) {
                if (!isInteger(input) && difficulty.toString().equals("Devilish"))
                    switch (input.toLowerCase()) {
                        case "q": setCellDoodle(10 + "", getFCoordinates()); break;
                        case "w": setCellDoodle(11 + "", getFCoordinates()); break;
                        case "e": setCellDoodle(12 + "", getFCoordinates()); break;
                        case "r": setCellDoodle(13 + "", getFCoordinates()); break;
                        case "t": setCellDoodle(14 + "", getFCoordinates()); break;
                        case "y": setCellDoodle(15 + "", getFCoordinates()); break;
                        case "u": setCellDoodle(16 + "", getFCoordinates()); break;
                    }
                else if (isInteger(input)) {
                    getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",doodle");
                    setCellDoodle(input, getFCoordinates());
                }
            } else if (getdoodleSelected() == false) {
                if (!input.equals("0")) {
                    if (!isInteger(input) && difficulty.toString().equals("Devilish"))
                        switch (input.toLowerCase()) {
                            case "q": setCell(10 + "", getFCoordinates()); break;
                            case "w": setCell(11 + "", getFCoordinates()); break;
                            case "e": setCell(12 + "", getFCoordinates()); break;
                            case "r": setCell(13 + "", getFCoordinates()); break;
                            case "t": setCell(14 + "", getFCoordinates()); break;
                            case "y": setCell(15 + "", getFCoordinates()); break;
                            case "u": setCell(16 + "", getFCoordinates()); break;
                        }
                    else if (isInteger(input)) {
                        setCell(input, getFCoordinates());
                        getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",guess," + view.getTimer());
                    }
                    isFinished(difficulty);
                } else if (input.equals("0")) {
                    setCell("", getFCoordinates());
                    getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1]);
                }
            }

            boolean isEqual, isEmpty;
            for (int y = 0; y < size; y++)
                for (int x = 0; x < size; x++) {
                    isEqual = checkCell(f[x][y].getText(), getFCoordinates());
                    isEmpty = checkCell("", f[x][y]);

                    if (isEqual && !isEmpty)
                        Highlight(f[x][y], SudokuTheme.CELL_HIGHLIGHT);
                    if (!isEqual) {
                        Highlight(f[x][y], SudokuTheme.CELL_BG);
                    }
                }
            getFCoordinates().setBackground(SudokuTheme.CELL_SELECTED);
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }

        public String getKey(KeyEvent ke) {
            return ke.getKeyChar() + "";
        }
    };

    private FocusListener highlighter = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            String stringCoor = e.getComponent().getName();
            String stringCoorArr[] = stringCoor.split(",");
            coordinates[0] = Integer.parseInt(stringCoorArr[0]);
            coordinates[1] = Integer.parseInt(stringCoorArr[1]);
            boolean isEqual, isEmpty;

            for (int y = 0; y < size; y++)
                for (int x = 0; x < size; x++) {
                    isEqual = checkCell(f[x][y].getText(), getFCoordinates());
                    isEmpty = checkCell("", f[x][y]);

                    if (isEqual && !isEmpty)
                        Highlight(f[x][y], SudokuTheme.CELL_HIGHLIGHT);
                    if (!isEqual) {
                        Highlight(f[x][y], SudokuTheme.CELL_BG);
                    }
                }
            e.getComponent().setBackground(SudokuTheme.CELL_SELECTED);
        }

        @Override
        public void focusLost(FocusEvent e) {
            e.getComponent().setBackground(SudokuTheme.CELL_BG);
        }
    };

    public void Highlight(JTextField f2, Color color) {
        f2.setBackground(color);
    }

    public void deleteCell(JTextField f2) {
        String stringsArr[] = getFCoordinates().getName().split(",");
        int count = getFCoordinates().getName().split(",", -1).length - 1;

        if (count >= 2 && !stringsArr[2].equals("init")) {
            setCell("", f2);
            doodleAmount[coordinates[0]][coordinates[1]] = 0;
            getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1]);
            if (!difficulty.toString().equals("Devilish"))
                score -= (81 - difficulty.getNumberOfCellsToDisplay()) * 5 - Integer.parseInt(stringsArr[3]);
            else
                score -= (256 - difficulty.getNumberOfCellsToDisplay()) * 3 - Integer.parseInt(stringsArr[3]);
        }
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                Highlight(f[x][y], SudokuTheme.CELL_BG);
    }

    public void numberPad(String num) {
        String strings = getFCoordinates().getName();
        String stringsArr[] = strings.split(",");
        int count = strings.split(",", -1).length - 1;
        int doodle_num = 3;
        if (difficulty.toString().equals("Devilish"))
            doodle_num = 2;
        if (count == 2 && stringsArr[2].equals("init")) {
        } else if (getdoodleSelected() == true && getDoodleAmount(getFCoordinates()) < doodle_num) {
            getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",doodle");
            setCellDoodle(num, getFCoordinates());
        } else if (getdoodleSelected() == false) {
            getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",guess");
            setCell(num, getFCoordinates());
        }

        boolean isEqual, isEmpty;
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++) {
                isEqual = checkCell(f[x][y].getText(), getFCoordinates());
                isEmpty = checkCell("", f[x][y]);

                if (isEqual && !isEmpty)
                    Highlight(f[x][y], SudokuTheme.CELL_HIGHLIGHT);
                if (!isEqual) {
                    Highlight(f[x][y], SudokuTheme.CELL_BG);
                }
            }
        getFCoordinates().setBackground(SudokuTheme.CELL_SELECTED);
        isFinished(difficulty);
    }

    public void setCellDoodle(String key, JTextField f2) {
        String doodle = f2.getText();
        int doodleLimit = getDoodleAmount(f2);

        if (doodleLimit == 0)
            doodle = key;
        else
            doodle += "|" + key;

        f2.setText(doodle);
        f2.setFont(doodleFont);
        f2.setForeground(doodleColor);

        doodleLimit++;
        doodleAmount[coordinates[0]][coordinates[1]] = doodleLimit;
    }

    public void setCell(String key, JTextField f2) {
        if ("".equals(key)) {
            f2.setText(key);
        } else {
            f2.setText(key);
            f2.setFont(Bold);
            f2.setForeground(SudokuTheme.GUESS_NUMBER);
        }
    }

    public boolean checkCell(String key, JTextField f2) {
        if (f2.getText().equals(key))
            return true;
        else
            return false;
    }

    private Border Regular = SudokuTheme.createCellBorder();

    public MainBoard(String s, Difficulty difficulty, GameView view) {
        user = s;
        this.view = view;
        this.difficulty = difficulty;
        if (!difficulty.toString().equals("Devilish"))
            size = 9;
        else
            size = 16;
        setBackground(SudokuTheme.BG_DARK);
        CreateBoard(difficulty);
    }

    public final void CreateBoard(Difficulty difficulty) {
        if (!difficulty.toString().equals("Devilish")) {
            for (int x = 0; x <= 8; x++) {
                for (int y = 0; y <= 8; y++) {
                    f[x][y] = new JTextField(1);
                    f[x][y].setEditable(false);
                    f[x][y].setHorizontalAlignment(JTextField.CENTER);
                    f[x][y].setName(x + "," + y);
                    f[x][y].setBackground(SudokuTheme.CELL_BG);
                    f[x][y].setForeground(SudokuTheme.TEXT_PRIMARY);
                    f[x][y].setBorder(Regular);
                    f[x][y].setFont(Bold);
                    f[x][y].addFocusListener(highlighter);
                    f[x][y].addKeyListener(NumberListener);
                }
            }

            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    p[x][y] = new JPanel(new GridLayout(3, 3, 1, 1));
                    p[x][y].setBackground(SudokuTheme.BOX_BORDER);
                    p[x][y].setBorder(BorderFactory.createLineBorder(SudokuTheme.BOX_BORDER, 2));
                }
            }

            setLayout(new GridLayout(3, 3, 3, 3));

            for (int j = 0; j <= 2; j++) {
                for (int i = 0; i <= 2; i++) {
                    for (int x = 0; x <= 2; x++) {
                        for (int y = 0; y <= 2; y++) {
                            p[j][i].add(f[x + j * 3][y + i * 3]);
                        }
                    }
                    add(p[j][i]);
                }
            }
        } else {
            for (int x = 0; x <= 15; x++) {
                for (int y = 0; y <= 15; y++) {
                    f[x][y] = new JTextField(1);
                    f[x][y].setEditable(false);
                    f[x][y].setHorizontalAlignment(JTextField.CENTER);
                    f[x][y].setName(x + "," + y);
                    f[x][y].setBackground(SudokuTheme.CELL_BG);
                    f[x][y].setForeground(SudokuTheme.TEXT_PRIMARY);
                    f[x][y].setBorder(Regular);
                    f[x][y].setFont(Bold);
                    f[x][y].addFocusListener(highlighter);
                    f[x][y].addKeyListener(NumberListener);
                }
            }

            for (int x = 0; x <= 3; x++) {
                for (int y = 0; y <= 3; y++) {
                    p[x][y] = new JPanel(new GridLayout(4, 4, 1, 1));
                    p[x][y].setBackground(SudokuTheme.BOX_BORDER);
                    p[x][y].setBorder(BorderFactory.createLineBorder(SudokuTheme.BOX_BORDER, 2));
                }
            }

            setLayout(new GridLayout(4, 4, 3, 3));

            for (int j = 0; j <= 3; j++) {
                for (int i = 0; i <= 3; i++) {
                    for (int x = 0; x <= 3; x++) {
                        for (int y = 0; y <= 3; y++) {
                            p[j][i].add(f[x + j * 4][y + i * 4]);
                        }
                    }
                    add(p[j][i]);
                }
            }
        }
    }

    public final void PopulateBoard() {
        File file = new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/TestCaseSave.txt");
        int info[] = new int[2];
        try {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int count = line.split(",", -1).length - 1;

                    if (count == 3 && Character.isDigit(line.charAt(0))) {
                        String stringCoorArr[] = line.split(",");
                        info[0] = Integer.parseInt(stringCoorArr[0]);
                        info[1] = Integer.parseInt(stringCoorArr[1]);

                        if (!isInteger(f[info[0] - 1][info[1] - 1].getText())) {
                            f[info[0] - 1][info[1] - 1].setText(stringCoorArr[2]);
                            f[info[0] - 1][info[1] - 1].setName((info[0] - 1) + "," + (info[1] - 1) + ",init");
                            f[info[0] - 1][info[1] - 1].setFont(Bold);
                            f[info[0] - 1][info[1] - 1].setForeground(SudokuTheme.GIVEN_NUMBER);
                        } else
                            break;
                    }
                }
            } catch (NumberFormatException e) {
            }
        } catch (FileNotFoundException e) {
        }
    }

    public final void PopulateBoard(File file) {
        int info[] = new int[2];
        try {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int count = line.split(",", -1).length - 1;

                    if (count >= 3 && Character.isDigit(line.charAt(0))) {
                        String stringCoorArr[] = line.split(",");
                        info[0] = Integer.parseInt(stringCoorArr[0]);
                        info[1] = Integer.parseInt(stringCoorArr[1]);

                        if (!isInteger(f[info[0] - 1][info[1] - 1].getText())) {
                            if (stringCoorArr[3].equals("init")) {
                                f[info[0] - 1][info[1] - 1].setText(stringCoorArr[2]);
                                f[info[0] - 1][info[1] - 1].setName((info[0] - 1) + "," + (info[1] - 1) + ",init");
                                f[info[0] - 1][info[1] - 1].setFont(Bold);
                                f[info[0] - 1][info[1] - 1].setForeground(SudokuTheme.GIVEN_NUMBER);
                            } else if (stringCoorArr[3].equals("guess")) {
                                f[info[0] - 1][info[1] - 1].setText(stringCoorArr[2]);
                                f[info[0] - 1][info[1] - 1].setName((info[0] - 1) + "," + (info[1] - 1) + ",guess");
                                f[info[0] - 1][info[1] - 1].setFont(Bold);
                                f[info[0] - 1][info[1] - 1].setForeground(SudokuTheme.GUESS_NUMBER);
                            } else if (stringCoorArr[count].equals("doodle")) {
                                f[info[0] - 1][info[1] - 1].setText(stringCoorArr[2]);
                                f[info[0] - 1][info[1] - 1].setName((info[0] - 1) + "," + (info[1] - 1) + ",doodle");
                                f[info[0] - 1][info[1] - 1].setFont(doodleFont);
                                f[info[0] - 1][info[1] - 1].setForeground(doodleColor);
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
            }
        } catch (FileNotFoundException e) {
        }
    }

    public final void resetBoard() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (f[x][y].getForeground() != SudokuTheme.GIVEN_NUMBER)
                    f[x][y].setText("");
                doodleAmount[x][y] = 0;
            }
        }
    }

    public final int CheckBoard(Difficulty d) {
        File file = new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt");
        score = 0;
        int info[] = new int[2];
        try {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int count = line.split(",", -1).length - 1;

                    if (count >= 2 && Character.isDigit(line.charAt(0))) {
                        String stringCoorArr[] = line.split(",");
                        info[0] = Integer.parseInt(stringCoorArr[0]);
                        info[1] = Integer.parseInt(stringCoorArr[1]);

                        if (f[info[0] - 1][info[1] - 1].getFont() != doodleFont && stringCoorArr[3].equals("key")) {
                            if (!f[info[0] - 1][info[1] - 1].getText().equals(stringCoorArr[2]) && !f[info[0] - 1][info[1] - 1].getText().equals("")) {
                                f[info[0] - 1][info[1] - 1].setBorder(SudokuTheme.createErrorBorder());
                            } else f[info[0] - 1][info[1] - 1].setBorder(Regular);

                            count = f[info[0] - 1][info[1] - 1].getName().split(",", -1).length - 1;
                            String type[] = f[info[0] - 1][info[1] - 1].getName().split(",");
                            if (count > 2 && f[info[0] - 1][info[1] - 1].getText().equals(stringCoorArr[2]) && type[2].equals("guess")) {
                                if (!difficulty.toString().equals("Devilish"))
                                    score += (81 - difficulty.getNumberOfCellsToDisplay()) * 7 - view.getTimer();
                                else
                                    score += (256 - difficulty.getNumberOfCellsToDisplay()) * 3 - view.getTimer();
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
            }
        } catch (FileNotFoundException e) {
        }
        return score;
    }

    public final void isFinished(Difficulty d) {
        File file = new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt");
        int info[] = new int[2];
        try {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int count = line.split(",", -1).length - 1;

                    if (count == 3 && Character.isDigit(line.charAt(0))) {
                        String stringCoorArr[] = line.split(",");
                        info[0] = Integer.parseInt(stringCoorArr[0]);
                        info[1] = Integer.parseInt(stringCoorArr[1]);

                        if (stringCoorArr[3].equals("key")) {
                            if (!f[info[0] - 1][info[1] - 1].getText().equals(stringCoorArr[2]) || f[info[0] - 1][info[1] - 1].getText().equals("")) {
                                return;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
            }
        } catch (FileNotFoundException e) {
        }
        int time = view.getTimer();
        view.stopTimer();
        CheckBoard(difficulty);

        JOptionPane.showMessageDialog(this, "You have completed this game!");
        view.dispose();
        LeaderBoard leaderBoard = new LeaderBoard(user);
        leaderBoard.setLocationRelativeTo(null);
        leaderBoard.setVisible(true);
        leaderBoard.setTitle("Leaderboard");

        new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt").delete();
        if (difficulty == Difficulty.TEST)
            leaderBoard.newHighScore(Difficulty.EASY, user, score + "", time + "");
        leaderBoard.newHighScore(d, user, score + "", time + "");
    }

    public String getText(int x, int y) {
        return f[x][y].getText();
    }

    public static boolean isInteger(char c) {
        String s = c + "";
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
