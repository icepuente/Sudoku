package sudoku;

import javax.swing.*;
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
    private JPanel JSidebar = new JPanel(new GridLayout(7, 1));
    private JPanel JNumberPad = new JPanel(new GridLayout(4, 4));
    private JPanel JPanel2 = new JPanel(new BorderLayout(1, 3));
    private JPanel JPanel3 = new JPanel(new BorderLayout(7, 7));
    private JPanel JDGPanel = new JPanel(new GridLayout(1, 3, 0, 0));
    private JPanel JMain = new JPanel(new BorderLayout(2, 2));
    private JPanel JMain2 = new JPanel(new BorderLayout(1, 3));
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

    ;

    public static void setElapsed(int savedTime) {
        elapsedTime = savedTime;
    }

    ;
    private JButton JCheckBoard = new JButton("Check Board");
    private JButton JDelete = new JButton("Delete");
    private JButton JReset = new JButton("Reset Board");
    private JButton JMainMenu = new JButton("Main Menu");
    private JToggleButton JPause = new JToggleButton("Pause/Resume");
    private Timer timer = new Timer(1000, new TimerListener());
    private JLabel timerLabel = new JLabel();
    private JLabel Jempty1 = new JLabel();
    private JLabel Jempty2 = new JLabel();
    private JLabel Jempty3 = new JLabel();
    private JLabel Jempty4 = new JLabel();
    private JTextArea JBoardText = new JTextArea(5, 20);
    private JToggleButton Doodle = new JToggleButton("Doodle");
    private JButton selectedButton = new JButton();

    private boolean doodleSelected;
    private boolean paused = false;
    private Font font = new Font("Verdana", Font.PLAIN, 12);
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

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //private int height = screenSize.height;
    //private int width = screenSize.width;
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

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setLocationRelativeTo(null);

        setTitle(difficulty.toString());

        checkBoardMessage = "\n\t When the board is checked, only guesses (not doodles) are considered"
                + " when \n\t calculating your score and determining which cells have correct  \n" +
                "\tentries. "
                + "All incorrect guesses will receive a red border, while correct \n\t ones will remain the"
                + " same.";
        doodleMessage = "\n\t The doodle function allows you to place multiple numbers (with a max of 4)"
                + " into a cell. These \n\t will end up being purple in color and will not affect your score"
                + " if you decide to check the board for correctness. ";
        defaultMessage = "\t Welcome to Sudoku!"
                + "\n\t To get started, select an empty cell on the board and use either the number pad or your keyboard"
                + " to input a number."
                + "\n\t If you find yourself confused about a particular feature, clicking its button will generally"
                + " pull up helpful tips \n\t and information in this box. If that's not enough, the help section can"
                + " be accessed by clicking the main menu.";
        pauseMessage = "\n\t The game is now paused, and the board will remain invisible"
                + " (sorry, no cheating!) until it is resumed.";
        helpMessage = "\tHELPFUL TIPS: Before making a number selection with either your keyboard or the "
                + "number pad, you must first select an \t\topen cell on the board. To make a doodle (a max"
                + " of 4 is allowed per cell), click the Doodle button to toggle the feature. You \t\twill "
                + "remain in doodle mode until the button is clicked again.";

        deleteMessage = "\n\t To delete a guess or doodle, simply select the cell that you'd like to clear "
                + "and click Delete. \n\t NOTE: all contents will be removed, even if multiple doodles are present.";
        resetMessage = "\n\t Reseting the board clears every single entry that wasn't previously there, but you've"
                + "\n\t probably already figured that out by now.";


        //adds functionality to each button in the number pad 
        //user must first click on a cell and then click on a number on the number pad
        for (int i = 1; i <= size; i++) {
            JButtons[i - 1] = new JButton(Integer.toString(i));
            JNumberPad.add(JButtons[i - 1]);
            JButtons[i - 1].setFocusable(false);

            final String num = Integer.toString(i);

            JButtons[i - 1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JBoard.numberPad(num);
                }
            });
        }
        JNumberPad.setFocusable(false);


        //doodle function
        Doodle.setFocusable(false);
        Doodle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();

                boolean selected = abstractButton.getModel().isSelected();
                JBoard.doodleSelected(selected);
                JBoardText.setText(selected ? doodleMessage : defaultMessage);
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                String[] options = {"Return to Main Menu saving current game",
                        "Exit saving current game",
                        "No, I want to play Sudoku forever.",
                        "I'm having issues. With everything."};
                int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to quit?",
                        "Quit",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                switch (n) {
                    case 0:
                        try {
                            Save.saveGame(user, difficulty);
                        } catch (IOException ex) {
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
                        try {
                            Save.saveGame(user, difficulty);
                        } catch (IOException ex) {
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

        //Check board
        JCheckBoard.setFocusable(false);
        JCheckBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JBoardText.setText(checkBoardMessage);
                Score = JBoard.CheckBoard(difficulty);
                JScore.setText("Score: " + (JBoard.CheckBoard(difficulty) + ""));
            }
        });

        //delete button - user must first click on a cell and then click on delete
        JDelete.setFocusable(false);
        JDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedButton = JDelete;
                JBoardText.setText(deleteMessage);
                JBoard.deleteCell(JBoard.getFCoordinates());
            }
        });

        JBoardText.setText(defaultMessage);


        //reset button 
        JReset.setFocusable(false);
        JReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JBoardText.setText(resetMessage);
                JBoard.resetBoard();
            }
        });


        //main menu button
        JMainMenu.setFocusable(false);
        JMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(JSidebar,"Are you sure you want to quit?", "Quit",
                //JOptionPane.PLAIN_MESSAGE);

                String[] options = {"Yes - Current progress will be saved.",
                        "No, I want to play Sudoku forever.",
                        "I'm having issues. With everything."};
                int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to quit?",
                        "Quit",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                switch (n) {
                    case 0:
                        try {
                            Save.saveGame(user, difficulty);
                        } catch (IOException ex) {
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
            }
        });


        //pause button
        JPause.setFocusable(false);
        JPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();

                boolean paused = abstractButton.getModel().isSelected();

                if (paused) {
                    timer.stop();
                    JBoard.setVisible(false);
                    JBoardText.setText(pauseMessage);
                } else if (!paused) {
                    timer.start();
                    JBoard.setVisible(true);
                    JBoardText.setText(defaultMessage);
                }
            }

        });


        timer.start();
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(JBoardText);
        scrollPane.setPreferredSize(new Dimension(250, 80));
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JBoardText.setText(defaultMessage);
        JBoardText.setLineWrap(true);
        JBoardText.setEditable(false);
        JBoardText.setFocusable(false);
        JBoardText.setWrapStyleWord(true);
        JBoardText.setPreferredSize(new Dimension(250, 80));
        JBoardText.setBorder(BorderFactory.createEmptyBorder(3, 3, 20, 20));
        JBoardText.setFont(font);


        Jempty1.setText("  ");
        Jempty2.setText("        ");
        Jempty3.setText("     ");
        Jempty4.setText("     ");

        JPanel2.add(Jempty3, BorderLayout.LINE_START);
        JPanel2.add(JNumberPad, BorderLayout.CENTER);
        JPanel2.add(Jempty4, BorderLayout.LINE_END);

        JSidebar.add(timerLabel);
        JSidebar.add(JScore);

        JDGPanel.add(Doodle, BorderLayout.LINE_START);
        JDGPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 20));
        JDGPanel.add(JDelete, BorderLayout.LINE_END);

        JSidebar.add(Jempty1);
        JSidebar.add(JPanel2);
        JSidebar.add(JDGPanel);

        JPanel3.add(Jempty2, BorderLayout.PAGE_START);
        JPanel3.add(JCheckBoard, BorderLayout.CENTER);
        JPanel3.add(JReset, BorderLayout.LINE_START);
        JPanel3.add(JPause, BorderLayout.LINE_END);

        JSidebar.add(JPanel3);
        JSidebar.add(JMainMenu);


        JBoard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        JMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        JMain.add(JBoard);
        JMain.add(JSidebar, BorderLayout.LINE_END);
        JMain.add(JMain2, BorderLayout.PAGE_END);
        JMain.add(scrollPane, BorderLayout.PAGE_END);
        add(JMain);
        setVisible(true);
    }


    //accessor for the actual board 
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
        int elapsedSeconds,
                elapsedMinutes,
                elapsedHours;

        String elapsedSecondsStr,
                elapsedMinutesStr,
                elapsedHoursStr;

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

            //System.out.println(time);
            //System.out.println(elapsedTime);
            elapsedSeconds = time;

            elapsedTime++;

            if (elapsedHours < 10) elapsedHoursStr = "0" + Integer.toString(elapsedHours);
            else elapsedHoursStr = Integer.toString(elapsedHours);

            if (elapsedMinutes < 10) elapsedMinutesStr = "0" + Integer.toString(elapsedMinutes);
            else elapsedMinutesStr = Integer.toString(elapsedMinutes);

            if (elapsedSeconds < 10) elapsedSecondsStr = "0" + Integer.toString(elapsedSeconds);
            else elapsedSecondsStr = Integer.toString(elapsedSeconds);

            timerLabel.setText("Time: " + elapsedHoursStr + ":" + elapsedMinutesStr + ":" + elapsedSecondsStr);
        }
    }

}
