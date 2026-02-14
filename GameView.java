package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameView extends JFrame {
    private static final long serialVersionUID = 0;
    private static String user;
    private static MainBoard JBoard;
    private JPanel JSidebar = new JPanel();
    private JPanel JNumberPad = new JPanel();
    private JButton JButtons[] = new JButton[16];
    private JTextField JScore = new JTextField();

    public void setJScore(String score) {
        JScore.setText("Score: " + Integer.toString(Score));
    }

    private int Score = 0;
    private static int elapsedTime;

    public static int getElapsed() {
        return elapsedTime;
    }

    public static void setElapsed(int savedTime) {
        elapsedTime = savedTime;
    }

    private JButton JCheckBoard;
    private JButton JDelete;
    private JButton JReset;
    private JButton JMainMenu;
    private JToggleButton JPause;
    private Timer timer = new Timer(1000, new TimerListener());
    private JLabel timerLabel = new JLabel();
    private JTextArea JBoardText = new JTextArea(4, 20);
    private JToggleButton Doodle;
    private JButton selectedButton = new JButton();

    private boolean doodleSelected;
    private boolean paused = false;
    private final Difficulty difficulty;
    private int size;

    private String doodleMessage = "";
    private String guessMessage = "";
    private String pauseMessage = "";
    private String checkBoardMessage = "";
    private String defaultMessage = "";
    private String helpMessage = "";
    private String deleteMessage = "";
    private String resetMessage = "";

    private int height = 1100;
    private int width = 1800;

    public static MainBoard getJBoard() {
        return JBoard;
    }

    public GameView(String s, final Difficulty difficulty) {
        user = s;
        this.difficulty = difficulty;

        if (!difficulty.toString().equals("Devilish")) {
            size = 9;
            setSize(width / 2, height / 2);
        } else {
            size = 16;
            setSize(width, height - 100);
        }

        elapsedTime = 0;
        JBoard = new MainBoard(user, difficulty, this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(SudokuTheme.BG_DARK);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null);
        setTitle(difficulty.toString());

        checkBoardMessage = "When the board is checked, only guesses (not doodles) are considered when calculating your score. Incorrect guesses will receive a red border.";
        doodleMessage = "Doodle mode: place multiple numbers (max 4) into a cell. These appear in orange and won't affect your score.";
        defaultMessage = "Welcome to Sudoku! Select an empty cell and use the number pad or keyboard to input a number. Click any button for helpful tips.";
        pauseMessage = "Game paused. The board is hidden until you resume.";
        helpMessage = "Tips: Select an open cell before entering a number. Toggle Doodle mode to make candidate marks (max 4 per cell).";
        deleteMessage = "Select a cell and click Delete to clear its contents.";
        resetMessage = "Reset clears all entries that weren't part of the original puzzle.";

        // Number pad buttons
        JNumberPad.setLayout(new GridLayout(size <= 9 ? 3 : 4, size <= 9 ? 3 : 4, 4, 4));
        JNumberPad.setOpaque(false);

        for (int i = 1; i <= size; i++) {
            JButtons[i - 1] = createNumpadButton(Integer.toString(i));
            JNumberPad.add(JButtons[i - 1]);
            JButtons[i - 1].setFocusable(false);

            final String num = Integer.toString(i);
            JButtons[i - 1].addActionListener(e -> JBoard.numberPad(num));
        }
        JNumberPad.setFocusable(false);

        // Doodle toggle
        Doodle = SudokuTheme.createStyledToggleButton("Doodle");
        Doodle.setFocusable(false);
        Doodle.addActionListener(e -> {
            AbstractButton ab = (AbstractButton) e.getSource();
            boolean selected = ab.getModel().isSelected();
            JBoard.doodleSelected(selected);
            JBoardText.setText(selected ? doodleMessage : defaultMessage);
        });

        // Window close handling
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] options = {"Save & Return to Menu", "Save & Exit", "Keep Playing", "Help"};
                int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to quit?", "Quit",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[2]);

                switch (n) {
                    case 0:
                        try { Save.saveGame(user, difficulty); } catch (IOException ex) {
                            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        timer.stop();
                        MainMenu m = new MainMenu(user);
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                        setVisible(false);
                        dispose();
                        break;
                    case 1:
                        try { Save.saveGame(user, difficulty); } catch (IOException ex) {
                            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        timer.stop();
                        System.exit(0);
                        break;
                    case 3:
                        JBoardText.setText(helpMessage);
                        break;
                }
            }
        });

        Score = JBoard.CheckBoard(difficulty);
        JScore.setText("Score: " + Integer.toString(Score));
        JScore.setEditable(false);
        JScore.setFocusable(false);
        JScore.setHorizontalAlignment(JTextField.CENTER);
        JScore.setFont(SudokuTheme.BODY_BOLD);
        JScore.setBackground(SudokuTheme.BG_MEDIUM);
        JScore.setForeground(SudokuTheme.ACCENT_LIGHT);
        JScore.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SudokuTheme.CELL_BORDER, 1),
                new EmptyBorder(8, 12, 8, 12)));

        // Action buttons
        JCheckBoard = SudokuTheme.createStyledButton("Check Board");
        JCheckBoard.setFocusable(false);
        JCheckBoard.addActionListener(e -> {
            JBoardText.setText(checkBoardMessage);
            Score = JBoard.CheckBoard(difficulty);
            JScore.setText("Score: " + (JBoard.CheckBoard(difficulty) + ""));
        });

        JDelete = SudokuTheme.createSecondaryButton("Delete");
        JDelete.setFocusable(false);
        JDelete.addActionListener(e -> {
            selectedButton = JDelete;
            JBoardText.setText(deleteMessage);
            JBoard.deleteCell(JBoard.getFCoordinates());
        });

        JReset = SudokuTheme.createSecondaryButton("Reset");
        JReset.setFocusable(false);
        JReset.addActionListener(e -> {
            JBoardText.setText(resetMessage);
            JBoard.resetBoard();
        });

        JMainMenu = SudokuTheme.createSecondaryButton("Main Menu");
        JMainMenu.setFocusable(false);
        JMainMenu.addActionListener(e -> {
            String[] options = {"Yes - save progress", "Keep playing", "Help"};
            int n = JOptionPane.showOptionDialog(null,
                    "Return to main menu?", "Quit",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            switch (n) {
                case 0:
                    try { Save.saveGame(user, difficulty); } catch (IOException ex) {
                        Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timer.stop();
                    MainMenu m = new MainMenu(user);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                    setVisible(false);
                    dispose();
                    break;
                case 2:
                    JBoardText.setText(helpMessage);
                    break;
            }
        });

        // Pause toggle
        JPause = SudokuTheme.createStyledToggleButton("Pause");
        JPause.setFocusable(false);
        JPause.addActionListener(e -> {
            AbstractButton ab = (AbstractButton) e.getSource();
            boolean isPaused = ab.getModel().isSelected();

            if (isPaused) {
                timer.stop();
                JBoard.setVisible(false);
                JBoardText.setText(pauseMessage);
                JPause.setText("Resume");
            } else {
                timer.start();
                JBoard.setVisible(true);
                JBoardText.setText(defaultMessage);
                JPause.setText("Pause");
            }
        });

        timer.start();

        // Timer label
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(SudokuTheme.TIMER_FONT);
        timerLabel.setForeground(SudokuTheme.TEXT_PRIMARY);

        // Help text area
        JBoardText.setText(defaultMessage);
        JBoardText.setLineWrap(true);
        JBoardText.setEditable(false);
        JBoardText.setFocusable(false);
        JBoardText.setWrapStyleWord(true);
        JBoardText.setFont(SudokuTheme.SMALL_FONT);
        JBoardText.setBackground(SudokuTheme.BG_MEDIUM);
        JBoardText.setForeground(SudokuTheme.TEXT_SECONDARY);
        JBoardText.setBorder(new EmptyBorder(10, 12, 10, 12));

        JScrollPane scrollPane = new JScrollPane(JBoardText);
        scrollPane.setPreferredSize(new Dimension(250, 70));
        scrollPane.setBorder(BorderFactory.createLineBorder(SudokuTheme.CELL_BORDER, 1));
        scrollPane.getViewport().setBackground(SudokuTheme.BG_MEDIUM);

        // Build sidebar
        JSidebar.setLayout(new BoxLayout(JSidebar, BoxLayout.Y_AXIS));
        JSidebar.setBackground(SudokuTheme.BG_DARK);
        JSidebar.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Timer section
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false);
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        timerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        // Score section
        JScore.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Numpad wrapper
        JPanel numpadWrapper = new JPanel(new BorderLayout());
        numpadWrapper.setOpaque(false);
        numpadWrapper.add(JNumberPad, BorderLayout.CENTER);
        numpadWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, size <= 9 ? 130 : 170));

        // Doodle + Delete row
        JPanel doodleDeletePanel = new JPanel(new GridLayout(1, 2, 6, 0));
        doodleDeletePanel.setOpaque(false);
        doodleDeletePanel.add(Doodle);
        doodleDeletePanel.add(JDelete);
        doodleDeletePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Action buttons
        JCheckBoard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        JPanel actionRow = new JPanel(new GridLayout(1, 2, 6, 0));
        actionRow.setOpaque(false);
        actionRow.add(JReset);
        actionRow.add(JPause);
        actionRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JMainMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Assemble sidebar
        JSidebar.add(timerPanel);
        JSidebar.add(Box.createVerticalStrut(8));
        JSidebar.add(JScore);
        JSidebar.add(Box.createVerticalStrut(14));
        JSidebar.add(numpadWrapper);
        JSidebar.add(Box.createVerticalStrut(10));
        JSidebar.add(doodleDeletePanel);
        JSidebar.add(Box.createVerticalStrut(8));
        JSidebar.add(JCheckBoard);
        JSidebar.add(Box.createVerticalStrut(6));
        JSidebar.add(actionRow);
        JSidebar.add(Box.createVerticalStrut(10));
        JSidebar.add(JMainMenu);
        JSidebar.add(Box.createVerticalGlue());

        JSidebar.setPreferredSize(new Dimension(240, 0));

        // Board panel
        JPanel boardPanel = new JPanel(new BorderLayout());
        boardPanel.setBackground(SudokuTheme.BG_DARK);
        boardPanel.setBorder(new EmptyBorder(12, 12, 6, 6));
        boardPanel.add(JBoard, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(SudokuTheme.BG_DARK);
        bottomPanel.setBorder(new EmptyBorder(4, 12, 8, 12));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.setPreferredSize(new Dimension(0, 85));

        // Main layout
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(JSidebar, BorderLayout.LINE_END);
        getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
        setVisible(true);
    }

    private JButton createNumpadButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(SudokuTheme.PRIMARY);
                } else if (getModel().isRollover()) {
                    g2.setColor(SudokuTheme.BG_LIGHT);
                } else {
                    g2.setColor(SudokuTheme.BG_MEDIUM);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(SudokuTheme.CELL_BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.dispose();

                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.setColor(SudokuTheme.TEXT_PRIMARY);
                g.setFont(getFont());
                g.drawString(getText(), x, y);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        button.setFont(SudokuTheme.NUMPAD_FONT);
        button.setForeground(SudokuTheme.TEXT_PRIMARY);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public MainBoard getBoard() {
        return JBoard;
    }

    public void setScore() {
        Score = JBoard.CheckBoard(difficulty);
        JScore.setText("Score: " + Integer.toString(Score));
    }

    public void stopTimer() {
        this.timer.stop();
    }

    public int getTimer() {
        return elapsedTime;
    }

    class TimerListener implements ActionListener {
        int elapsedSeconds, elapsedMinutes, elapsedHours;
        String elapsedSecondsStr, elapsedMinutesStr, elapsedHoursStr;

        @Override
        public void actionPerformed(ActionEvent evt) {
            int time = elapsedTime;

            elapsedHours = 0;
            elapsedMinutes = 0;
            elapsedSeconds = 0;

            while (time >= 3600) {
                time = time - 3600;
                elapsedHours++;
            }
            while (time >= 60) {
                time = time - 60;
                elapsedMinutes++;
            }

            elapsedSeconds = time;
            elapsedTime++;

            if (elapsedHours < 10) elapsedHoursStr = "0" + Integer.toString(elapsedHours);
            else elapsedHoursStr = Integer.toString(elapsedHours);

            if (elapsedMinutes < 10) elapsedMinutesStr = "0" + Integer.toString(elapsedMinutes);
            else elapsedMinutesStr = Integer.toString(elapsedMinutes);

            if (elapsedSeconds < 10) elapsedSecondsStr = "0" + Integer.toString(elapsedSeconds);
            else elapsedSecondsStr = Integer.toString(elapsedSeconds);

            timerLabel.setText(elapsedHoursStr + ":" + elapsedMinutesStr + ":" + elapsedSecondsStr);
        }
    }
}
