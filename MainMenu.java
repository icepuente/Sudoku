package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu extends JFrame {

    public MainMenu(String s) {
        this.setTitle(s);
        user = s;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(SudokuTheme.BG_DARK);

        JPanel card = SudokuTheme.createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Sudoku");
        titleLabel.setFont(SudokuTheme.TITLE_FONT);
        titleLabel.setForeground(SudokuTheme.PRIMARY_LIGHT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + user);
        welcomeLabel.setFont(SudokuTheme.BODY_FONT);
        welcomeLabel.setForeground(SudokuTheme.TEXT_MUTED);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        jButtonContinue = SudokuTheme.createStyledButton("Continue Game");
        jButtonContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonContinue.setMaximumSize(new Dimension(260, 44));
        jButtonContinue.addActionListener(e -> {
            try {
                continueAction();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jButtonNewGame = SudokuTheme.createStyledButton("New Game");
        jButtonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonNewGame.setMaximumSize(new Dimension(260, 44));
        jButtonNewGame.addActionListener(e -> {
            try {
                newGameAction();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jButtonLeaderboard = SudokuTheme.createSecondaryButton("Leaderboard");
        jButtonLeaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonLeaderboard.setMaximumSize(new Dimension(260, 44));
        jButtonLeaderboard.addActionListener(e -> leaderboardAction());

        jButtonLoginReturn = SudokuTheme.createSecondaryButton("Sign Out");
        jButtonLoginReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonLoginReturn.setMaximumSize(new Dimension(260, 44));
        jButtonLoginReturn.addActionListener(e -> loginReturnAction());

        // Version
        jLabel1 = new JLabel("v1.00");
        jLabel1.setFont(SudokuTheme.VERSION_FONT);
        jLabel1.setForeground(SudokuTheme.TEXT_MUTED);
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assemble
        card.add(Box.createVerticalStrut(8));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(welcomeLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(jButtonContinue);
        card.add(Box.createVerticalStrut(12));
        card.add(jButtonNewGame);
        card.add(Box.createVerticalStrut(12));
        card.add(jButtonLeaderboard);
        card.add(Box.createVerticalStrut(12));
        card.add(jButtonLoginReturn);
        card.add(Box.createVerticalStrut(20));
        card.add(jLabel1);
        card.add(Box.createVerticalStrut(4));

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(SudokuTheme.BG_DARK);
        wrapper.setBorder(new EmptyBorder(30, 50, 30, 50));
        wrapper.add(card);

        getContentPane().add(wrapper);
        pack();
    }

    private void continueAction() throws IOException {
        Object[] options = {"Test", "Easy", "Medium", "Hard", "Devilish"};
        Difficulty[] a = {Difficulty.TEST, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.DEVILISH};
        Object selected = JOptionPane.showInputDialog(null, "Choose Difficulty", "Load Game",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
        if (selected != null) {
            Difficulty d = null;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selected))
                    d = a[i];
            }
            if (d != null) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                if (Save.loadGame(user, d)) {
                    setVisible(false);
                    this.dispose();
                } else
                    JOptionPane.showMessageDialog(this, "No Save File Found!");
            }
        }
    }

    private void newGameAction() throws FileNotFoundException, UnsupportedEncodingException {
        Object[] options = {"Test", "Easy", "Medium", "Hard", "Devilish"};
        Difficulty[] a = {Difficulty.TEST, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.DEVILISH, Difficulty.TEST};
        Object selected = JOptionPane.showInputDialog(null, "Choose Difficulty", "New Game",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
        if (selected != null) {
            Difficulty d = null;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selected))
                    d = a[i];
            }
            if (d != null && d != Difficulty.TEST) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                View = new GameView(user, d);
                Tools.generateBoard(user, d);
                View.getBoard().PopulateBoard(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt"));
                setVisible(false);
                dispose();
            } else if (d == Difficulty.TEST) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                View = new GameView(user, d);
                Save.initialSave(d, user);
                View.getBoard().PopulateBoard(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt"));
                setVisible(false);
                dispose();
            }
        }
    }

    private void leaderboardAction() {
        LeaderBoard leaderBoard = new LeaderBoard(user);
        leaderBoard.setLocationRelativeTo(null);
        leaderBoard.setVisible(true);
        leaderBoard.setTitle("Leaderboard");
        this.setVisible(false);
    }

    private void loginReturnAction() {
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setTitle("Sudoku");
        login.setVisible(true);
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SudokuTheme.applyTheme();
        EventQueue.invokeLater(() -> new MainMenu(user).setVisible(true));
    }

    private JButton jButtonContinue;
    private JButton jButtonLeaderboard;
    private JButton jButtonLoginReturn;
    private JButton jButtonNewGame;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private static String user;
    private static GameView View;

    public static GameView getView() {
        return View;
    }

    public static void setView(GameView View) {
        MainMenu.View = View;
    }
}
