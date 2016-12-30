package sudoku;

import javax.swing.*;
import javax.swing.border.Border;
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
    private Font Bold = new Font("Verdana", Font.BOLD, 20);
    private static String user;
    private static Difficulty difficulty;
    private int size;

    private int[][] doodleAmount = new int[16][16]; //used to see if current cell exceeds number of allowed doodleAmount 
    private JTextField[][] doodles = new JTextField[16][16]; //used to see if current cell exceeds number of allowed doodles 
    private Font doodleFont = new Font("Verdana", Font.BOLD, 12);
    private Color doodleColor = new Color(169, 0, 97);
    private int doodleLimit;
    private boolean doodleSelected;
    private GameView view;
    private int score;

    //accessor for retrieving text field
    public JTextField[][] getTextField() {
        return f;
    }

    //accessor for retrieving coordinates array in the form f[coordinates[0]][coordinates[1]]
    public JTextField getFCoordinates() {
        return f[coordinates[0]][coordinates[1]];
    }

    //gets the status of the doodle button from GameView 
    public void doodleSelected(boolean selected) {
        doodleSelected = selected;
    }

    //retrieves the status of the doodle button (toggled or not)
    public boolean getdoodleSelected() {
        return doodleSelected;
    }

    //accessor for retrieving number of doodles in that particular cell  
    public int getDoodleAmount(JTextField f2) {
        if ("".equals(f2.getText())) { //if cell is currently empty
            doodleAmount[coordinates[0]][coordinates[1]] = 0;
            return 0;
        } else
            return doodleAmount[coordinates[0]][coordinates[1]];
    }

    /*
    Number Listener is added to each textfield so that it 
    will listen for integer input from the keyboard.
    */
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
                        case "q":
                            setCellDoodle(10 + "", getFCoordinates());
                            break;
                        case "w":
                            setCellDoodle(11 + "", getFCoordinates());
                            break;
                        case "e":
                            setCellDoodle(12 + "", getFCoordinates());
                            break;
                        case "r":
                            setCellDoodle(13 + "", getFCoordinates());
                            break;
                        case "t":
                            setCellDoodle(14 + "", getFCoordinates());
                            break;
                        case "y":
                            setCellDoodle(15 + "", getFCoordinates());
                            break;
                        case "u":
                            setCellDoodle(16 + "", getFCoordinates());
                            break;
                    }
                else if (isInteger(input)) {
                    getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",doodle");
                    setCellDoodle(input, getFCoordinates());
                }
            } else if (getdoodleSelected() == false) {
                if (!input.equals("0")) { //add integer values only
                    if (!isInteger(input) && difficulty.toString().equals("Devilish"))
                        switch (input.toLowerCase()) {
                            case "q":
                                setCell(10 + "", getFCoordinates());
                                break;
                            case "w":
                                setCell(11 + "", getFCoordinates());
                                break;
                            case "e":
                                setCell(12 + "", getFCoordinates());
                                break;
                            case "r":
                                setCell(13 + "", getFCoordinates());
                                break;
                            case "t":
                                setCell(14 + "", getFCoordinates());
                                break;
                            case "y":
                                setCell(15 + "", getFCoordinates());
                                break;
                            case "u":
                                setCell(16 + "", getFCoordinates());
                                break;
                        }
                    else if (isInteger(input)) {
                        setCell(input, getFCoordinates()); //set cell to inputed number
                        getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1] + ",guess," + view.getTimer());
                    }
                    isFinished(difficulty);
                } else if (input.equals("0")) { //blank
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
                        Highlight(f[x][y], Color.YELLOW);
                    if (!isEqual) {
                        Highlight(f[x][y], Color.WHITE);
                    }
                }
            getFCoordinates().setBackground(Color.LIGHT_GRAY);
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

    /*
    highlighter is used only on the sudoku board.
    The coordinates of the textfield inside of the sudoku board will be set 
    to the int array "coordinates".
    */
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
                        Highlight(f[x][y], Color.YELLOW);
                    if (!isEqual) {
                        Highlight(f[x][y], Color.WHITE);
                    }
                }
            e.getComponent().setBackground(Color.LIGHT_GRAY);
        }

        @Override
        public void focusLost(FocusEvent e) {
            e.getComponent().setBackground(Color.WHITE);
        }
    };

    //highlights and unhighlights cells - used by FocusListener highlighter
    public void Highlight(JTextField f2, Color color) {
        f2.setBackground(color);
    }


    //used to delete the selected cell 
    public void deleteCell(JTextField f2) {
        String stringsArr[] = getFCoordinates().getName().split(",");
        int count = getFCoordinates().getName().split(",", -1).length - 1;


        if (count >= 2 && !stringsArr[2].equals("init")) {
            setCell("", f2);
            doodleAmount[coordinates[0]][coordinates[1]] = 0; //reset doodle amount back to 0
            getFCoordinates().setName(stringsArr[0] + "," + stringsArr[1]);
            if (!difficulty.toString().equals("Devilish"))
                score -= (81 - difficulty.getNumberOfCellsToDisplay()) * 5 - Integer.parseInt(stringsArr[3]);
            else
                score -= (256 - difficulty.getNumberOfCellsToDisplay()) * 3 - Integer.parseInt(stringsArr[3]);
        }
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                Highlight(f[x][y], Color.WHITE);
    }

    //controls the number pad and inputs the chosen number into the chosen cell 
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
                    Highlight(f[x][y], Color.YELLOW);
                if (!isEqual) {
                    Highlight(f[x][y], Color.WHITE);
                }
            }
        getFCoordinates().setBackground(Color.LIGHT_GRAY);
        isFinished(difficulty);
    }

    //doodle function - sets the chosen cell to whatever the user inputs from their keyboard 
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


    //normal guesses - sets the chosen cell to whatever the user inputs from their keyboard 
    public void setCell(String key, JTextField f2) {
        if ("".equals(key)) { //if it is blank, only the text needs to be adjusted
            f2.setText(key);
            //doodleAmount[coordinates[0]][coordinates[1]] = 0;
        } else { //otherwise, set the text, font, and color
            f2.setText(key);
            f2.setFont(Bold);
            f2.setForeground(Color.BLACK);
            //doodleAmount[coordinates[0]][coordinates[1]] = 0;
        }
    }

    //check to see if a specific number is already present in a cell or use to compare contents 
    public boolean checkCell(String key, JTextField f2) {
        if (f2.getText().equals(key))
            return true;
        else
            return false;
    }

    //creates borders around cells - default is black, while red is used by checkBoard for incorrect answers
    private Border Regular = BorderFactory.createLineBorder(Color.BLACK, 1);

    public MainBoard(String s, Difficulty difficulty, GameView view) {
        user = s;
        this.view = view;
        this.difficulty = difficulty;
        if (!difficulty.toString().equals("Devilish"))
            size = 9;
        else
            size = 16;
        CreateBoard(difficulty);
        //PopulateBoard();
    }

    //create actual board with default settings 
    public final void CreateBoard(Difficulty difficulty) {
        if (!difficulty.toString().equals("Devilish")) {
            for (int x = 0; x <= 8; x++) {
                for (int y = 0; y <= 8; y++) {
                    f[x][y] = new JTextField(1);
                    f[x][y].setEditable(false);
                    f[x][y].setHorizontalAlignment(JTextField.CENTER);
                    f[x][y].setName(x + "," + y);
                    f[x][y].setBackground(Color.WHITE);
                    f[x][y].setBorder(Regular);
                    f[x][y].addFocusListener(highlighter);
                    f[x][y].addKeyListener(NumberListener);
                }
            }

            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    p[x][y] = new JPanel(new GridLayout(3, 3));
                }
            }

            setLayout(new GridLayout(3, 3, 5, 5));

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
                    f[x][y].setBackground(Color.WHITE);
                    f[x][y].setBorder(Regular);
                    f[x][y].addFocusListener(highlighter);
                    f[x][y].addKeyListener(NumberListener);
                }
            }

            for (int x = 0; x <= 3; x++) {
                for (int y = 0; y <= 3; y++) {
                    p[x][y] = new JPanel(new GridLayout(4, 4));
                }
            }

            setLayout(new GridLayout(4, 4, 5, 5));

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

    //populate the board based on the inputed .txt file
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
                            f[info[0] - 1][info[1] - 1].setForeground(Color.BLUE);
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
                                f[info[0] - 1][info[1] - 1].setForeground(Color.BLUE);
                            } else if (stringCoorArr[3].equals("guess")) {
                                f[info[0] - 1][info[1] - 1].setText(stringCoorArr[2]);
                                f[info[0] - 1][info[1] - 1].setName((info[0] - 1) + "," + (info[1] - 1) + ",guess");
                                f[info[0] - 1][info[1] - 1].setFont(Bold);
                                f[info[0] - 1][info[1] - 1].setForeground(Color.BLACK);
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

    //clears the boards and removes everything but the cells that were initially there
    public final void resetBoard() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (f[x][y].getForeground() != Color.BLUE)
                    f[x][y].setText("");
                doodleAmount[x][y] = 0;
            }
        }
    }

    //checks the board to see which guesses are right and which ones are wrong
    //cells with incorrect guesses will have a red border
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
                                Border Wrong = BorderFactory.createLineBorder(Color.RED, 3);
                                f[info[0] - 1][info[1] - 1].setBorder(Wrong);
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

    //gets the contents of the cell 
    public String getText(int x, int y) {
        return f[x][y].getText();
    }

    //checks if a string is an integer 
    public static boolean isInteger(char c) {
        String s = c + "";
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //checks if a character is an integer
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}