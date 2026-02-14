package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderBoard extends JFrame {

    public LeaderBoard(String s) {
        user = s;
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        getContentPane().setBackground(SudokuTheme.BG_DARK);
        setResizable(false);

        jTabbedPane = new JTabbedPane();
        jScrollPaneEasy = new JScrollPane();
        jTableEasy = new JTable();
        jScrollPaneMedium = new JScrollPane();
        jTableMedium = new JTable();
        jScrollPaneHard = new JScrollPane();
        jTableHard = new JTable();
        jScrollPaneDevilish = new JScrollPane();
        jTableDevilish = new JTable();

        EasyTable = new String[25][4];
        MediumTable = new String[25][4];
        HardTable = new String[25][4];
        DevilishTable = new String[25][4];

        jButtonExit = SudokuTheme.createSecondaryButton("Back to Menu");
        jButtonExit.addActionListener(e -> exitAction());

        refreshScores();

        String[] columns = {"Rank", "Name", "Score", "Time"};

        setupTable(jTableEasy, EasyTable, columns);
        jScrollPaneEasy.setViewportView(jTableEasy);
        jTabbedPane.addTab("Easy", jScrollPaneEasy);

        setupTable(jTableMedium, MediumTable, columns);
        jScrollPaneMedium.setViewportView(jTableMedium);
        jTabbedPane.addTab("Medium", jScrollPaneMedium);

        setupTable(jTableHard, HardTable, columns);
        jScrollPaneHard.setViewportView(jTableHard);
        jTabbedPane.addTab("Hard", jScrollPaneHard);

        setupTable(jTableDevilish, DevilishTable, columns);
        jScrollPaneDevilish.setViewportView(jTableDevilish);
        jTabbedPane.addTab("Devilish", jScrollPaneDevilish);

        // Style tabbed pane
        jTabbedPane.setBackground(SudokuTheme.BG_DARK);
        jTabbedPane.setForeground(SudokuTheme.TEXT_SECONDARY);
        jTabbedPane.setFont(SudokuTheme.BODY_BOLD);

        // Title
        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(SudokuTheme.HEADING_FONT);
        titleLabel.setForeground(SudokuTheme.TEXT_PRIMARY);

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(SudokuTheme.BG_DARK);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel playerLabel = new JLabel("Player: " + user);
        playerLabel.setFont(SudokuTheme.BODY_BOLD);
        playerLabel.setForeground(SudokuTheme.ACCENT_LIGHT);
        playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        jButtonExit.setAlignmentX(Component.LEFT_ALIGNMENT);
        jButtonExit.setMaximumSize(new Dimension(180, 42));

        rightPanel.add(playerLabel);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(jButtonExit);

        rightPanel.setPreferredSize(new Dimension(200, 0));

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(SudokuTheme.BG_DARK);
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(SudokuTheme.BG_DARK);
        topPanel.setBorder(new EmptyBorder(0, 4, 10, 0));
        topPanel.add(titleLabel, BorderLayout.WEST);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(jTabbedPane, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(700, 450));
        pack();
    }

    private void setupTable(JTable table, String[][] data, String[] columns) {
        table.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

        table.setBackground(SudokuTheme.BG_MEDIUM);
        table.setForeground(SudokuTheme.TEXT_PRIMARY);
        table.setSelectionBackground(SudokuTheme.PRIMARY);
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(SudokuTheme.CELL_BORDER);
        table.setFont(SudokuTheme.BODY_FONT);
        table.setRowHeight(28);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(SudokuTheme.BG_LIGHT);
        header.setForeground(SudokuTheme.TEXT_PRIMARY);
        header.setFont(SudokuTheme.BODY_BOLD);
        header.setBorder(BorderFactory.createLineBorder(SudokuTheme.CELL_BORDER));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }
    }

    public void refreshScores() {
        if (EasyTable[0][0] == null)
            for (int i = 0; i < 25; i++) {
                EasyTable[i][0] = i + 1 + "";
                MediumTable[i][0] = i + 1 + "";
                HardTable[i][0] = i + 1 + "";
                DevilishTable[i][0] = i + 1 + "";
            }
        try {
            Scanner scanner = new Scanner(new File(Tools.getDocumentsPath() + "/Sudoku/LeaderBoards.txt"));
            System.out.println(Tools.getDocumentsPath() + "/Sudoku/LeaderBoards.txt");
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                switch (line) {
                    case "Easy":
                        line = scanner.nextLine();
                        for (int i = 0; !line.equals("") && !line.equals("Medium"); i++) {
                            String info[] = line.split(",");
                            EasyTable[i][1] = info[0];
                            EasyTable[i][2] = info[1];
                            EasyTable[i][3] = info[2];
                            line = scanner.nextLine();
                        }
                        break;
                    case "Medium":
                        line = scanner.nextLine();
                        for (int i = 0; !line.equals("") && !line.equals("Hard"); i++) {
                            String info[] = line.split(",");
                            MediumTable[i][1] = info[0];
                            MediumTable[i][2] = info[1];
                            MediumTable[i][3] = info[2];
                            line = scanner.nextLine();
                        }
                        break;
                    case "Hard":
                        line = scanner.nextLine();
                        for (int i = 0; !line.equals("") && !line.equals("Devilish"); i++) {
                            String info[] = line.split(",");
                            HardTable[i][1] = info[0];
                            HardTable[i][2] = info[1];
                            HardTable[i][3] = info[2];
                            line = scanner.nextLine();
                        }
                        break;
                    case "Devilish":
                        line = scanner.nextLine();
                        for (int i = 0; !line.equals(""); i++) {
                            String info[] = line.split(",");
                            DevilishTable[i][1] = info[0];
                            DevilishTable[i][2] = info[1];
                            DevilishTable[i][3] = info[2];
                            line = scanner.nextLine();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            try {
                PrintWriter leaderBoardWriter = new PrintWriter(Tools.getDocumentsPath() + "/Sudoku/LeaderBoards.txt", "UTF-8");
                leaderBoardWriter.close();
                refreshScores();
            } catch (FileNotFoundException | UnsupportedEncodingException ex1) {
                Logger.getLogger(LeaderBoard.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        String[] cols = {"Rank", "Name", "Score", "Time"};
        jTableEasy.setModel(new DefaultTableModel(EasyTable, cols));
        jTableMedium.setModel(new DefaultTableModel(MediumTable, cols));
        jTableHard.setModel(new DefaultTableModel(HardTable, cols));
        jTableDevilish.setModel(new DefaultTableModel(DevilishTable, cols));
    }

    public void newHighScore(Difficulty difficulty, String new_user, String new_score, String new_time) {
        String strings[][] = null;
        switch (difficulty.toString()) {
            case "Easy": strings = EasyTable; break;
            case "Medium": strings = MediumTable; break;
            case "Hard": strings = HardTable; break;
            case "Devilish": strings = DevilishTable; break;
        }
        for (int i = 0; i < 25; i++) {
            if (strings[i][1] == null) {
                strings[i][1] = new_user;
                strings[i][2] = new_score;
                strings[i][3] = new_time;

                switch (difficulty.toString()) {
                    case "Easy": EasyTable = strings; break;
                    case "Medium": MediumTable = strings; break;
                    case "Hard": HardTable = strings; break;
                    case "Devilish": DevilishTable = strings; break;
                }
                break;
            } else if ((Integer.parseInt(strings[i][2]) < Integer.parseInt(new_score)) ||
                    (Integer.parseInt(strings[i][2]) == Integer.parseInt(new_score) &&
                            Integer.parseInt(strings[i][3]) > Integer.parseInt(new_time))) {
                String one = strings[i][1],
                        two = strings[i][2],
                        three = strings[i][3];
                strings[i][1] = new_user;
                strings[i][2] = new_score;
                strings[i][3] = new_time;

                if (strings[i + 1][1] == null) {
                    strings[i + 1][1] = one;
                    strings[i + 1][2] = two;
                    strings[i + 1][3] = three;

                    switch (difficulty.toString()) {
                        case "Easy": EasyTable = strings; break;
                        case "Medium": MediumTable = strings; break;
                        case "Hard": HardTable = strings; break;
                        case "Devilish": DevilishTable = strings; break;
                    }
                    break;
                } else if (i != 25 && strings[i + 1][1] != null) {
                    switch (difficulty.toString()) {
                        case "Easy": EasyTable = strings; break;
                        case "Medium": MediumTable = strings; break;
                        case "Hard": HardTable = strings; break;
                        case "Devilish": DevilishTable = strings; break;
                    }
                    newHighScore(difficulty, one, two, three);
                }
            }
        }
        try {
            PrintWriter leaderBoardWriter = new PrintWriter(Tools.getDocumentsPath() + "/Sudoku/LeaderBoards.txt", "UTF-8");
            leaderBoardWriter.println("Easy");
            for (int i = 0; EasyTable[i][1] != null; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j != 3)
                        leaderBoardWriter.print(EasyTable[i][j] + ",");
                    else
                        leaderBoardWriter.println(EasyTable[i][j]);
                }
            }
            leaderBoardWriter.println("\nMedium");
            for (int i = 0; MediumTable[i][1] != null; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j != 3)
                        leaderBoardWriter.print(MediumTable[i][j] + ",");
                    else
                        leaderBoardWriter.println(MediumTable[i][j]);
                }
            }
            leaderBoardWriter.println("\nHard");
            for (int i = 0; HardTable[i][1] != null; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j != 3)
                        leaderBoardWriter.print(HardTable[i][j] + ",");
                    else
                        leaderBoardWriter.println(HardTable[i][j]);
                }
            }
            leaderBoardWriter.println("\nDevilish");
            for (int i = 0; DevilishTable[i][1] != null; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j != 3)
                        leaderBoardWriter.print(DevilishTable[i][j] + ",");
                    else
                        leaderBoardWriter.println(DevilishTable[i][j]);
                }
            }
            leaderBoardWriter.println();
            leaderBoardWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(LeaderBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshScores();
    }

    private void exitAction() {
        MainMenu menu = new MainMenu(user);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SudokuTheme.applyTheme();
        EventQueue.invokeLater(() -> new LeaderBoard(user).setVisible(true));
    }

    private JButton jButtonExit;
    private JScrollPane jScrollPaneDevilish;
    private JScrollPane jScrollPaneEasy;
    private JScrollPane jScrollPaneHard;
    private JScrollPane jScrollPaneMedium;
    private JTabbedPane jTabbedPane;
    private JTable jTableDevilish;
    private JTable jTableEasy;
    private JTable jTableHard;
    private JTable jTableMedium;
    private String EasyTable[][];
    private String MediumTable[][];
    private String HardTable[][];
    private String DevilishTable[][];
    private static String user;
}
