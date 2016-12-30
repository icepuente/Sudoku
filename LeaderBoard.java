/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alec
 */
public class LeaderBoard extends javax.swing.JFrame {

    /**
     * Creates new form LeaderBoard
     *
     * @param s
     */
    public LeaderBoard(String s) {
        user = s;
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jScrollPaneEasy = new javax.swing.JScrollPane();
        jTableEasy = new javax.swing.JTable();
        jScrollPaneMedium = new javax.swing.JScrollPane();
        jTableMedium = new javax.swing.JTable();
        jScrollPaneHard = new javax.swing.JScrollPane();
        jTableHard = new javax.swing.JTable();
        jScrollPaneDevilish = new javax.swing.JScrollPane();
        jTableDevilish = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EasyTable = new String[25][4];
        MediumTable = new String[25][4];
        HardTable = new String[25][4];
        DevilishTable = new String[25][4];
        jButtonExport = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        setResizable(false);

        refreshScores();
        jTableEasy.setModel(new javax.swing.table.DefaultTableModel(
                EasyTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableEasy.getTableHeader().setReorderingAllowed(false);
        jScrollPaneEasy.setViewportView(jTableEasy);
        if (jTableEasy.getColumnModel().getColumnCount() > 0) {
            jTableEasy.getColumnModel().getColumn(0).setResizable(false);
            jTableEasy.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableEasy.getColumnModel().getColumn(1).setResizable(false);
            jTableEasy.getColumnModel().getColumn(2).setResizable(false);
            jTableEasy.getColumnModel().getColumn(3).setResizable(false);
        }

        jTabbedPane.addTab("Easy", jScrollPaneEasy);

        jTableMedium.setModel(new javax.swing.table.DefaultTableModel(
                MediumTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableMedium.getTableHeader().setReorderingAllowed(false);
        jScrollPaneMedium.setViewportView(jTableMedium);
        if (jTableMedium.getColumnModel().getColumnCount() > 0) {
            jTableMedium.getColumnModel().getColumn(0).setResizable(false);
            jTableMedium.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableMedium.getColumnModel().getColumn(1).setResizable(false);
            jTableMedium.getColumnModel().getColumn(2).setResizable(false);
            jTableMedium.getColumnModel().getColumn(3).setResizable(false);
        }

        jTabbedPane.addTab("Medium", jScrollPaneMedium);

        jTableHard.setModel(new javax.swing.table.DefaultTableModel(
                HardTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableHard.getTableHeader().setReorderingAllowed(false);
        jScrollPaneHard.setViewportView(jTableHard);
        if (jTableHard.getColumnModel().getColumnCount() > 0) {
            jTableHard.getColumnModel().getColumn(0).setResizable(false);
            jTableHard.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableHard.getColumnModel().getColumn(1).setResizable(false);
            jTableHard.getColumnModel().getColumn(2).setResizable(false);
            jTableHard.getColumnModel().getColumn(3).setResizable(false);
        }

        jTabbedPane.addTab("Hard", jScrollPaneHard);

        jTableDevilish.setModel(new javax.swing.table.DefaultTableModel(
                DevilishTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableDevilish.getTableHeader().setReorderingAllowed(false);
        jScrollPaneDevilish.setViewportView(jTableDevilish);
        if (jTableDevilish.getColumnModel().getColumnCount() > 0) {
            jTableDevilish.getColumnModel().getColumn(0).setResizable(false);
            jTableDevilish.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableDevilish.getColumnModel().getColumn(1).setResizable(false);
            jTableDevilish.getColumnModel().getColumn(2).setResizable(false);
            jTableDevilish.getColumnModel().getColumn(3).setResizable(false);
        }

        jTabbedPane.addTab("Devilish", jScrollPaneDevilish);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("");

        jLabel2.setText("");

        jLabel3.setText("");

        jLabel4.setText("");

        jButtonExport.setText("Export to XML");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jButtonExit.setText("Exit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButtonExport)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonExport)
                                        .addComponent(jButtonExit))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jButtonExport.setVisible(false);
        pack();
    }// </editor-fold>                        

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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

                int seconds = 0;
                int minutes = 0;
                int hours = 0;

                switch (line) {
                    case "Easy":
                        seconds = 0;
                        minutes = 0;
                        hours = 0;
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
                        seconds = 0;
                        minutes = 0;
                        hours = 0;
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
                        seconds = 0;
                        minutes = 0;
                        hours = 0;
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
                        seconds = 0;
                        minutes = 0;
                        hours = 0;
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

        jTableEasy.setModel(new javax.swing.table.DefaultTableModel(
                EasyTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }));
        jTableMedium.setModel(new javax.swing.table.DefaultTableModel(
                MediumTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }));
        jTableHard.setModel(new javax.swing.table.DefaultTableModel(
                HardTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }));
        jTableDevilish.setModel(new javax.swing.table.DefaultTableModel(
                DevilishTable,
                new String[]{
                        "Rank:", "Name:", "Score:", "Time:"
                }));
    }

    public void newHighScore(Difficulty difficulty, String new_user, String new_score, String new_time) {
        String strings[][] = null;
        switch (difficulty.toString()) {
            case "Easy":
                strings = EasyTable;
                break;
            case "Medium":
                strings = MediumTable;
                break;
            case "Hard":
                strings = HardTable;
                break;
            case "Devilish":
                strings = DevilishTable;
                break;
        }
        for (int i = 0; i < 25; i++) {
            if (strings[i][1] == null) {
                strings[i][1] = new_user;
                strings[i][2] = new_score;
                strings[i][3] = new_time;

                switch (difficulty.toString()) {
                    case "Easy":
                        EasyTable = strings;
                        break;
                    case "Medium":
                        MediumTable = strings;
                        break;
                    case "Hard":
                        HardTable = strings;
                        break;
                    case "Devilish":
                        DevilishTable = strings;
                        break;
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
                        case "Easy":
                            EasyTable = strings;
                            break;
                        case "Medium":
                            MediumTable = strings;
                            break;
                        case "Hard":
                            HardTable = strings;
                            break;
                        case "Devilish":
                            DevilishTable = strings;
                            break;
                    }
                    break;
                } else if (i != 25 && strings[i + 1][1] != null) {
                    switch (difficulty.toString()) {
                        case "Easy":
                            EasyTable = strings;
                            break;
                        case "Medium":
                            MediumTable = strings;
                            break;
                        case "Hard":
                            HardTable = strings;
                            break;
                        case "Devilish":
                            DevilishTable = strings;
                            break;
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

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        MainMenu menu = new MainMenu(user);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LeaderBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderBoard(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneDevilish;
    private javax.swing.JScrollPane jScrollPaneEasy;
    private javax.swing.JScrollPane jScrollPaneHard;
    private javax.swing.JScrollPane jScrollPaneMedium;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableDevilish;
    private javax.swing.JTable jTableEasy;
    private javax.swing.JTable jTableHard;
    private javax.swing.JTable jTableMedium;
    private String EasyTable[][];
    private String MediumTable[][];
    private String HardTable[][];
    private String DevilishTable[][];
    private static String user;
    // End of variables declaration                   
}
